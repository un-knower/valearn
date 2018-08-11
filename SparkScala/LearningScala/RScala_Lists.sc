object RScala_Lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  
  // val var def  y: => Int (CBN) y:Int (CBV) lazy val (CBV)
  // call by value , call by name

  // val initialized when it's defined
  // last statement gives it value 8
  val a0 = {
    println("hi")
    2 * 2 * 2
  }                                               //> hi
                                                  //| a0  : Int = 8
  // def just defines and it gets initialized on call
  // Hence it'll print hi
  def a01 = {
    println("hi")
    2 * 2 * 2
  }                                               //> a01: => Int
  println(a0)                                     //> 8

  println(a01)                                    //> hi
                                                  //| 8

  val b = Stream(1, 1000000)                      //> b  : scala.collection.immutable.Stream[Int] = Stream(1, ?)
  // println(b(4))

  val a = List(1, 2, 3, 4, 5)                     //> a  : List[Int] = List(1, 2, 3, 4, 5)
  println("a.head" + a.head)                      //> a.head1
  println("a.tail" + a.tail)                      //> a.tailList(2, 3, 4, 5)
  println("a.last" + a.last)                      //> a.last5
  println("a.add" + (a :+ 6))                     //> a.addList(1, 2, 3, 4, 5, 6)
  println("a.add.list " + (a ++ List(6, 7, 8)))   //> a.add.list List(1, 2, 3, 4, 5, 6, 7, 8)
  println("a.drop" + a.drop(2))                   //> a.dropList(3, 4, 5)
  println("a.dropRight" + a.dropRight(2))         //> a.dropRightList(1, 2, 3)
  println("a.map" + a.map(x => fn1(x)))           //> a.mapList(Odd, Even, Odd, Even, Odd)
  println("a.map" + a.flatMap(x => fn2(x)))       //> a.mapList(1, 2, 3, 2, 4, 6, 3, 6, 9, 4, 8, 12, 5, 10, 15)

  println(a.foldRight(0)((x, y) => x + y))        //> 15
  //foldLeft foldRight reduceLEft(auto Intialise) reduceRight
  println(a.reduceLeft(_ + _))                    //> 15

  def fn1(x: Int): String = {
    if (x % 2 == 0) "Even"
    else "Odd"
  }                                               //> fn1: (x: Int)String

  def fn2(x: Int): List[Int] = {
    List(x * 1, x * 2, x * 3)

  }                                               //> fn2: (x: Int)List[Int]
  def fn3(y: => Int, x: Int): Unit = {
    println(x)
  }                                               //> fn3: (y: => Int, x: Int)Unit

  val aList1 = List(List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10), List(11, 12, 13, 14, 15))
                                                  //> aList1  : List[List[Int]] = List(List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10),
                                                  //|  List(11, 12, 13, 14, 15))

  aList1.reduceLeft(_ ++ _).reduceLeft(_ + _)     //> res0: Int = 120

  val ad1 = List(1, 2, 3, 4, 5)                   //> ad1  : List[Int] = List(1, 2, 3, 4, 5)
  val bd1 = List(1, 2, 3, 4, 5, 6, 6, 7)          //> bd1  : List[Int] = List(1, 2, 3, 4, 5, 6, 6, 7)
  println(ad1.foldRight(bd1)(_ :: _)) // a +: List(x,y,z)
                                                  //> List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 6, 7)
  //a.foldLeft(b)(_ :: _)
  println("Even nos: " + a.filter(x => x % 2 == 0))
                                                  //> Even nos: List(2, 4)
  println("Filternot ==> Odd : " + a.filterNot(x => x % 2 == 0))
                                                  //> Filternot ==> Odd : List(1, 3, 5)
  println("partition ==> : " + a.partition(_ % 2 == 0))
                                                  //> partition ==> : (List(2, 4),List(1, 3, 5))
  println("takewhile :" + a.takeWhile(_ - 3 < 0)) //> takewhile :List(1, 2)
  println("dropwhile : " + a.dropWhile(_ - 3 < 0))//> dropwhile : List(3, 4, 5)
  println("span: " + a.span(_ - 3 < 0))           //> span: (List(1, 2),List(3, 4, 5))
  println("exists: " + b.exists(_ == 100))        //> exists: false
  println("forall: " + b.forall(_ < 10))          //> forall: false
  println("zip :" + a.zip(b))                     //> zip :List((1,1), (2,1000000))
  println("zip :" + b.zip(a))                     //> zip :Stream((1,1), ?)
  val m = b.zip(a)                                //> m  : scala.collection.immutable.Stream[(Int, Int)] = Stream((1,1), ?)
  println("unzip" + m.unzip)                      //> unzip(Stream(1, ?),Stream(1, ?))
  val k = List("Ramji", "sdfsdf", "SDfsdfsd", "aabb")
                                                  //> k  : List[String] = List(Ramji, sdfsdf, SDfsdfsd, aabb)
  println(k.sorted)                               //> List(Ramji, SDfsdfsd, aabb, sdfsdf)

  //a.foldfoldLeft(b)(_ :: _)
  //a * b = b * a

  val a3: List[(String, List[(String, List[Int])], String, List[(String, Int)])] =
    List(
      ("abcd", List(("abcd1", List(1, 2)), ("abcd2", List(3, 4))), "mdff", List(("sdfsdsdfd1", 5), ("dgdfgdfgd", 6))),
      ("abcd", List(("abcd1", List(11, 22)), ("abcd2", List(33, 44))), "mdff", List(("sdfsdsdfd1", 55), ("dgdfgdfgd", 66))))
                                                  //> a3  : List[(String, List[(String, List[Int])], String, List[(String, Int)])
                                                  //| ] = List((abcd,List((abcd1,List(1, 2)), (abcd2,List(3, 4))),mdff,List((sdfs
                                                  //| dsdfd1,5), (dgdfgdfgd,6))), (abcd,List((abcd1,List(11, 22)), (abcd2,List(33
                                                  //| , 44))),mdff,List((sdfsdsdfd1,55), (dgdfgdfgd,66))))

  println(a3.map(x => x._2.map(x => x._2.head)))  //> List(List(1, 3), List(11, 33))

  a3.map(x => x._2.head._2.head)                  //> res1: List[Int] = List(1, 11)

  def f1(x: Int): Unit= {
    println(List(1 * x, 2 * x, 3 * x))
  }                                               //> f1: (x: Int)Unit

  a3.map(x => x._2.head._2.head).foreach(f1)      //> List(1, 2, 3)
                                                  //| List(11, 22, 33)

  val aMap = Map("a" -> "Apple" , "b" -> "Ball")  //> aMap  : scala.collection.immutable.Map[String,String] = Map(a -> Apple, b -
                                                  //| > Ball)
  
  
}