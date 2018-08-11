package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Count up how many of each star rating exists in the MovieLens 100K data set. */
object RatingsCounter {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "RatingsCounter")

    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("C:/SparkScala/ml-100k/u.data")
    //lines: org.apache.spark.rdd.RDD[String] = ../ml-100k/u.data MapPartitionsRDD[1] at textFile at <console>:33

    // Convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)

    /*
     * Snippet of Data
     	196	242	3	881250949
      186	302	3	891717742
      22	377	1	878887116
      244	51	2	880606923
      166	346	1	886397596
      298	474	4	884182806
     *
     */
    val ratings = lines.map(x => x.toString().split("\t")(2))

    //ratings: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[4] at map at <console>:35

    /*
    scala> ratings.take(4)
		res3: Array[String] = Array(3, 3, 1, 2)
     */

    // Count up how many times each value (rating) occurs
    // countByValue -It is an action
    //It returns the count of each unique value in an RDD as a local Map (as a Map to driver program)
    //(value, countofvalues) pair
    //Care must be taken to use this API since it returns the value to driver program so
    //it's suitable only for small values.

    val results = ratings.countByValue()

    //scala> val results = ratings.countByValue()
    //results: scala.collection.Map[String,Long] = Map(4 -> 34174, 5 -> 21201, 1 -> 6110, 2 -> 11370, 3 -> 27145)

    // Sort the resulting map of (rating, count) tuples
    //sortBy Function is available in Seq trait
    val sortedResults = results.toSeq.sortBy(_._1)

    // Create a Pair RDD just not RDD of String like last time
    val ratings2 = lines.map(x => (x.toString().split("\t")(2), 1))
    //ratings2: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[8] at map at <console>:35
    /*
     * scala> ratings2.take(4)
			res4: Array[(String, Int)] = Array((3,1), (3,1), (1,1), (2,1))
     */

    //reduceByKey is a function in Paired RDD
    /**
     * > reduceByKey() is transformation which operate on pairRDD (which contains Key/Value).
     * > PairRDD contains tuple, hence we need to pass the function that operator on tuple instead of each element.
     * > It merges the values with the same key using associative reduce function.
     * > It is wide operation because data shuffles may happen across multiple partitions.
     * > It merges data locally before sending data across partitions for optimize data shuffling.
     * > It takes function as an input which has two parameter of the same type (values associated with same key) and one element output of the input type(value)
     */

    val results2 = ratings2.reduceByKey((x, y) => x + y)
    //results2: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[9] at reduceByKey at <console>:37

    // Flipping map and values so that I can sort by num of occurence by sortByKey function
    val flipped = results2.map(x => (x._2, x._1))
    //flipped: org.apache.spark.rdd.RDD[(Int, String)] = MapPartitionsRDD[10] at map at <console>:39

    val sortedResults2 = flipped.sortByKey()

    // Print each result on its own line.
    sortedResults.foreach(println)
    sortedResults2.foreach(println)

  }
}
