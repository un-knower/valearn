object FunProg1Week3 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 
  println("Welcome to the Scala worksheet")

  abstract class IntSet {
    def incl(x: Int): IntSet
    def contains(x: Int): Boolean
    def union(other: IntSet): IntSet
  }

  object Empty extends IntSet {
    def contains(x: Int): Boolean = false
    def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
    override def toString = "."
    override def union(other: IntSet) = other
  }

  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
    def contains(x: Int): Boolean = {
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true
    }
    def incl(x: Int): IntSet = {
      if (x < elem) new NonEmpty(elem, left incl x, right)
      else if (x > elem) new NonEmpty(elem, left, right incl x)
      else this
    }
    override def toString = "{" + left + elem + right + "}"

    /* *********** Look at Union**************** */
    override def union(other: IntSet): IntSet = {
      //((left union other) union right) incl elem
      ((left union right) union other) incl elem
    }
  };$skip(1074); 

  val t1 = new NonEmpty(1, Empty, Empty) incl 2 incl 3;System.out.println("""t1  : FunProg1Week3.IntSet = """ + $show(t1 ));$skip(55); 
  val t2 = new NonEmpty(4, Empty, Empty) incl 5 incl 6;System.out.println("""t2  : FunProg1Week3.IntSet = """ + $show(t2 ));$skip(16); val res$0 = 

  t1 union t2

  /*
    				4											7
    		2				5		Union			6				8

    1				3

     ((2 union other) union 5) incl 4
     				 ((1 union other) union 3) incl 2  -- Label-1
     				 				 ((Empty union other) union Empty) incl 1  -->->  (other Union Empty)  incl 1										-->--> 8 inlc 6 incl 7 incl 1
     				 				 					 other																				 ((6 union Empty) union 8) incl 7  										-->--> 8 inlc 6 incl 7
     				 				 					 																											 ((Empty union Empty) union Empty) incl 6								 ((Empty union 8) union Empty) incl 6


    	Lable-1 :
    																																			8
    (8 inlc 6 incl 7 incl 1) union 3) incl 2					--->					6   				union 3 incl  2
    																														1				7
    																													 ((6 union 3) union 7) incl 8
    																													 				 ((1 union 3) union 7) incl 6  							--- > ((3 incl 1) union 7) incl 6  ---- > 3 incl 1 incl 7 incl 6
    																													 				 		 ((Empty union 3) union Empty) incl 1									(1 union Empty) union Empty incl 3

*/

  /*
		Cons List - Immutable Linked List

		Constructed from two building blocks-
		Nil  - The Empty List
		Cons - A Cell containing an elemen and remaining of the list


		*/
  /**
   *
   * Polymorphism means that a function type comes in many forms
   *
   * # Function can be applied to arguments of many type
   * # In Classes the type can have instances of many types.
   *
   * Two Prinicipal form of Polymorphism
   *
   * # Subtyping - Instance of a subclass can be passed to base class
   * # Generics - Instance of a function	 or class are created by type paramterization
   *
   */

  /**
   * Type Parameters for Class
   * Generics Classes
   */

  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
  }

  /*
	 val head : T defines parameter and field of Class at same time

	 (val head:T , val tail: T) is equivalent to

	 class Cons[T](_head: T, _tail: T) extends List[T] {
	  val head = _head
	  val tail = _tail
	 }

	 Also

	 While extending a trait we must implement all methods

	 val head:T is also an implementation of head function

	*/
  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
  }

  class Nil[T] extends List[T] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  };System.out.println("""res0: FunProg1Week3.IntSet = """ + $show(res$0));$skip(2592); 

  /**
   * Type Parameters to Function
   * Generic Functions
   *
   */

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]);System.out.println("""singleton: [T](elem: T)FunProg1Week3.Cons[T]""");$skip(22); val res$1 = 

  singleton[Int](1);System.out.println("""res1: FunProg1Week3.Cons[Int] = """ + $show(res$1));$skip(27); val res$2 = 
  singleton[Boolean](true);System.out.println("""res2: FunProg1Week3.Cons[Boolean] = """ + $show(res$2));$skip(15); val res$3 = 
  singleton(1);System.out.println("""res3: FunProg1Week3.Cons[Int] = """ + $show(res$3));$skip(18); val res$4 = 
  singleton(true);System.out.println("""res4: FunProg1Week3.Cons[Boolean] = """ + $show(res$4));$skip(341); 

  /* Write a function nth that takes an integer n and a list and selects the nth element of the list
  If index is outside the range then  IndexOutOfBounds exception is thrown*/

  def nth[T](n: Int, xs: List[T]): T = {

    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head else nth(n - 1, xs.tail)

  };System.out.println("""nth: [T](n: Int, xs: FunProg1Week3.List[T])T""");$skip(75); 

  val list = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Nil))));System.out.println("""list  : FunProg1Week3.Cons[Int] = """ + $show(list ));$skip(17); val res$5 = 

  nth(2, list);System.out.println("""res5: Int = """ + $show(res$5));$skip(15); val res$6 = 
  nth(7, list);System.out.println("""res6: Int = """ + $show(res$6));$skip(1173); 

  /** Subtyping, Generics , Covariance */

  /**
   * Type Bounds
   * One might want to express that assertAllPos takes Empty sets to
   * Empty sets and NonEmpty sets to NonEmpty sets.
   * A way to express this is:
   * def assertAllPos[S <: IntSet](r: S): S = ...
   * Here, “<: IntSet” is an upper bound of the type parameter S:
   * It means that S can be instantiated only to types that conform to
   * IntSet.
   * Generally, the notation
   * ▶ S <: T means: S is a subtype of T, and
   * ▶ S >: T means: S is a supertype of T, or T is a subtype of S.
   *
   *
   *
   * You can also use a lower bound for a type variable.
   * Example
   * [S >: NonEmpty]
   * introduces a type parameter S that can range only over supertypes
   * of NonEmpty.
   * So S could be one of NonEmpty, IntSet, AnyRef, or Any.
   *
   *
   * Finally, it is also possible to mix a lower bound with an upper bound.
   * For instance,
   * [S >: NonEmpty <: IntSet]
   * would restrict S any type on the interval between NonEmpty and
   * IntSet.
   */

  // Function which takes two parameters and defines s as supertype

  def map[U >: T, T](a: List[T], f: T => U): List[U] = ???;System.out.println("""map: [U >: T, T](a: FunProg1Week3.List[T], f: T => U)FunProg1Week3.List[U]""")}

  /**
   *
   *
   * When to use an abstract class in Scala
   * By Alvin Alexander. Last updated: September 5 2016
   *
   * This is an excerpt from the Scala Cookbook (partially modified for the internet). This is Recipe 4.12, “When to use an abstract class in Scala.”
   * Problem
   *
   * Scala has traits, and a trait is more flexible than an abstract class, so you wonder, “When should I use an abstract class?”
   * Solution
   *
   * There are two main reasons to use an abstract class in Scala:
   *
   * You want to create a base class that requires constructor arguments.
   * The code will be called from Java code.
   *
   * Regarding the first reason, traits don’t allow constructor parameters:
   *
   * // this won't compile
   * trait Animal(name: String)
   *
   * So, use an abstract class whenever a base behavior must have constructor parameters:
   *
   * abstract class Animal(name: String)
   *
   * Regarding the second reason, if you’re writing code that needs to be accessed from Java, you’ll find that Scala traits with implemented methods can’t be called from Java code. If you run into this situation, see Recipe 17.7, “Wrapping Traits with Implementations”, for solutions to that problem.
   * Discussion
   *
   * Use an abstract class instead of a trait when the base functionality must take constructor parameters. However, be aware that a class can extend only one abstract class.
   *
   * Abstract classes work just like Java in that you can define some methods that have complete implementations, and other methods that have no implementation and are therefore abstract. To declare that a method is abstract, just leave the body of the method undefined:
   *
   * def speak   // no body makes the method abstract
   *
   * There is no need for an abstract keyword; simply leaving the body of the method undefined makes it abstract. This is consistent with how abstract methods in traits are defined.
   *
   * In the following example the methods save, update, and delete are defined in the abstract class BaseController, but the methods connect, getStatus, and setServerName have no method body, and are therefore abstract:
   *
   * abstract class BaseController(db: Database) {
   * def save { db.save }
   * def update { db.update }
   * def delete { db.delete }
   *
   * // abstract
   * def connect
   *
   * // an abstract method that returns a String
   * def getStatus: String
   *
   * // an abstract method that takes a parameter
   * def setServerName(serverName: String)
   * }
   *
   * When a class extends the BaseController class, it must implement the connect, getStatus, and setServerName methods, or be declared abstract. Attempting to extend BaseController without implementing those methods yields a “class needs to be abstract” error, as shown in the REPL:
   *
   * scala> class WidgetController(db: Database) extends BaseController(db)
   *
   * <console>:9: error: class WidgetController needs to be abstract, since:
   * method setServerName in class BaseController of type (serverName: String)Unit is not defined
   * method getStatus in class BaseController of type => String is not defined
   * method connect in class BaseController of type => Unit is not defined
   * class WidgetController(db: Database) extends BaseController(db)
   * ^
   *
   * Because a class can extend only one abstract class, when you’re trying to decide whether to use a trait or abstract class, always use a trait, unless you have this specific need to have constructor arguments in your base implementation.
   */

}
