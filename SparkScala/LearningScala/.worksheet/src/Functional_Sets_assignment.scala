object Functional_Sets_assignment {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(79); 
  println("Welcome to the Scala worksheet")

  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean;$skip(256); 

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: Functional_Sets_assignment.Set, elem: Int)Boolean""");$skip(396); 

  /**
   * Define a function singletonSet which creates a singleton set from one integer value: the set represents the set of the one given element.
   * Now that we have a way to create singleton sets, we want to define a function that allow us to build bigger sets from smaller ones
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = { x => x == elem };System.out.println("""singletonSet: (elem: Int)Functional_Sets_assignment.Set""");$skip(36); 

  val set1: Set = singletonSet(1);System.out.println("""set1  : Functional_Sets_assignment.Set = """ + $show(set1 ));$skip(22); val res$0 = 

  contains(set1, 1);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(703); 

  /**
   *
   *
   * Define the functions union , intersect , and diff , which takes two sets, and return, respectively, their union, intersection and differences.
   * diff(s, t) returns a set which contains all the elements of the set s that are not in the set t .
   *
   * Define the function filter which selects only the elements of a set that are accepted by a given predicate p .
   * The filtered elements are returned as a new set.
   *
   *
   */

  /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = {
    def f(elem: Int): Boolean = { contains(s, elem) || contains(t, elem) }
    f
  };System.out.println("""union: (s: Functional_Sets_assignment.Set, t: Functional_Sets_assignment.Set)Functional_Sets_assignment.Set""");$skip(254); 

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` or `t`.
   */
  def intersection(s: Set, t: Set): Set = {
    def f(elem: Int): Boolean = { contains(s, elem) && contains(t, elem) }
    f
  };System.out.println("""intersection: (s: Functional_Sets_assignment.Set, t: Functional_Sets_assignment.Set)Functional_Sets_assignment.Set""");$skip(248); 

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {
    def f(elem: Int): Boolean = { (contains(s, elem)) && (!contains(t, elem)) }
    f
  };System.out.println("""diff: (s: Functional_Sets_assignment.Set, t: Functional_Sets_assignment.Set)Functional_Sets_assignment.Set""");$skip(31); 

  val set2 = singletonSet(2);System.out.println("""set2  : Functional_Sets_assignment.Set = """ + $show(set2 ));$skip(25); val res$1 = 

  union(set1, set2)(1);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(33); val res$2 = 
  contains(union(set1, set2), 1);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(30); val res$3 = 
  intersection(set1, set2)(1);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(40); val res$4 = 
  contains(intersection(set1, set2), 1);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(22); val res$5 = 
  diff(set1, set2)(1);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(32); val res$6 = 
  contains(diff(set1, set2), 1);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(1496); 

  /**
   *
   * 2.2 Queries and Transformations on Sets
   *
   * In this part, we are interested in functions used to make requests on elements of a set. The first function tests whether a given predicate is true for all elements of the set.
   * This forall function has the following signature:
   *
   * def forall(s: Set, p: Int => Boolean): Boolean
   *
   * Note that there is no direct way to find which elements are in a set. contains only allows to know whether a given element is included.
   * Thus, if we wish to do something to all elements of a set, then we have to iterate over all integers, testing each time whether it is included in the set,
   * and if so, to do something with it.
   * Here, we consider that an integer x has the property -1000 <= x <= 1000 in order to limit the search space.
   *
   * Implement forall using linear recursion. For this, use a helper function nested inforall.
   *
   * Using forall , implement a function exists which tests whether a set contains at least one element for which the given predicate is true.
   * Note that the functions forall and exists behave like the universal and existential quantifiers of first-order logic.
   *
   * Finally, write a function map which transforms a given set into another one by applying to each of its elements the given function.
   *
   *
   *
   */

  /**
   * Returns the subset of `s` for which `p` holds.
   */
  def filter(s: Set, p: Int => Boolean): Set = { x => contains(s, x) && p(x) };System.out.println("""filter: (s: Functional_Sets_assignment.Set, p: Int => Boolean)Functional_Sets_assignment.Set""");$skip(87); 
  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  var bound = 1000;System.out.println("""bound  : Int = """ + $show(bound ));$skip(299); 
  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def inforall(x: Int): Boolean = {
      if (x > bound) true
      else if (contains(s, x) && !p(x)) false
      else inforall(x + 1)
    }
    inforall(-bound)
  };System.out.println("""forall: (s: Functional_Sets_assignment.Set, p: Int => Boolean)Boolean""");$skip(173); 

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */

  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, !p(_));System.out.println("""exists: (s: Functional_Sets_assignment.Set, p: Int => Boolean)Boolean""");$skip(175); 
  //def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

  // Let's revise partial function syntax / function value
  def square(x: Int): Int = { x * x };System.out.println("""square: (x: Int)Int""");$skip(102); 
  val a = square(_);System.out.println("""a  : Int => Int = """ + $show(a ));$skip(61);  // it returns function value. Means a will apply square to argument passed to it.
  // can look like  val a = x => square(x)

  println(a(2));$skip(250); 

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */

  // Can be read like. It checks existence of all elements which are euqual to f(x) in s
  def map(s: Set, f: Int => Int): Set = (elem) => exists(s, elem == f(_));System.out.println("""map: (s: Functional_Sets_assignment.Set, f: Int => Int)Functional_Sets_assignment.Set""");$skip(186); 

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  };System.out.println("""toString: (s: Functional_Sets_assignment.Set)String""");$skip(118); 

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  };System.out.println("""printSet: (s: Functional_Sets_assignment.Set)Unit""");$skip(15); 

  bound = 10;$skip(37); 
  printSet(x => x > 0 && x % 2 == 0);$skip(176); 

  /**
   * Eg. function f1 = x => x+1 , function s1 = x => x * x . Write a function which returns a "function" which does s1(f1(x))
   */

  def f1(x: Int): Int = { x + 1 };System.out.println("""f1: (x: Int)Int""");$skip(34); 
  def s1(x: Int): Int = { x * x };System.out.println("""s1: (x: Int)Int""");$skip(81); 

  def fs(f1: Int => Int, s1: Int => Int): Int => Int = { x: Int => s1(f1(x)) };System.out.println("""fs: (f1: Int => Int, s1: Int => Int)Int => Int""");$skip(26); 

  val f1s1 = fs(f1, s1);System.out.println("""f1s1  : Int => Int = """ + $show(f1s1 ));$skip(10); val res$7 = 
  f1s1(2);System.out.println("""res7: Int = """ + $show(res$7))}
}
