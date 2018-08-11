object FunProg1Week2Assignment_FuncSet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  /**
   * We represent a set by its characteristic function, i.e.
   * its `contains` predicate.
   */
  type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)
                                                  //> contains: (s: FunProg1Week2Assignment_FuncSet.Set, elem: Int)Boolean

  /**
   * Define a function singletonSet which creates a singleton set from one integer value: the set represents the set of the one given element.
   * Now that we have a way to create singleton sets, we want to define a function that allow us to build bigger sets from smaller ones
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = { x => x == elem }
                                                  //> singletonSet: (elem: Int)FunProg1Week2Assignment_FuncSet.Set

  val set1: Set = singletonSet(1)                 //> set1  : FunProg1Week2Assignment_FuncSet.Set = FunProg1Week2Assignment_FuncSe
                                                  //| t$$$Lambda$8/1456208737@27f674d

  contains(set1, 1)                               //> res0: Boolean = true

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
  }                                               //> union: (s: FunProg1Week2Assignment_FuncSet.Set, t: FunProg1Week2Assignment_
                                                  //| FuncSet.Set)FunProg1Week2Assignment_FuncSet.Set

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` or `t`.
   */
  def intersection(s: Set, t: Set): Set = {
    def f(elem: Int): Boolean = { contains(s, elem) && contains(t, elem) }
    f
  }                                               //> intersection: (s: FunProg1Week2Assignment_FuncSet.Set, t: FunProg1Week2Assi
                                                  //| gnment_FuncSet.Set)FunProg1Week2Assignment_FuncSet.Set

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {
    def f(elem: Int): Boolean = { (contains(s, elem)) && (!contains(t, elem)) }
    f
  }                                               //> diff: (s: FunProg1Week2Assignment_FuncSet.Set, t: FunProg1Week2Assignment_F
                                                  //| uncSet.Set)FunProg1Week2Assignment_FuncSet.Set

  val set2 = singletonSet(2)                      //> set2  : FunProg1Week2Assignment_FuncSet.Set = FunProg1Week2Assignment_FuncS
                                                  //| et$$$Lambda$8/1456208737@1d251891

  union(set1, set2)(1)                            //> res1: Boolean = true
  contains(union(set1, set2), 1)                  //> res2: Boolean = true
  intersection(set1, set2)(1)                     //> res3: Boolean = false
  contains(intersection(set1, set2), 1)           //> res4: Boolean = false
  diff(set1, set2)(1)                             //> res5: Boolean = true
  contains(diff(set1, set2), 1)                   //> res6: Boolean = true

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
  def filter(s: Set, p: Int => Boolean): Set = { x => contains(s, x) && p(x) }
                                                  //> filter: (s: FunProg1Week2Assignment_FuncSet.Set, p: Int => Boolean)FunProg1
                                                  //| Week2Assignment_FuncSet.Set
  /**
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  var bound = 1000                                //> bound  : Int = 1000
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
  }                                               //> forall: (s: FunProg1Week2Assignment_FuncSet.Set, p: Int => Boolean)Boolean
                                                  //| 

  /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */

  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, !p(_))
                                                  //> exists: (s: FunProg1Week2Assignment_FuncSet.Set, p: Int => Boolean)Boolean
                                                  //| 
  //def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, x => !p(x))

  // Let's revise partial function syntax / function value
  def square(x: Int): Int = { x * x }             //> square: (x: Int)Int
  val a = square(_) // it returns function value. Means a will apply square to argument passed to it.
                                                  //> a  : Int => Int = FunProg1Week2Assignment_FuncSet$$$Lambda$12/509886383@6e8
                                                  //| dacdf
  // can look like  val a = x => square(x)

  println(a(2))                                   //> 4

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */

  // Can be read like. It checks existence of all elements which are euqual to f(x) in s
  def map(s: Set, f: Int => Int): Set = (elem) => exists(s, elem == f(_))
                                                  //> map: (s: FunProg1Week2Assignment_FuncSet.Set, f: Int => Int)FunProg1Week2As
                                                  //| signment_FuncSet.Set

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }                                               //> toString: (s: FunProg1Week2Assignment_FuncSet.Set)String

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }                                               //> printSet: (s: FunProg1Week2Assignment_FuncSet.Set)Unit

  bound = 10
  printSet(x => x > 0 && x % 2 == 0)              //> {2,4,6,8,10}

  /**
   * Eg. function f1 = x => x+1 , function s1 = x => x * x . Write a function which returns a "function" which does s1(f1(x))
   */

  def f1(x: Int): Int = { x + 1 }                 //> f1: (x: Int)Int
  def s1(x: Int): Int = { x * x }                 //> s1: (x: Int)Int

  def fs(f1: Int => Int, s1: Int => Int): Int => Int = { x: Int => s1(f1(x)) }
                                                  //> fs: (f1: Int => Int, s1: Int => Int)Int => Int

  val f1s1 = fs(f1, s1)                           //> f1s1  : Int => Int = FunProg1Week2Assignment_FuncSet$$$Lambda$20/905544614@
                                                  //| 7f690630
  f1s1(2)                                         //> res7: Int = 9

}