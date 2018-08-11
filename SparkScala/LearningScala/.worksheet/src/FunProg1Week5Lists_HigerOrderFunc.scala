object FunProg1Week5Lists_HigerOrderFunc {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(86); 
  println("Welcome to the Scala worksheet");$skip(277); 

  //pattern must be a value: FunProg1Week5Lists_HigerOrderFunc.Nil2[T] Note: if you intended to match against the class, try `case _: Nil2[_]`

  def map[U >: T, T](a: List[T], f: T => U): List[U] = a match {
    case Nil     => a
    case x :: xs => f(x) :: map(xs, f)
  };System.out.println("""map: [U >: T, T](a: List[T], f: T => U)List[U]""");$skip(47); val res$0 = 

  map[Int, Int](List(1, 2, 3), (x => x * x))

  /** Complicate this stuff by trying to Wrap in a class */

  class List2[T](val a: List[T]) {
    def map[U >: T, T](f: T => U): List[U] = a match {
      case Nil     => a.asInstanceOf[List[U]]
      case x :: xs => f(x.asInstanceOf[T]) :: new List2[T](xs.asInstanceOf[List[T]]).map(f)
    }
  };System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(351); 

  val aList = new List2[Int](List(1, 2, 3, 4));System.out.println("""aList  : FunProg1Week5Lists_HigerOrderFunc.List2[Int] = """ + $show(aList ));$skip(34); val res$1 = 
  aList.map[Int, Int](x => x * x);System.out.println("""res1: List[Int] = """ + $show(res$1));$skip(132); 

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil     => Nil
    case y :: ys => (y * y) :: squareList(ys)
  };System.out.println("""squareList: (xs: List[Int])List[Int]""");$skip(33); val res$2 = 

  squareList(List(1, 2, 3, 4));System.out.println("""res2: List[Int] = """ + $show(res$2));$skip(72); 

  def squareList2(xs: List[Int]): List[Int] = { xs map (x => x * x) };System.out.println("""squareList2: (xs: List[Int])List[Int]""");$skip(34); val res$3 = 

  squareList2(List(1, 2, 3, 4));System.out.println("""res3: List[Int] = """ + $show(res$3));$skip(150); 

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil     => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  };System.out.println("""posElems: (xs: List[Int])List[Int]""");$skip(41); val res$4 = 

  posElems(List(-1, 0, 1, 2, 3, 4, 5));System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(164); 
  def filter[T](xs: List[T], p: T => Boolean): List[T] = xs match {
    case Nil     => Nil
    case y :: ys => if (p(y)) y :: filter(ys, p) else filter(ys, p)
  };System.out.println("""filter: [T](xs: List[T], p: T => Boolean)List[T]""");$skip(51); val res$5 = 
  filter[Int](List(-1, 0, 1, 2, 3, 4, 5), (_ > 0));System.out.println("""res5: List[Int] = """ + $show(res$5));$skip(40); 

  val xs = List(-1, 0, 1, 2, 3, 4, 5);System.out.println("""xs  : List[Int] = """ + $show(xs ));$skip(113); val res$6 = 

  /** List Functions */

  //Selects all elements of this list which satisfy a predicate.
  xs filter (_ > 0);System.out.println("""res6: List[Int] = """ + $show(res$6));$skip(97); val res$7 = 

  //Selects all elements of this list which do not satisfy a predicate.
  xs filterNot (_ > 0);System.out.println("""res7: List[Int] = """ + $show(res$7));$skip(89); val res$8 = 

  //Partitions this list in two lists according to a predicate.
  xs partition (_ > 0);System.out.println("""res8: (List[Int], List[Int]) = """ + $show(res$8));$skip(88); val res$9 = 

  //Takes longest prefix of elements that satisfy a predicate.
  xs takeWhile (_ > 0);System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(23); val res$10 = 
  xs takeWhile (_ < 0);System.out.println("""res10: List[Int] = """ + $show(res$10));$skip(87); val res$11 = 

  //Drops longest prefix of elements that satisfy a predicate
  xs dropWhile (_ > 0);System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(23); val res$12 = 
  xs dropWhile (_ < 0);System.out.println("""res12: List[Int] = """ + $show(res$12));$skip(200); val res$13 = 

  //Splits this list into a prefix/suffix pair according to a predicate.

  //Note: c span p is equivalent to (but possibly more efficient than) (c takeWhile p, c dropWhile p),

  xs span (_ > 0);System.out.println("""res13: (List[Int], List[Int]) = """ + $show(res$13));$skip(407); 

  /**
   * Write a function pack that packs consecutive duplicates of and elements of a list into sublists
   * pack(List("a","a","a","b","c","c","a")) returns List(List("a","a","a"),List("b")List("c","c"),List("a"))
   *
   *
   */

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: xs1 => val (first, rest) = xs span (y => y == x); first :: pack(rest)

  };System.out.println("""pack: [T](xs: List[T])List[List[T]]""");$skip(50); val res$14 = 

  pack(List("a", "a", "a", "b", "c", "c", "a"));System.out.println("""res14: List[List[String]] = """ + $show(res$14));$skip(193); 

  /** Using pack write a function encode that produces the run-length encoding of a list*/

  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack[T](xs).map(y => (y.head, y.length))
  };System.out.println("""encode: [T](xs: List[T])List[(T, Int)]""");$skip(52); val res$15 = 

  encode(List("a", "a", "a", "b", "c", "c", "a"));System.out.println("""res15: List[(String, Int)] = """ + $show(res$15))}

}
