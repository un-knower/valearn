object RTailRecursion {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /** Cartesion Product of Two Lists */

  val a = List(1, 2, 3)                           //> a  : List[Int] = List(1, 2, 3)
  val b = List(4, 5, 6)                           //> b  : List[Int] = List(4, 5, 6)

  def Multi(a: List[Int], b: List[Int]): List[Int] = {

    def loop(xtemp: List[Int], ytemp: List[Int], acc: List[Int]): List[Int] =
      {

        if (xtemp.isEmpty) acc
        else {
          if (ytemp.isEmpty) loop(xtemp.tail, b, acc)
          else loop(xtemp, ytemp.tail, acc ++ List(xtemp.head * ytemp.head))
        }

      }

    loop(a, b, List())
  }                                               //> Multi: (a: List[Int], b: List[Int])List[Int]

  Multi(a, b)                                     //> res0: List[Int] = List(4, 5, 6, 8, 10, 12, 12, 15, 18)

  /** Normal sum vs TailRecursive Sum */

  def sum(a: Stream[Int]): Int = {
    if (a.isEmpty) 0
    else a.head + sum(a.tail)
  }                                               //> sum: (a: Stream[Int])Int

  def sumTail(a: Stream[Int]): Int = {
    def loop(a: Stream[Int], acc: Int): Int = {
      if (a.isEmpty) acc
      else loop(a.tail, acc + a.head)
    }
    loop(a, 0)
  }                                               //> sumTail: (a: Stream[Int])Int

  println(sumTail((1 to 10000000).toStream))      //> -2004260032
  println(sum((1 to 100000).toStream))            //> java.lang.StackOverflowError
                                                  //| 	at scala.collection.immutable.Range.apply(Range.scala:144)
                                                  //| 	at scala.collection.immutable.Range.apply(Range.scala:60)
                                                  //| 	at scala.collection.IndexedSeqLike$Elements.next(IndexedSeqLike.scala:62
                                                  //| )
                                                  //| 	at scala.collection.Iterator.toStream(Iterator.scala:1403)
                                                  //| 	at scala.collection.Iterator.toStream$(Iterator.scala:1402)
                                                  //| 	at scala.collection.AbstractIterator.toStream(Iterator.scala:1417)
                                                  //| 	at scala.collection.Iterator.$anonfun$toStream$1(Iterator.scala:1403)
                                                  //| 	at scala.collection.immutable.Stream$Cons.tail(Stream.scala:1169)
                                                  //| 	at scala.collection.immutable.Stream$Cons.tail(Stream.scala:1159)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.scala:31)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.scala:31)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.scala:31)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.scala:31)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.scala:31)
                                                  //| 	at RTailRecursion$.sum$1(RTailRecursion.sca
                                                  //| Output exceeds cutoff limit.

}