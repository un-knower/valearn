object RTailRecursion {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 
  println("Welcome to the Scala worksheet");$skip(68); 

  /** Cartesion Product of Two Lists */

  val a = List(1, 2, 3);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(24); 
  val b = List(4, 5, 6);System.out.println("""b  : List[Int] = """ + $show(b ));$skip(373); 

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
  };System.out.println("""Multi: (a: List[Int], b: List[Int])List[Int]""");$skip(16); val res$0 = 

  Multi(a, b);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(135); 

  /** Normal sum vs TailRecursive Sum */

  def sum(a: Stream[Int]): Int = {
    if (a.isEmpty) 0
    else a.head + sum(a.tail)
  };System.out.println("""sum: (a: Stream[Int])Int""");$skip(177); 

  def sumTail(a: Stream[Int]): Int = {
    def loop(a: Stream[Int], acc: Int): Int = {
      if (a.isEmpty) acc
      else loop(a.tail, acc + a.head)
    }
    loop(a, 0)
  };System.out.println("""sumTail: (a: Stream[Int])Int""");$skip(47); 

  println(sumTail((1 to 10000000).toStream));$skip(39); 
  println(sum((1 to 100000).toStream))}

}
