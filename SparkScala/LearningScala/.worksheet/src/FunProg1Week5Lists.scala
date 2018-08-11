object FunProg1Week5Lists {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(71); 
  println("Welcome to the Scala worksheet");$skip(51); 

  val fruit = List("apples", "oranges", "pears");System.out.println("""fruit  : List[String] = """ + $show(fruit ));$skip(32); 

  val nums = List(1, 2, 3, 4);System.out.println("""nums  : List[Int] = """ + $show(nums ));$skip(66); 

  val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1));System.out.println("""diag3  : List[List[Int]] = """ + $show(diag3 ));$skip(23); 

  val empty = List();System.out.println("""empty  : List[Nothing] = """ + $show(empty ));$skip(324); 

  /**
   * The List type Like arrays, lists are homogeneous: the elements of a list all have the same type.
   * The type of a list that has elements of type T is written List[T]. For instance, here are the same four lists with explicit types added:
   */

  val fruit2: List[String] = List("apples", "oranges", "pears");System.out.println("""fruit2  : List[String] = """ + $show(fruit2 ));$skip(44); 

  val nums2: List[Int] = List(1, 2, 3, 4);System.out.println("""nums2  : List[Int] = """ + $show(nums2 ));$skip(84); 

  val diag32: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1));System.out.println("""diag32  : List[List[Int]] = """ + $show(diag32 ));$skip(39); 

  val empty2: List[Nothing] = List();System.out.println("""empty2  : List[Nothing] = """ + $show(empty2 ));$skip(496); 

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

  val fruit3 = "apples" :: ("oranges" :: ("pears" :: Nil));System.out.println("""fruit3  : List[String] = """ + $show(fruit3 ));$skip(46); 

  val nums3 = 1 :: (2 :: (3 :: (4 :: Nil)));System.out.println("""nums3  : List[Int] = """ + $show(nums3 ));$skip(105); 

  val diag33 = (1 :: (0 :: (0 :: Nil))) :: (0 :: (1 :: (0 :: Nil))) :: (0 :: (0 :: (1 :: Nil))) :: Nil;System.out.println("""diag33  : List[List[Int]] = """ + $show(diag33 ));$skip(21); 

  val empty3 = Nil;System.out.println("""empty3  : scala.collection.immutable.Nil.type = """ + $show(empty3 ));$skip(481); 

  /**
   * In fact the previous definitions of fruit, nums, diag3, and empty in terms of List(...) are just wrappers that expand to these definitions.
   * For instance, List(1, 2, 3) creates the list 1 :: (2 :: (3 :: Nil)). Because it ends in a colon,
   * the :: operation associates to the right: A :: B :: C is interpreted as A :: (B :: C).
   * Therefore, you can drop the parentheses in the previous definitions. For instance:
   */

  val nums4 = 1 :: 2 :: 3 :: 4 :: Nil;System.out.println("""nums4  : List[Int] = """ + $show(nums4 ));$skip(280); 

  // Operators ending in ":" Assosicate to Right.

  //A :: B :: C is interpreted as A :: ( B :: C )

  //Operators ening in ":" are also different as they are seen as method calls of Right hand operand.

  //So the expression -

  val nums5 = 1 :: (2 :: (3 :: (4 :: Nil)));System.out.println("""nums5  : List[Int] = """ + $show(nums5 ));$skip(57); val res$0 = 

  //is equivalent to -

  Nil.::(4).::(3).::(2).::(1);System.out.println("""res0: List[Int] = """ + $show(res$0));$skip(415); val res$1 = 

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

  fruit.isEmpty;System.out.println("""res1: Boolean = """ + $show(res$1));$skip(15); val res$2 = 

  fruit.head;System.out.println("""res2: String = """ + $show(res$2));$skip(20); val res$3 = 

  fruit.tail.head;System.out.println("""res3: String = """ + $show(res$3));$skip(15); val res$4 = 

  diag3.head;System.out.println("""res4: List[Int] = """ + $show(res$4));$skip(315); 

  /**
   * List patterns
   * List patterns correspond one-by-one to list expressions. You can either match on all elements of a list using a pattern of the form List(...),
   * or you take lists apart bit by bit using patterns composed from the :: operator and the Nil constant
   */
  val List(a, b, c) = fruit;System.out.println("""a  : String = """ + $show(a ));System.out.println("""b  : String = """ + $show(b ));System.out.println("""c  : String = """ + $show(c ));$skip(353); 
  /**
   * The pattern List(a, b, c) matches lists of length 3, and binds the three elements to the pattern variables a, b, and c.
   * If you don’t know the number of list elements beforehand, it’s better to match with :: instead. For instance,
   * the pattern a :: b :: rest matches lists of length 2 or greater:
   */

  val x :: y :: rest = fruit;System.out.println("""x  : String = """ + $show(x ));System.out.println("""y  : String = """ + $show(y ));System.out.println("""rest  : List[String] = """ + $show(rest ));$skip(207); val res$5 = 

  //List Pattern Matching Example
  //1 :: 2 :: xs // Matches List with first 2 elements with 1 & 2

  List(1, 2, 3) match {
    case 1 :: 2 :: xs => "Found Match"
    case _            => "No Match"
  };System.out.println("""res5: String = """ + $show(res$5));$skip(134); val res$6 = 

  //x :: Nil // Matches List of Length 1

  List(1) match {
    case x :: Nil => "Found Match"
    case _        => "No Match"
  };System.out.println("""res6: String = """ + $show(res$6));$skip(286); val res$7 = 

  //List(x) /// Same as x :: Nil

  //List() /// Empty list, same as Nil

  //List(2 :: xs) // A list of single Element.. That element is List which starts with 2 */

  List(List(2, 3, 4, 5)) match {
    case List(2 :: xs) => "Found Match"
    case _             => "No Match"
  };System.out.println("""res7: String = """ + $show(res$7));$skip(782); 

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
    else insert(xs.head, isort(xs.tail));System.out.println("""isort: (xs: List[Int])List[Int]""");$skip(134); 

  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail);System.out.println("""insert: (x: Int, xs: List[Int])List[Int]""");$skip(180); 

  /**  Insertion Sort with Pattern Matching */

  def isort2(xs: List[Int]): List[Int] = xs match {
    case List()   => List()
    case x :: xs1 => insert2(x, isort2(xs1))
  };System.out.println("""isort2: (xs: List[Int])List[Int]""");$skip(161); 
  def insert2(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs
    else y :: insert2(x, ys)
  };System.out.println("""insert2: (x: Int, xs: List[Int])List[Int]""");$skip(28); val res$8 = 

  isort(List(3, 1, 4, 2));System.out.println("""res8: List[Int] = """ + $show(res$8));$skip(27); val res$9 = 
  isort2(List(3, 1, 4, 2));System.out.println("""res9: List[Int] = """ + $show(res$9));$skip(53); 

  val xs: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8);System.out.println("""xs  : List[Int] = """ + $show(xs ));$skip(34); 
  val ys: List[Int] = List(9, 10);System.out.println("""ys  : List[Int] = """ + $show(ys ));$skip(12); val res$10 = 
  xs.length;System.out.println("""res10: Int = """ + $show(res$10));$skip(10); val res$11 = 
  xs.last;System.out.println("""res11: Int = """ + $show(res$11));$skip(10); val res$12 = 
  xs.init;System.out.println("""res12: List[Int] = """ + $show(res$12));$skip(12); val res$13 = 
  xs take 2;System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(12); val res$14 = 
  xs drop 2;System.out.println("""res14: List[Int] = """ + $show(res$14));$skip(8); val res$15 = 
  xs(2);System.out.println("""res15: Int = """ + $show(res$15));$skip(11); val res$16 = 
  xs ++ ys;System.out.println("""res16: List[Int] = """ + $show(res$16));$skip(13); val res$17 = 
  xs.reverse;System.out.println("""res17: List[Int] = """ + $show(res$17));$skip(21); val res$18 = 
  xs updated (2, 22);System.out.println("""res18: List[Int] = """ + $show(res$18));$skip(15); val res$19 = 
  xs indexOf 2;System.out.println("""res19: Int = """ + $show(res$19));$skip(16); val res$20 = 
  xs contains 3;System.out.println("""res20: Boolean = """ + $show(res$20));$skip(12); val res$21 = 
  xs ::: ys;System.out.println("""res21: List[Int] = """ + $show(res$21));$skip(11); val res$22 = 
  xs :: ys;System.out.println("""res22: List[Any] = """ + $show(res$22));$skip(11); val res$23 = 
  xs :+ 11;System.out.println("""res23: List[Int] = """ + $show(res$23));$skip(11); val res$24 = 
  11 +: xs;System.out.println("""res24: List[Int] = """ + $show(res$24));$skip(158); 

  def last[T](xs: List[T]): T = xs match {
    case List()  => throw new Error("Last of Empty List")
    case List(x) => x
    case y :: ys => last(ys)
  };System.out.println("""last: [T](xs: List[T])T""");$skip(174); 

  def init[T](xs: List[T]): List[T] = xs match {
    case List()  => throw new Error("init of Empty List")
    case List(x) => List()
    case y :: ys => y :: init(ys)
  };System.out.println("""init: [T](xs: List[T])List[T]""");$skip(135); 

  def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
    case List()  => ys
    case z :: zs => z :: concat(zs, ys)

  };System.out.println("""concat: [T](xs: List[T], ys: List[T])List[T]""");$skip(128); 

  def reverse[T](xs: List[T]): List[T] = xs match {
    case List()  => List()
    case y :: ys => reverse(ys) ++ List(y)
  };System.out.println("""reverse: [T](xs: List[T])List[T]""");$skip(88); 

  def removeAt[T](n: Int, xs: List[T]): List[T] = { (xs take n) ::: (xs drop n + 1) };System.out.println("""removeAt: [T](n: Int, xs: List[T])List[T]""");$skip(209); 

  def flatten(xs: List[Any]): List[Any] = xs match {
    case List()               => List()
    case (z: List[Any]) :: zs => flatten(z) ::: flatten(zs)
    case z :: zs              => z :: flatten(zs)
  };System.out.println("""flatten: (xs: List[Any])List[Any]""");$skip(59); val res$25 = 

  flatten(
    List(List(1, 1), 2, List(3, List(5, 8))));System.out.println("""res25: List[Any] = """ + $show(res$25));$skip(54); val res$26 = 

  // splitAt
  List(3, 1, 2, 4, 7, 5, 6).splitAt(3);System.out.println("""res26: (List[Int], List[Int]) = """ + $show(res$26));$skip(429); 

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
    };System.out.println("""merge: (xs: List[Int], ys: List[Int])List[Int]""");$skip(28); val res$27 = 

  msort(List(3, 1, 4, 2));System.out.println("""res27: List[Int] = """ + $show(res$27));$skip(88); 

  /**
   * Let's break down it using Tuple Syntax
   */

  val pair = ("answer", 42);System.out.println("""pair  : (String, Int) = """ + $show(pair ));$skip(54); 

  //Match with Patterns

  val (key, value) = pair;System.out.println("""key  : String = """ + $show(key ));System.out.println("""value  : Int = """ + $show(value ));$skip(535); 

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
  };System.out.println("""msort2: (xs: List[Int])List[Int]""");$skip(28); val res$28 = 

  msort(List(3, 1, 4, 2));System.out.println("""res28: List[Int] = """ + $show(res$28));$skip(27); val res$29 = 
  msort2(List(3, 1, 4, 2));System.out.println("""res29: List[Int] = """ + $show(res$29));$skip(662); 

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
  };System.out.println("""msort3: [T](xs: List[T])(lt: (T, T) => Boolean)List[T]""");$skip(46); val res$30 = 

  msort3(List(3, 1, 4, 2))((x, y) => x < y);System.out.println("""res30: List[Int] = """ + $show(res$30));$skip(99); val res$31 = 
  msort3(List("Apple", "Banana", "Orange", "Guava"))((x: String, y: String) => x.compareTo(y) < 0)
  /** Use Ordering Class */

  /// Import Ordering Class

  import math.Ordering;System.out.println("""res31: List[String] = """ + $show(res$31));$skip(557); 

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
  };System.out.println("""msort4: [T](xs: List[T])(ord: scala.math.Ordering[T])List[T]""");$skip(43); val res$32 = 

  msort4(List(3, 1, 4, 2))(Ordering.Int);System.out.println("""res32: List[Int] = """ + $show(res$32));$skip(70); val res$33 = 
  msort4(List("Apple", "Banana", "Orange", "Guava"))(Ordering.String);System.out.println("""res33: List[String] = """ + $show(res$33));$skip(969); 

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
  };System.out.println("""msort5: [T](xs: List[T])(implicit ord: scala.math.Ordering[T])List[T]""");$skip(29); val res$34 = 

  msort5(List(3, 1, 4, 2));System.out.println("""res34: List[Int] = """ + $show(res$34));$skip(53); val res$35 = 
  msort5(List("Apple", "Banana", "Orange", "Guava"));System.out.println("""res35: List[String] = """ + $show(res$35))}

}
