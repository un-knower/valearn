object RamjiScala {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(63); 
  println("Welcome to the Scala worksheet");$skip(222); 
  // val var def  y: => Int (CBN) y:Int (CBV) lazy val (CBV)
  // call by value , call by name

  // val initialized when it's defined
  // last statement gives it value 8
  val a0 = {
    println("hi")
    2 * 2 * 2
  };System.out.println("""a0  : Int = """ + $show(a0 ));$skip(130); 
  // def just defines and it gets initialized on call
  // Hence it'll print hi
  def a01 = {
    println("hi")
    2 * 2 * 2
  };System.out.println("""a01: => Int""");$skip(14); 
  println(a0);$skip(17); 

  println(a01);$skip(31); 

  val b = Stream(1, 1000000);System.out.println("""b  : scala.collection.immutable.Stream[Int] = """ + $show(b ));$skip(51); 
  // println(b(4))

  val a = List(1, 2, 3, 4, 5);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(29); 
  println("a.head" + a.head);$skip(29); 
  println("a.tail" + a.tail);$skip(29); 
  println("a.last" + a.last);$skip(30); 
  println("a.add" + (a :+ 6));$skip(48); 
  println("a.add.list " + (a ++ List(6, 7, 8)));$skip(32); 
  println("a.drop" + a.drop(2));$skip(42); 
  println("a.dropRight" + a.dropRight(2));$skip(40); 
  println("a.map" + a.map(x => fn1(x)));$skip(44); 
  println("a.map" + a.flatMap(x => fn2(x)));$skip(45); 

  println(a.foldRight(0)((x, y) => x + y));$skip(93); 
  //foldLeft foldRight reduceLEft(auto Intialise) reduceRight
  println(a.reduceLeft(_ + _));$skip(78); 

  def fn1(x: Int): String = {
    if (x % 2 == 0) "Even"
    else "Odd"
  };System.out.println("""fn1: (x: Int)String""");$skip(71); 

  def fn2(x: Int): List[Int] = {
    List(x * 1, x * 2, x * 3)

  };System.out.println("""fn2: (x: Int)List[Int]""");$skip(58); 
  def fn3(y: => Int, x: Int): Unit = {
    println(x)
  };System.out.println("""fn3: (y: => Int, x: Int)Unit""");$skip(91); 

  val aList1 = List(List(1, 2, 3, 4, 5), List(6, 7, 8, 9, 10), List(11, 12, 13, 14, 15));System.out.println("""aList1  : List[List[Int]] = """ + $show(aList1 ));$skip(48); val res$0 = 

  aList1.reduceLeft(_ ++ _).reduceLeft(_ + _);System.out.println("""res0: Int = """ + $show(res$0));$skip(34); 

  val ad1 = List(1, 2, 3, 4, 5);System.out.println("""ad1  : List[Int] = """ + $show(ad1 ));$skip(41); 
  val bd1 = List(1, 2, 3, 4, 5, 6, 6, 7);System.out.println("""bd1  : List[Int] = """ + $show(bd1 ));$skip(58); 
  println(ad1.foldRight(bd1)(_ :: _));$skip(78);  // a +: List(x,y,z)
  //a.foldLeft(b)(_ :: _)
  println("Even nos: " + a.filter(x => x % 2 == 0));$skip(65); 
  println("Filternot ==> Odd : " + a.filterNot(x => x % 2 == 0));$skip(56); 
  println("partition ==> : " + a.partition(_ % 2 == 0));$skip(50); 
  println("takewhile :" + a.takeWhile(_ - 3 < 0));$skip(51); 
  println("dropwhile : " + a.dropWhile(_ - 3 < 0));$skip(40); 
  println("span: " + a.span(_ - 3 < 0));$skip(43); 
  println("exists: " + b.exists(_ == 100));$skip(41); 
  println("forall: " + b.forall(_ < 10));$skip(30); 
  println("zip :" + a.zip(b));$skip(30); 
  println("zip :" + b.zip(a));$skip(19); 
  val m = b.zip(a);System.out.println("""m  : scala.collection.immutable.Stream[(Int, Int)] = """ + $show(m ));$skip(29); 
  println("unzip" + m.unzip);$skip(54); 
  val k = List("Ramji", "sdfsdf", "SDfsdfsd", "aabb");System.out.println("""k  : List[String] = """ + $show(k ));$skip(20); 
  println(k.sorted);$skip(389); 

  //a.foldfoldLeft(b)(_ :: _)
  //a * b = b * a

  val a3: List[(String, List[(String, List[Int])], String, List[(String, Int)])] =
    List(
      ("abcd", List(("abcd1", List(1, 2)), ("abcd2", List(3, 4))), "mdff", List(("sdfsdsdfd1", 5), ("dgdfgdfgd", 6))),
      ("abcd", List(("abcd1", List(11, 22)), ("abcd2", List(33, 44))), "mdff", List(("sdfsdsdfd1", 55), ("dgdfgdfgd", 66))));System.out.println("""a3  : List[(String, List[(String, List[Int])], String, List[(String, Int)])] = """ + $show(a3 ));$skip(51); 

  println(a3.map(x => x._2.map(x => x._2.head)));$skip(35); val res$1 = 

  a3.map(x => x._2.head._2.head);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(71); 

  def f1(x: Int): Unit= {
    println(List(1 * x, 2 * x, 3 * x))
  };System.out.println("""f1: (x: Int)Unit""");$skip(47); 

  a3.map(x => x._2.head._2.head).foreach(f1);$skip(50); 

  val aMap = Map("a" -> "Apple" , "b" -> "Ball");System.out.println("""aMap  : scala.collection.immutable.Map[String,String] = """ + $show(aMap ))}


}
