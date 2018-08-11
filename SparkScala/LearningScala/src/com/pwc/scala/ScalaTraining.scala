package com.pwc.scala

object getArraySum{

  def add(a:Array[Int],b:Array[Int]) = {
    
    val a:Array[Int] = Array(1,2,3,4,5)
    val b:Array[Int] = Array(1,2,3,4,5,6,7,8)
    a.zip(b).map(x => x._1 + x._2)
    
  }
  def main(args: Array[String]): Unit = {
    
  }
}