object FunProg1Week5Lists {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val fruit = List("apples", "oranges", "pears")  //> fruit  : List[String] = List(apples, oranges, pears)

  val nums = List(1, 2, 3, 4)                     //> nums  : List[Int] = List(1, 2, 3, 4)

  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
                                                  //> diag3  : List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
                                                  //| 

  val empty = List()                              //> empty  : List[Nothing] = List()

  /**
   * The List type Like arrays, lists are homogeneous: the elements of a list all have the same type.
   * The type of a list that has elements of type T is written List[T]. For instance, here are the same four lists with explicit types added:
   */

  val fruit2: List[String] = List("apples", "oranges", "pears")
                                                  //> fruit2  : List[String] = List(apples, oranges, pears)

  val nums2: List[Int] = List(1, 2, 3, 4)         //> nums2  : List[Int] = List(1, 2, 3, 4)

  val diag32: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
                                                  //> diag32  : List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1)
                                                  //| )

  val empty2: List[Nothing] = List()              //> empty2  : List[Nothing] = List()

  /**
   * Constructing lists
   * All lists are built from two fundamental building blocks,
   *
   * Nil and
   *
   * :: (pronounced “cons”).
   *
   * Nil represents the empty list. The infix operator, ::, expresses list extension at the front. That is, x :: xs represents a list whose first element is x,
   * followed by (the elements of) list xs. Hence, the previous list values could also have been defined as follows:
   */

  val fruit3 = "apples" :: ("oranges" :: ("pears" :: Nil))
                                                  //> fruit3  : List[String] = List(apples, oranges, pears)

  val nums3 = 1 :: (2 :: (3 :: (4 :: Nil)))       //> nums3  : List[Int] = List(1, 2, 3, 4)

  val diag33 = (1 :: (0 :: (0 :: Nil))) :: (0 :: (1 :: (0 :: Nil))) :: (0 :: (0 :: (1 :: Nil))) :: Nil
                                                  //> diag33  : List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1
                                                  //| ))

  val empty3 = Nil                                //> empty3  : scala.collection.immutable.Nil.type = List()

  /**
   * In fact the previous definitions of fruit, nums, diag3, and empty in terms of List(...) are just wrappers that expand to these definitions.
   * For instance, List(1, 2, 3) creates the list 1 :: (2 :: (3 :: Nil)). Because it ends in a colon,
   * the :: operation associates to the right: A :: B :: C is interpreted as A :: (B :: C).
   * Therefore, you can drop the parentheses in the previous definitions. For instance:
   */

  val nums4 = 1 :: 2 :: 3 :: 4 :: Nil             //> nums4  : List[Int] = List(1, 2, 3, 4)

  // Operators ending in ":" Assosicate to Right.

  //A :: B :: C is interpreted as A :: ( B :: C )

  //Operators ening in ":" are also different as they are seen as method calls of Right hand operand.

  //So the expression -

  val nums5 = 1 :: (2 :: (3 :: (4 :: Nil)))       //> nums5  : List[Int] = List(1, 2, 3, 4)

  //is equivalent to -

  Nil.::(4).::(3).::(2).::(1)                     //> res0: List[Int] = List(1, 2, 3, 4)

  /**
   * Basic operations on lists
   * All operations on lists can be expressed in terms of the following three:
   *
   * head returns the first element of a list
   *
   * tail returns a list consisting of all elements except the first
   *
   * isEmpty returns true if the list is empty
   *
   * Table 16.1 · Basic list operations What it is What it does empty.isEmpty returns true
   */

  fruit.isEmpty                                   //> res1: Boolean = false

  fruit.head                                      //> res2: String = apples

  fruit.tail.head                                 //> res3: String = oranges

  diag3.head                                      //> res4: List[Int] = List(1, 0, 0)

  /**
   * List patterns
   * List patterns correspond one-by-one to list expressions. You can either match on all elements of a list using a pattern of the form List(...),
   * or you take lists apart bit by bit using patterns composed from the :: operator and the Nil constant
   */
  val List(a, b, c) = fruit                       //> a  : String = apples
                                                  //| b  : String = oranges
                                                  //| c  : String = pears
  /**
   * The pattern List(a, b, c) matches lists of length 3, and binds the three elements to the pattern variables a, b, and c.
   * If you don’t know the number of list elements beforehand, it’s better to match with :: instead. For instance,
   * the pattern a :: b :: rest matches lists of length 2 or greater:
   */

  val x :: y :: rest = fruit                      //> x  : String = apples
                                                  //| y  : String = oranges
                                                  //| rest  : List[String] = List(pears)

  //List Pattern Matching Example
  //1 :: 2 :: xs // Matches List with first 2 elements with 1 & 2

  List(1, 2, 3) match {
    case 1 :: 2 :: xs => "Found Match"
    case _            => "No Match"
  }                                               //> res5: String = Found Match

  //x :: Nil // Matches List of Length 1

  List(1) match {
    case x :: Nil => "Found Match"
    case _        => "No Match"
  }                                               //> res6: String = Found Match

  //List(x) /// Same as x :: Nil

  //List() /// Empty list, same as Nil

  //List(2 :: xs) // A list of single Element.. That element is List which starts with 2 */

  List(List(2, 3, 4, 5)) match {
    case List(2 :: xs) => "Found Match"
    case _             => "No Match"
  }                                               //> res7: String = Found Match

/*** Sorting Lists */
  /**
   * Insertion Sort
   *
   * #1- Sort List (7,3,9,2) is to sort tail List(3,9,2) to obtain List(2,3,9)
   * #2-  Next step is to insert the head 7 in the right place to obtain the result List(2,3,7,9)
   *
   *
   */
  /**
   * Suppose there exists a function called Insert designed to insert a value into a sorted sequence at the beginning of an array.
   * It operates by beginning at the end of the sequence and shifting each element one place to the right until a suitable position is found for the new element.
   * The function has the side effect of overwriting the value stored immediately after the sorted sequence in the array.
   */

  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))          //> isort: (xs: List[Int])List[Int]

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)            //> insert: (x: Int, xs: List[Int])List[Int]

  /**  Insertion Sort with Pattern Matching */

  def isort2(xs: List[Int]): List[Int] = xs match {
    case List()   => List()
    case x :: xs1 => insert2(x, isort2(xs1))
  }                                               //> isort2: (xs: List[Int])List[Int]
  def insert2(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
    else y :: insert2(x, ys)
  }                                               //> insert2: (x: Int, xs: List[Int])List[Int]

  isort(List(3, 1, 4, 2))                         //> res8: List[Int] = List(1, 2, 3, 4)
  isort2(List(3, 1, 4, 2))                        //> res9: List[Int] = List(1, 2, 3, 4)

  val xs: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)//> xs  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
  val ys: List[Int] = List(9, 10)                 //> ys  : List[Int] = List(9, 10)
  xs.length                                       //> res10: Int = 8
  xs.last                                         //> res11: Int = 8
  xs.init                                         //> res12: List[Int] = List(1, 2, 3, 4, 5, 6, 7)
  xs take 2                                       //> res13: List[Int] = List(1, 2)
  xs drop 2                                       //> res14: List[Int] = List(3, 4, 5, 6, 7, 8)
  xs(2)                                           //> res15: Int = 3
  xs ++ ys                                        //> res16: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  xs.reverse                                      //> res17: List[Int] = List(8, 7, 6, 5, 4, 3, 2, 1)
  xs updated (2, 22)                              //> res18: List[Int] = List(1, 2, 22, 4, 5, 6, 7, 8)
  xs indexOf 2                                    //> res19: Int = 1
  xs contains 3                                   //> res20: Boolean = true
  xs ::: ys                                       //> res21: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  xs :: ys                                        //> res22: List[Any] = List(List(1, 2, 3, 4, 5, 6, 7, 8), 9, 10)
  xs :+ 11                                        //> res23: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 11)
  11 +: xs                                        //> res24: List[Int] = List(11, 1, 2, 3, 4, 5, 6, 7, 8)

  def last[T](xs: List[T]): T = xs match {
    case List()  => throw new Error("Last of Empty List")
    case List(x) => x
    case y :: ys => last(ys)
  }                                               //> last: [T](xs: List[T])T

  def init[T](xs: List[T]): List[T] = xs match {
    case List()  => throw new Error("init of Empty List")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  }                                               //> init: [T](xs: List[T])List[T]

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List()  => ys
    case z :: zs => z :: concat(zs, ys)

  }                                               //> concat: [T](xs: List[T], ys: List[T])List[T]

  def reverse[T](xs: List[T]): List[T] = xs match {
    case List()  => List()
    case y :: ys => reverse(ys) ++ List(y)
  }                                               //> reverse: [T](xs: List[T])List[T]

  def removeAt[T](n: Int, xs: List[T]): List[T] = { (xs take n) ::: (xs drop n + 1) }
                                                  //> removeAt: [T](n: Int, xs: List[T])List[T]

  def flatten(xs: List[Any]): List[Any] = xs match {
    case List()               => List()
    case (z: List[Any]) :: zs => flatten(z) ::: flatten(zs)
    case z :: zs              => z :: flatten(zs)
  }                                               //> flatten: (xs: List[Any])List[Any]

  flatten(
    List(List(1, 1), 2, List(3, List(5, 8))))     //> res25: List[Any] = List(1, 1, 2, 3, 5, 8)

  // splitAt
  List(3, 1, 2, 4, 7, 5, 6).splitAt(3)            //> res26: (List[Int], List[Int]) = (List(3, 1, 2),List(4, 7, 5, 6))

  /**
   * Merge Sort
   * ▶ Separate the list into two sub-lists, each containing around half
   * of the elements of the original list.
   * ▶ Sort the two sub-lists.
   * ▶ Merge the two sorted sub-lists into a single sorted list.
   *
   */

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }                                               //> msort: (xs: List[Int])List[Int]

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
    }                                             //> merge: (xs: List[Int], ys: List[Int])List[Int]

  msort(List(3, 1, 4, 2))                         //> res27: List[Int] = List(1, 2, 3, 4)

  /**
   * Let's break down it using Tuple Syntax
   */

  val pair = ("answer", 42)                       //> pair  : (String, Int) = (answer,42)

  //Match with Patterns

  val (key, value) = pair                         //> key  : String = answer
                                                  //| value  : Int = 42

  // Let's rewrite merge function of merge Sort using Pattern match

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
  }                                               //> msort2: (xs: List[Int])List[Int]

  msort(List(3, 1, 4, 2))                         //> res28: List[Int] = List(1, 2, 3, 4)
  msort2(List(3, 1, 4, 2))                        //> res29: List[Int] = List(1, 2, 3, 4)

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
  }                                               //> msort3: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]

  msort3(List(3, 1, 4, 2))((x, y) => x < y)       //> res30: List[Int] = List(1, 2, 3, 4)
  msort3(List("Apple", "Banana", "Orange", "Guava"))((x: String, y: String) => x.compareTo(y) < 0)
                                                  //> res31: List[String] = List(Apple, Banana, Guava, Orange)
  /** Use Ordering Class */

  /// Import Ordering Class

  import math.Ordering

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
  }                                               //> msort4: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]

  msort4(List(3, 1, 4, 2))(Ordering.Int)          //> res32: List[Int] = List(1, 2, 3, 4)
  msort4(List("Apple", "Banana", "Orange", "Guava"))(Ordering.String)
                                                  //> res33: List[String] = List(Apple, Banana, Guava, Orange)

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
  }                                               //> msort5: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]

  msort5(List(3, 1, 4, 2))                        //> res34: List[Int] = List(1, 2, 3, 4)
  msort5(List("Apple", "Banana", "Orange", "Guava"))
                                                  //> res35: List[String] = List(Apple, Banana, Guava, Orange)

}