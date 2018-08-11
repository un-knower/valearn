package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.util.LongAccumulator
import org.apache.log4j._
import scala.collection.mutable.ArrayBuffer

/**
 * Finds the degrees of separation between two Marvel comic book characters, based
 *  on co-appearances in a comic.
 */

/**
 * Let's Assume Sample marvel-graph
 * 5306 1165 3836 4361 1282 716 4289
 * 5988 748 1282 3752 4655 14 1872
 *
 *
 */

object DegreesOfSeparation {

  // The characters we want to find the separation between.
  val startCharacterID = 5306 //SpiderMan
  val targetCharacterID = 14 //ADAM 3,031 (who?)

  // We make our accumulator a "global" Option so we can reference it in a mapper later.
  var hitCounter: Option[LongAccumulator] = None
  //hitCounter: Option[org.apache.spark.util.LongAccumulator] = None

  // Some custom data types
  // BFSData contains an array of hero ID connections, the distance, and color.
  type BFSData = (Array[Int], Int, String)
  // A BFSNode has a heroID and the BFSData associated with it.
  type BFSNode = (Int, BFSData)

  /** Converts a line of raw input into a BFSNode */
  def convertToBFS(line: String): BFSNode = {

    // Split up the line into fields
    val fields = line.split("\\s+")

    // Extract this hero ID from the first field
    val heroID = fields(0).toInt

    // Extract subsequent hero ID's into the connections array
    var connections: ArrayBuffer[Int] = ArrayBuffer()
    for (connection <- 1 to (fields.length - 1)) {
      connections += fields(connection).toInt
    }

    /**
     * scala> val heroID = "5306 1165 3836 4361 1282 716 4289".split("\\s+").head.toInt
     * heroID: Int = 5306
     *
     * scala> val connections = "5306 1165 3836 4361 1282 716 4289".split("\\s+").tail.map(_.toInt)
     * connections: Array[Int] = Array(1165, 3836, 4361, 1282, 716, 4289)
     *
     */

    // Default distance and color is 9999 and white
    var color: String = "WHITE"
    var distance: Int = 9999

    // Unless this is the character we're starting from
    // Hero ID = 5306. So All Lines with Starting Int = 5306 will be a BFSNode with color = GRAY
    // Rest all lines will have color = WHITE
    if (heroID == startCharacterID) {
      color = "GRAY"
      distance = 0
    }

    return (heroID, (connections.toArray, distance, color))
  }

  //convertToBFS: (line: String)BFSNode

  /** Create "iteration 0" of our RDD of BFSNodes */
  /** Reads File and Converts a RDD of line(String) of raw input into a RDD of BFSNodes */

  def createStartingRdd(sc: SparkContext): RDD[BFSNode] = {
    val inputFile = sc.textFile("C:/SparkScala/marvel-graph.txt")
    //inputFile: org.apache.spark.rdd.RDD[String] = C:/SparkScala/marvel-graph.txt MapPartitionsRDD[1] at textFile at <console>:24
    return inputFile.map(convertToBFS)
    /*
     scala> inputFile.map(convertToBFS)
		 res12: org.apache.spark.rdd.RDD[BFSNode] = MapPartitionsRDD[2] at map at <console>:53
     */

  }
  //createStartingRdd: (sc: org.apache.spark.SparkContext)org.apache.spark.rdd.RDD[BFSNode]

  /** Expands a BFSNode into this node and its children */
  /** Called from Flatmap.For each BFSNode.Accepts one BFSNode and Returns an Array of Node and It's Children Nodes */

  def bfsMap(node: BFSNode): Array[BFSNode] = {

    // Extract data from the BFSNode
    val characterID: Int = node._1
    val data: BFSData = node._2

    val connections: Array[Int] = data._1
    val distance: Int = data._2
    var color: String = data._3

    // This is called from flatMap, so we return an array
    // of potentially many BFSNodes to add to our new RDD

    var results: ArrayBuffer[BFSNode] = ArrayBuffer()

    // Gray nodes are flagged for expansion, and create new
    // gray nodes for each connection
    // Connections for Nodes with HeroId =5306 which are marked GRAY will be expanded
    // and for each connection a BFSNode will be created with color = GRAY
    // Note We marked distance of Gray Nodes = 0 in bfsMap function
    // So all new BFSNodes created out of connections will have distance=1
    if (color == "GRAY") {
      for (connection <- connections) {
        val newCharacterID = connection
        val newDistance = distance + 1
        val newColor = "GRAY"

        // Have we stumbled across the character we're looking for?
        // If so increment our accumulator so the driver script knows.
        if (targetCharacterID == connection) {
          if (hitCounter.isDefined) {
            hitCounter.get.add(1)
          }
        }

        // Create our new Gray node for this connection and add it to the results
        val newEntry: BFSNode = (newCharacterID, (Array(), newDistance, newColor))
        results += newEntry
      }

      // Color this GRAY node as black, indicating it has been processed already.
      color = "BLACK"
    }

    // Add the original node back in, so its connections can get merged with
    // the gray nodes in the reducer.
    val thisEntry: BFSNode = (characterID, (connections, distance, color))
    results += thisEntry
    //scala> ArrayBuffer(1,2,3) += 0
    //res32: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 0)
    // Or just do result += node

    return results.toArray
  }
  //bfsMap: (node: BFSNode)Array[BFSNode]

  /** Combine nodes for the same heroID, preserving the shortest length and darkest color. */
  def bfsReduce(data1: BFSData, data2: BFSData): BFSData = {

    // Extract data that we are combining
    val edges1: Array[Int] = data1._1
    val edges2: Array[Int] = data2._1
    val distance1: Int = data1._2
    val distance2: Int = data2._2
    val color1: String = data1._3
    val color2: String = data2._3

    // Default node values
    var distance: Int = 9999
    var color: String = "WHITE"
    var edges: ArrayBuffer[Int] = ArrayBuffer()

    // See if one is the original node with its connections.
    // If so preserve them.
    if (edges1.length > 0) {
      edges ++= edges1
    }
    if (edges2.length > 0) {
      edges ++= edges2
    }

    // Preserve minimum distance
    if (distance1 < distance) {
      distance = distance1
    }
    if (distance2 < distance) {
      distance = distance2
    }

    // Preserve darkest color
    if (color1 == "WHITE" && (color2 == "GRAY" || color2 == "BLACK")) {
      color = color2
    }
    if (color1 == "GRAY" && color2 == "BLACK") {
      color = color2
    }
    if (color2 == "WHITE" && (color1 == "GRAY" || color1 == "BLACK")) {
      color = color1
    }
    if (color2 == "GRAY" && color1 == "BLACK") {
      color = color1
    }

    return (edges.toArray, distance, color)
  }
  //bfsReduce: (data1: BFSData, data2: BFSData)BFSData

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "DegreesOfSeparation")

    // Our accumulator, used to signal when we find the target
    // character in our BFS traversal.
    hitCounter = Some(sc.longAccumulator("Hit Counter"))
    //hitCounter: Option[org.apache.spark.util.LongAccumulator] = Some(LongAccumulator(id: 0, name: Some(Hit Counter), value: 0))

    // Read file into BFS Nodes RDD
    //We'll have RDD of BDSNodes with colr = 'GRAY' for Node where heroID == startCharacterID
    var iterationRdd = createStartingRdd(sc)
    //iterationRdd: org.apache.spark.rdd.RDD[BFSNode] = MapPartitionsRDD[5] at map at <console>:58

    var iteration: Int = 0
    
    
    for (iteration <- 1 to 10) {
      println("Running BFS Iteration# " + iteration)

      // Create new vertices as needed to darken or reduce distances in the
      // reduce stage. If we encounter the node we're looking for as a GRAY
      // node, increment our accumulator to signal that we're done.

      val mapped: RDD[BFSNode] = iterationRdd.flatMap(bfsMap)
      //mapped: org.apache.spark.rdd.RDD[BFSNode] = MapPartitionsRDD[6] at flatMap at <console>:62

      /**
       * scala> iterationRdd.map(bfsMap).count()
       * res46: Long = 2
       *
       * scala> iterationRdd.map(bfsMap).first()
       * res47: Array[BFSNode] = Array((1165,(Array(),1,GRAY)), (3836,(Array(),1,GRAY)), (4361,(Array(),1,GRAY)), (1282,(Array(),1,GRAY)), (716,(Array(),1,GRAY)), (4289,(Array(),1,GRAY)), (5306,(Array(1165, 3836, 4361, 1282, 716, 4289),0,BLACK)))
       *
       * scala> iterationRdd.flatMap(bfsMap)
       * res48: org.apache.spark.rdd.RDD[BFSNode] = MapPartitionsRDD[32] at flatMap at <console>:63
       *
       * scala> iterationRdd.flatMap(bfsMap).count()
       * res49: Long = 8
       *
       * scala> iterationRdd.flatMap(bfsMap).first()
       * res50: BFSNode = (1165,(Array(),1,GRAY))
       *
       * // Accessing last element of RDD.. take gives List of elements.. so I take all elements of RDD and then last on that list
       * scala> iterationRdd.flatMap(bfsMap).take(iterationRdd.flatMap(bfsMap).count.toInt).last
       * res56: BFSNode = (5988,(Array(748, 1282, 3752, 4655, 14, 1872),9999,WHITE))
       * //Similarly Second Element
       * scala> iterationRdd.flatMap(bfsMap).take(iterationRdd.flatMap(bfsMap).count.toInt)(2)
       * res57: BFSNode = (4361,(Array(),1,GRAY))
       *
       */

      // Note that mapped.count() action here forces the RDD to be evaluated, and
      // that's the only reason our accumulator is actually updated.
      println("Processing " + mapped.count() + " values.")

      if (hitCounter.isDefined) {
        val hitCount = hitCounter.get.value
        if (hitCount > 0) {
          println("Hit the target character! From " + hitCount +
            " different direction(s).")
          return
        }
      }

      // Reducer combines data for each character ID, preserving the darkest
      // color and shortest path.
      iterationRdd = mapped.reduceByKey(bfsReduce)
    }
  }
}