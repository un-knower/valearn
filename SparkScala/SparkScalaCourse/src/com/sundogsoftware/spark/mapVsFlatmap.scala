package com.sundogsoftware.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.rdd._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.collection.mutable.ArrayBuffer

object mapVsFlatmap extends App {

  // Set the log level to only print errors
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkContext using every core of the local machine
  val sc = new SparkContext("local[*]", "mapVsFlatmap")

  ///def parallelize[T](seq: Seq[T], numSlices: Int)(implicit evidence$1: ClassTag[T]): RDD[T]
  // parallelize takes sequence,.. returns RDD of element of Sequence
  val intRDD: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4))

  val arrT: Array[(String, Int)] = Array(("aa", 1), ("aa", 2), ("bb", 2))

  // arrtRDD will be RDD of Tuples.. result of parallelize
  val arrTRDD: RDD[(String, Int)] = sc.parallelize(arrT)

  //Square values of tuples
  val mapped = arrTRDD.map(x => (x._1, x._2 * x._2))

  val reduced = mapped.reduceByKey((x, y) => x + y)

  println("Reduced O/p- " + reduced.take(1).toList)

  /*
   it accepts the argument function which returns array, list or sequence (any subtype of scala.TraversableOnce) of elements instead of a single element.
   As a final result it flattens all the elements of the resulting RDD in case
   individual elements are in form of list, array, sequence or any such collection.
     */

  val arrT2: Array[Array[(String, Int)]] = Array(Array(("aa", 1), ("aa", 2), ("bb", 2)), Array(("aa", 1), ("aa", 2), ("bb", 2)))

  val arrT2RDD: RDD[Array[(String, Int)]] = sc.parallelize(arrT2)

  val flatMapped: RDD[(String, Int)] = arrT2RDD.flatMap(x => x)

  //Print first element of flatmapped
  // Note take returns list/array
  println("Flatmapped has flattened Array and result is RDD of Tuples- " + flatMapped.take(1)(0))

  val reduced2 = flatMapped.reduceByKey((x, y) => x + y)

  println("Reduced 2 O/p- " + reduced2.take(1).toList)
}