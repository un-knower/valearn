package com.pwc.scala

object RamjiSc extends App {

  val aMap = Map(1 -> "Apple", 2 -> "Ball", 3 -> "Cat")

  def fn2(x: (Int, String)): String = {

    if (x._1 < 2) "L2" else "G2"

  }

  println(aMap.groupBy(fn2))

  val bMap = Map((1, 2) -> ("Apple", "Mapple", "Chappel"), (3, 4) -> ("Ball", "Call", "Hall"))

  println(bMap.filterKeys(x => x._1 > 2))

  //println(bMap.filter(x => (x._1 > 1) & (x._2._1 ="Apple" )))

  println(bMap.foldLeft((List[(Int, Int)](), List[(String, String, String)]()))((x, y) => (x._1 :+ y._1, x._2 :+ y._2)))

  println(bMap.get((1, 2)))

  def fn1(x: ((Int, Int), (String, String, String))): ((String, String, String), (Int)) = {

    (x._2, x._1._2)
  }
  println(bMap.map(fn1))

  println(bMap.mapValues(x => x._2))

  println("Count  " + aMap.count(_ == (1, "Apple")))

  println("Count  " + bMap.count(x => x._1._1 == 1))

}