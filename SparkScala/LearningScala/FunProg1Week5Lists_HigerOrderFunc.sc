object FunProg1Week5Lists_HigerOrderFunc {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  //pattern must be a value: FunProg1Week5Lists_HigerOrderFunc.Nil2[T] Note: if you intended to match against the class, try `case _: Nil2[_]`

  def map[U >: T, T](a: List[T], f: T => U): List[U] = a match {
    case Nil     => a
    case x :: xs => f(x) :: map(xs, f)
  }                                               //> map: [U >: T, T](a: List[T], f: T => U)List[U]

  map[Int, Int](List(1, 2, 3), (x => x * x))      //> res0: List[Int] = List(1, 4, 9)

  /** Complicate this stuff by trying to Wrap in a class */

  class List2[T](val a: List[T]) {
    def map[U >: T, T](f: T => U): List[U] = a match {
      case Nil     => a.asInstanceOf[List[U]]
      case x :: xs => f(x.asInstanceOf[T]) :: new List2[T](xs.asInstanceOf[List[T]]).map(f)
    }
  }

  val aList = new List2[Int](List(1, 2, 3, 4))    //> aList  : FunProg1Week5Lists_HigerOrderFunc.List2[Int] = FunProg1Week5Lists_H
                                                  //| igerOrderFunc$List2$1@506e6d5e
  aList.map[Int, Int](x => x * x)                 //> res1: List[Int] = List(1, 4, 9, 16)

  def squareList(xs: List[Int]): List[Int] = xs match {
    case Nil     => Nil
    case y :: ys => (y * y) :: squareList(ys)
  }                                               //> squareList: (xs: List[Int])List[Int]

  squareList(List(1, 2, 3, 4))                    //> res2: List[Int] = List(1, 4, 9, 16)

  def squareList2(xs: List[Int]): List[Int] = { xs map (x => x * x) }
                                                  //> squareList2: (xs: List[Int])List[Int]

  squareList2(List(1, 2, 3, 4))                   //> res3: List[Int] = List(1, 4, 9, 16)

  def posElems(xs: List[Int]): List[Int] = xs match {
    case Nil     => xs
    case y :: ys => if (y > 0) y :: posElems(ys) else posElems(ys)
  }                                               //> posElems: (xs: List[Int])List[Int]

  posElems(List(-1, 0, 1, 2, 3, 4, 5))            //> res4: List[Int] = List(1, 2, 3, 4, 5)
  def filter[T](xs: List[T], p: T => Boolean): List[T] = xs match {
    case Nil     => Nil
    case y :: ys => if (p(y)) y :: filter(ys, p) else filter(ys, p)
  }                                               //> filter: [T](xs: List[T], p: T => Boolean)List[T]
  filter[Int](List(-1, 0, 1, 2, 3, 4, 5), (_ > 0))//> res5: List[Int] = List(1, 2, 3, 4, 5)

  val xs = List(-1, 0, 1, 2, 3, 4, 5)             //> xs  : List[Int] = List(-1, 0, 1, 2, 3, 4, 5)

  /** List Functions */

  //Selects all elements of this list which satisfy a predicate.
  xs filter (_ > 0)                               //> res6: List[Int] = List(1, 2, 3, 4, 5)

  //Selects all elements of this list which do not satisfy a predicate.
  xs filterNot (_ > 0)                            //> res7: List[Int] = List(-1, 0)

  //Partitions this list in two lists according to a predicate.
  xs partition (_ > 0)                            //> res8: (List[Int], List[Int]) = (List(1, 2, 3, 4, 5),List(-1, 0))

  //Takes longest prefix of elements that satisfy a predicate.
  xs takeWhile (_ > 0)                            //> res9: List[Int] = List()
  xs takeWhile (_ < 0)                            //> res10: List[Int] = List(-1)

  //Drops longest prefix of elements that satisfy a predicate
  xs dropWhile (_ > 0)                            //> res11: List[Int] = List(-1, 0, 1, 2, 3, 4, 5)
  xs dropWhile (_ < 0)                            //> res12: List[Int] = List(0, 1, 2, 3, 4, 5)

  //Splits this list into a prefix/suffix pair according to a predicate.

  //Note: c span p is equivalent to (but possibly more efficient than) (c takeWhile p, c dropWhile p),

  xs span (_ > 0)                                 //> res13: (List[Int], List[Int]) = (List(),List(-1, 0, 1, 2, 3, 4, 5))

  /**
   * Write a function pack that packs consecutive duplicates of and elements of a list into sublists
   * pack(List("a","a","a","b","c","c","a")) returns List(List("a","a","a"),List("b")List("c","c"),List("a"))
   *
   *
   */

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil      => Nil
    case x :: xs1 => val (first, rest) = xs span (y => y == x); first :: pack(rest)

  }                                               //> pack: [T](xs: List[T])List[List[T]]

  pack(List("a", "a", "a", "b", "c", "c", "a"))   //> res14: List[List[String]] = List(List(a, a, a), List(b), List(c, c), List(a
                                                  //| ))

  /** Using pack write a function encode that produces the run-length encoding of a list*/

  def encode[T](xs: List[T]): List[(T, Int)] = {
    pack[T](xs).map(y => (y.head, y.length))
  }                                               //> encode: [T](xs: List[T])List[(T, Int)]

  encode(List("a", "a", "a", "b", "c", "c", "a")) //> res15: List[(String, Int)] = List((a,3), (b,1), (c,2), (a,1))

}