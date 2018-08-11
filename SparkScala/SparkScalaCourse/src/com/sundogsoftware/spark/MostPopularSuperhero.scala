package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Find the superhero with the most co-appearances. */
object MostPopularSuperhero {
  /*
   * Data Snippet marvel-names
   * 	1 "24-HOUR MAN/EMMANUEL"
      2 "3-D MAN/CHARLES CHAN"
      3 "4-D MAN/MERCURIO"
      4 "8-BALL/"
      5 "A"
      6 "A'YIN"
   *
   * marvel-graph
   * 	5988 748 1722 3752 4655 5743 1872 3413 5527 6368 6085 4319 4728 1636 2397 3364 4001 1614 1819 1585 732 2660 3952 2507 3891 2070 2239 2602 612 1352 5447 4548 1596 5488 1605 5517 11 479 2554 2043 17 865 4292 6312 473 534 1479 6375 4456
      5989 4080 4264 4446 3779 2430 2297 6169 3530 3272 4282 6432 2548 4140 185 105 3878 2429 1334 4595 2767 3956 3877 4776 4946 3407 128 269 5775 5121 481 5516 4758 4053 1044 1602 3889 1535 6038 533 3986
      5982 217 595 1194 3308 2940 1815 794 1503 5197 859 5096 6039 2664 651 2244 528 284 1449 1097 1172 1092 108 3405 5204 387 4607 4545 3705 4930 1805 4712 4404 247 4754 4427 1845 536 5795 5978 533 3984 6056
      5983 1165 3836 4361 1282 716 4289 4646 6300 5084 2397 4454 1913 5861 5485
   *
   *
   */
  // Function to extract the hero ID and number of connections from each line
  def countCoOccurences(line: String) = {
    var elements = line.split("\\s+")
    (elements(0).toInt, elements.length - 1)
  }

  //countCoOccurences: (line: String)(Int, Int)

  // Function to extract hero ID -> hero name tuples (or None in case of failure)
  def parseNames(line: String): Option[(Int, String)] = {
    var fields = line.split('\"')
    if (fields.length > 1) {
      return Some(fields(0).trim().toInt, fields(1))
    } else {
      return None // flatmap will just discard None results, and extract data from Some results.
    }
  }
  //parseNames: (line: String)Option[(Int, String)]

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "MostPopularSuperhero")

    // Build up a hero ID -> name RDD
    val names = sc.textFile("C:/SparkScala/marvel-names.txt")
    //names: org.apache.spark.rdd.RDD[String] = C:/SparkScala/marvel-names.txt MapPartitionsRDD[1] at textFile at <console>:24

    val namesRdd = names.flatMap(parseNames)
    //namesRdd: org.apache.spark.rdd.RDD[(Int, String)] = MapPartitionsRDD[2] at flatMap at <console>:28
    //flatmap will just discard None results, and extract data from Some results.

    /*
     scala> namesRdd.take(10)
		 res3: Array[(Int, String)] = Array((1,24-HOUR MAN/EMMANUEL), (2,3-D MAN/CHARLES CHAN), (3,4-D MAN/MERCURIO), (4,8-BALL/), (5,A), (6,A'YIN), (7,ABBOTT, JACK), (8,ABCISSA), (9,ABEL), (10,ABOMINATION/EMIL BLO))
     */

    // Load up the superhero co-apperarance data
    val lines = sc.textFile("C:/SparkScala/marvel-graph.txt")
    //lines: org.apache.spark.rdd.RDD[String] = C:/SparkScala/marvel-graph.txt MapPartitionsRDD[4] at textFile at <console>:24

    // Convert to (heroID, number of connections) RDD
    val pairings = lines.map(countCoOccurences)
    //pairings: org.apache.spark.rdd.RDD[(Int, Int)] = MapPartitionsRDD[5] at map at <console>:28

    // Combine entries that span more than one line
    val totalFriendsByCharacter = pairings.reduceByKey((x, y) => x + y)
    //totalFriendsByCharacter: org.apache.spark.rdd.RDD[(Int, Int)] = ShuffledRDD[6] at reduceByKey at <console>:30

    // Flip it to # of connections, hero ID
    val flipped = totalFriendsByCharacter.map(x => (x._2, x._1))
    //flipped: org.apache.spark.rdd.RDD[(Int, Int)] = MapPartitionsRDD[7] at map at <console>:32

    // Find the max # of connections
    val mostPopular = flipped.max()
    // Default behavior sort by Key

    /**
     * public T max(scala.math.Ordering<T> ord)
     * Returns the max of this RDD as defined by the implicit Ordering[T].
     * Parameters:
     * ord - (undocumented)
     * Returns:
     * the maximum element of the RDD
     */
    //mostPopular: (Int, Int) = (1933,859)

    totalFriendsByCharacter.max()(Ordering[Int].on(x => x._2))
    //scala> totalFriendsByCharacter.max()(Ordering[Int].on(x => x._2))
    //res13: (Int, Int) = (859,1933)

    totalFriendsByCharacter.max()(Ordering[(Int, Int)].on(x => (x._2, x._1)))
    //scala> totalFriendsByCharacter.max()(Ordering[(Int,Int)].on(x => (x._2, x._1)))
    //res14: (Int, Int) = (859,1933)

    // Look up the name (lookup returns an array of results, so we need to access the first result with (0)).
    val mostPopularName = namesRdd.lookup(mostPopular._2)(0)

    // Print out our answer!
    println(s"$mostPopularName is the most popular superhero with ${mostPopular._1} co-appearances.")
  }

}
