object ClassHierarchy {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 
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
    override def union(other: IntSet): IntSet = {
      ((left union other) union right) incl elem
    }
  };$skip(990); 

  val t1 = new NonEmpty(3, Empty, Empty) incl 4 incl 5 incl 6 incl 7 incl 8;System.out.println("""t1  : ClassHierarchy.IntSet = """ + $show(t1 ));$skip(76); 
  val t2 = new NonEmpty(3, Empty, Empty) incl 4 incl 5 incl 6 incl 7 incl 8;System.out.println("""t2  : ClassHierarchy.IntSet = """ + $show(t2 ));$skip(16); val res$0 = 

  t1 union t2

  /// Type Parameters for Class
  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty = false
  }

  class Nil[T] extends List[T] {
    def isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")

  };System.out.println("""res0: ClassHierarchy.IntSet = """ + $show(res$0));$skip(524); 

  /// Type Parameters to Function

  def singleton[T](elem: T) = new Cons[T](elem, new Nil[T]);System.out.println("""singleton: [T](elem: T)ClassHierarchy.Cons[T]""");$skip(22); val res$1 = 

  singleton[Int](1);System.out.println("""res1: ClassHierarchy.Cons[Int] = """ + $show(res$1));$skip(27); val res$2 = 
  singleton[Boolean](true);System.out.println("""res2: ClassHierarchy.Cons[Boolean] = """ + $show(res$2));$skip(15); val res$3 = 
  singleton(1);System.out.println("""res3: ClassHierarchy.Cons[Int] = """ + $show(res$3));$skip(18); val res$4 = 
  singleton(true);System.out.println("""res4: ClassHierarchy.Cons[Boolean] = """ + $show(res$4));$skip(341); 

  /* Write a function nth that takes an integer n and a list and selects the nth element of the list
  If index is outside the range then  IndexOutOfBounds exception is thrown*/

  def nth[T](n: Int, xs: List[T]): T = {

    if (xs.isEmpty) throw new IndexOutOfBoundsException
    else if (n == 0) xs.head else nth(n - 1, xs.tail)

  };System.out.println("""nth: [T](n: Int, xs: ClassHierarchy.List[T])T""");$skip(75); 

  val list = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Nil))));System.out.println("""list  : ClassHierarchy.Cons[Int] = """ + $show(list ));$skip(17); val res$5 = 

  nth(2, list);System.out.println("""res5: Int = """ + $show(res$5));$skip(15); val res$6 = 
  nth(7, list);System.out.println("""res6: Int = """ + $show(res$6))}

}

