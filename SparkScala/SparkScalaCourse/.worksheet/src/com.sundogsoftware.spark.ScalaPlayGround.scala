package com.sundogsoftware.spark

import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object ScalaPlayGround {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(194); 
  println("Welcome to the Scala worksheet");$skip(52); 
  
   Logger.getLogger("org").setLevel(Level.ERROR);$skip(158); 
        
    // Create a SparkContext using every core of the local machine, named RatingsCounter
    val sc = new SparkContext("local[*]", "RatingsCounter");System.out.println("""sc  : org.apache.spark.SparkContext = """ + $show(sc ));$skip(110); 
   
    // Load up each line of the ratings data into an RDD
    val lines = sc.textFile("../ml-100k/u.data");System.out.println("""lines  : org.apache.spark.rdd.RDD[String] = """ + $show(lines ));$skip(219); 
    
    // Convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)
    val ratings = lines.map(x => x.toString().split("\t")(2));System.out.println("""ratings  : org.apache.spark.rdd.RDD[String] = """ + $show(ratings ))}
    
}
