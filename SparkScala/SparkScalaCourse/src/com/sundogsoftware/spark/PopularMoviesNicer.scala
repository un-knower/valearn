package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.io.Source
import java.nio.charset.CodingErrorAction
import scala.io.Codec

/** Find the movies with the most ratings. */
object PopularMoviesNicer {

  /* Data Snippet
      1|Toy Story (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)|0|0|0|1|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0
      2|GoldenEye (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?GoldenEye%20(1995)|0|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0
      3|Four Rooms (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0
      4|Get Shorty (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)|0|1|0|0|0|1|0|0|1|0|0|0|0|0|0|0|0|0|0
      5|Copycat (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Copycat%20(1995)|0|0|0|0|0|0|1|0|1|0|0|0|0|0|0|0|1|0|0
   */

  /** Load up a Map of movie IDs to movie names. */
  def loadMovieNames(): Map[Int, String] = {

    // Handle character encoding issues:
    implicit val codec = Codec("UTF-8")
    codec.onMalformedInput(CodingErrorAction.REPLACE)
    codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

    // Create a Map of Ints to Strings, and populate it from u.item.
    var movieNames: Map[Int, String] = Map()
    // Scala Functions on Driver
    val lines = Source.fromFile("C:/SparkScala/ml-100k/u.item").getLines()
    //lines: Iterator[String] = non-empty iterator
    /*
        scala> lines.next()
        res20: String = 1|Toy Story (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)|0|0|0|1|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0

        scala> lines.next()
        res21: String = 2|GoldenEye (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?GoldenEye%20(1995)|0|1|1|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0

        scala> lines.next()
        res22: String = 3|Four Rooms (1995)|01-Jan-1995||http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|1|0|0
      */
    /*
      scala> lines.next().split('|')
			res27: Array[String] = Array(4, Get Shorty (1995), 01-Jan-1995, "", http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995), 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
      */
    /*
      * See Next used incorrectly..
      scala> println((lines.next().split('|')(1),lines.next().split('|')(1)))
			(Get Shorty (1995),Copycat (1995))
      *
      *
      */
    for (line <- lines) {
      var fields = line.split('|')
      if (fields.length > 1) {
        movieNames += (fields(0).toInt -> fields(1))
      }
    }

    return movieNames
  }
  //loadMovieNames: ()Map[Int,String]

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "PopularMoviesNicer")

    // Create a broadcast variable of our ID -> movie name map
    var nameDict = sc.broadcast(loadMovieNames)
    //nameDict: org.apache.spark.broadcast.Broadcast[Map[Int,String]] = Broadcast(21)
    /*
    scala> nameDict.id
		res38: Long = 21
     */

    // Read in each rating line
    val lines = sc.textFile("C:/SparkScala/ml-100k/u.data")

    // Map to (movieID, 1) tuples
    val movies = lines.map(x => (x.split("\t")(1).toInt, 1))

    // Count up all the 1's for each movie
    val movieCounts = movies.reduceByKey((x, y) => x + y)

    // Flip (movieID, count) to (count, movieID)
    val flipped = movieCounts.map(x => (x._2, x._1))

    // Sort
    val sortedMovies = flipped.sortByKey()

    // Fold in the movie names from the broadcast variable
    val sortedMoviesWithNames  = sortedMovies.map(x => (nameDict.value(x._2), x._1))

    /*
      scala>  Map(1 -> "Toy Story", 2 -> "Golden Eye").get(1)
      res36: Option[String] = Some(Toy Story)
      scala> nameDict.value(2)
			res43: String = GoldenEye (1995)
      scala> nameDict.value(21)
			res44: String = Muppet Treasure Island (1996)
    */
    
    
    /*
       scala> val sortedMoviesWithNames  = sortedMovies.map(x => (nameDict.value(x._2), x._1))
      org.apache.spark.SparkException: Task not serializable
        at org.apache.spark.util.ClosureCleaner$.ensureSerializable(ClosureCleaner.scala:340)
        at org.apache.spark.util.ClosureCleaner$.org$apache$spark$util$ClosureCleaner$$clean(ClosureCleaner.scala:330)
        at org.apache.spark.util.ClosureCleaner$.clean(ClosureCleaner.scala:156)
        at org.apache.spark.SparkContext.clean(SparkContext.scala:2294)
        at org.apache.spark.rdd.RDD$$anonfun$map$1.apply(RDD.scala:370)
        at org.apache.spark.rdd.RDD$$anonfun$map$1.apply(RDD.scala:369)
        at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:151)
        at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:112)
        at org.apache.spark.rdd.RDD.withScope(RDD.scala:362)
        at org.apache.spark.rdd.RDD.map(RDD.scala:369)
        ... 48 elided
      Caused by: java.io.NotSerializableException: scala.io.Codec
     */

    // Collect and print results
    val results = sortedMoviesWithNames.collect()

    results.foreach(println)
  }

}

