package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

/** Count up how many of each word appears in a book as simply as possible. */
object WordCount {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "WordCount")

    // Read each line of my book into an RDD
    val input = sc.textFile("C:/SparkScala/book.txt")
    //input: org.apache.spark.rdd.RDD[String] = C:/SparkScala/book.txt MapPartitionsRDD[14] at textFile at <console>:24

    /*
     *
     scala> input.take(4)
		 res9: Array[String] = Array(Self-Employment: Building an Internet Business of One, Achieving Financial and Personal Freedom through a Lifestyle Technology Business, By Frank Kane, "")
     */

    // Split into words separated by a space character

    // String has all functions of Sequence

    val aString: String = "This is a String"
    val aArray: Array[String] = "This is a String".split(" ")
    //def flatMap[U](f: String => TraversableOnce[U])(implicit evidence$4: ClassTag[U]): RDD[U]

    val words = input.flatMap(x => x.split(" "))
    //words: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[15] at flatMap at <console>:26
    /*
     scala> words.take(5)
		 res15: Array[String] = Array(Self-Employment:, Building, an, Internet, Business)
     *
     */

    // Count up the occurrences of each word
    // countByValue -It is an action
    //It returns the count of each unique value in an RDD as a local Map (as a Map to driver program)

    val wordCounts = words.countByValue()
    //wordCounts: scala.collection.Map[String,Long] = Map(foolproof -> 1, precious -> 1, ...
    // Print the results.
    wordCounts.foreach(println)
  }

}

