object FunProg1Week5Tuples {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet")
  import math.Ordering;$skip(51); 
  val pair = ("answer", 42);System.out.println("""pair  : (String, Int) = """ + $show(pair ));$skip(54); 

  //Match with Patterns

  val (key, value) = pair;System.out.println("""key  : String = """ + $show(key ));System.out.println("""value  : Int = """ + $show(value ));$skip(272); 

  // Let's rewrite merge function of merge Sort using Pattern match

  //Original One-

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  };System.out.println("""msort: (xs: List[Int])List[Int]""");$skip(283); 

  def merge(xs: List[Int], ys: List[Int]): List[Int] =
    xs match {
      case Nil => ys
      case x :: xs1 =>
        ys match {
          case Nil => xs
          case y :: ys1 =>
            if (x < y) x :: merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
    };System.out.println("""merge: (xs: List[Int], ys: List[Int])List[Int]""");$skip(465); 

  // Updated One

  def msort2(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge2(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys)            => ys
        case (xs, Nil)            => xs
        case (x :: xs1, y :: ys1) => if (x < y) x :: merge2(xs1, ys) else y :: merge2(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      merge2(msort2(fst), msort2(snd))
    }
  };System.out.println("""msort2: (xs: List[Int])List[Int]""");$skip(28); val res$0 = 

  msort(List(3, 1, 4, 2));System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(27); val res$1 = 
  msort2(List(3, 1, 4, 2));System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(662); 

  /**
   * Parameterization of Msort by msort[T] doesn't work,because the comparison < in merge is not defined for
   * arbitrary types T.
   * Let's also pass function "less than"
   */

  def msort3[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge3(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys)            => ys
        case (xs, Nil)            => xs
        case (x :: xs1, y :: ys1) => if (lt(x, y)) x :: merge3(xs1, ys) else y :: merge3(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      merge3(msort3(fst)(lt), msort3(snd)(lt))
    }
  };System.out.println("""msort3: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(46); val res$2 = 

  msort3(List(3, 1, 4, 2))((x, y) => x < y);System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(99); val res$3 = 
  msort3(List("Apple", "Banana", "Orange", "Guava"))((x: String, y: String) => x.compareTo(y) < 0);System.out.println("""res3: List[String] = """ + $show(res$3));$skip(502); 
  /** Use Ordering Class */

  def msort4[T](xs: List[T])(ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge4(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys)            => ys
        case (xs, Nil)            => xs
        case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge4(xs1, ys) else y :: merge4(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      merge4(msort4(fst)(ord), msort4(snd)(ord))
    }
  };System.out.println("""msort4: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]""");$skip(43); val res$4 = 

  msort4(List(3, 1, 4, 2))(Ordering.Int);System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(70); val res$5 = 
  msort4(List("Apple", "Banana", "Orange", "Guava"))(Ordering.String);System.out.println("""res5: List[String] = """ + $show(res$5));$skip(969); 

  /**
   * Use Ordering Class as Implicit Parameter
   *
   * Rules for Implicit Parameters
   * The compiler will search an implicit definition that
   * ▶ is marked implicit
   * ▶ has a type compatible with T
   * ▶ is visible at the point of the function call, or is defined in a
   * companion object associated with T.
   * If there is a single (most specific) definition, it will be taken as
   * actual argument for the implicit parameter.
   * Otherwise it’s an error.
   *
   *
   */

  def msort5[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge5(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys)            => ys
        case (xs, Nil)            => xs
        case (x :: xs1, y :: ys1) => if (ord.lt(x, y)) x :: merge5(xs1, ys) else y :: merge5(xs, ys1)
      }
      val (fst, snd) = xs splitAt n
      merge5(msort5(fst), msort5(snd))
    }
  };System.out.println("""msort5: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]""");$skip(29); val res$6 = 

  msort5(List(3, 1, 4, 2));System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(53); val res$7 = 
  msort5(List("Apple", "Banana", "Orange", "Guava"));System.out.println("""res7: List[String] = """ + $show(res$7))}

}
