object Coursera1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(136); 

  /* Recursion Exercise 1*/

  for (row <- 1 to 4) {
    for (col <- 1 to row)
      print(pascal(col, row) + " ")
    println()
  };$skip(125); 

  def pascal(c: Int, r: Int): Int = {

    if (c == 1 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  };System.out.println("""pascal: (c: Int, r: Int)Int""");$skip(17); val res$0 = 

  pascal(2, 4);System.out.println("""res0: Int = """ + $show(res$0));$skip(15); val res$1 = 
  pascal(3, 5);System.out.println("""res1: Int = """ + $show(res$1));$skip(97); 

  /* Tail Recursion */

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(16); val res$2 = 

  gcd(14, 21);System.out.println("""res2: Int = """ + $show(res$2));$skip(87); 

  def factorial(n: Int): Int = {

    if (n == 0) 1 else n * factorial(n - 1)

  };System.out.println("""factorial: (n: Int)Int""");$skip(17); val res$3 = 

  factorial(4);System.out.println("""res3: Int = """ + $show(res$3));$skip(174); 

  /* Tail Recursive */

  def factorialR(n: Int): Int = {

    def loop(p: Int, n: Int): Int = {

      if (n == 1) p else loop(p * n, n - 1)
    }

    loop(1, n)
  };System.out.println("""factorialR: (n: Int)Int""");$skip(18); val res$4 = 

  factorialR(4);System.out.println("""res4: Int = """ + $show(res$4));$skip(227); 

  /* Higher Order Function */

  /*Function to compute sum of f() (sum/cube/factorial) numbers between numbers a,b */

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(52); 

  def sumInts(a: Int, b: Int) = sum(x => x, a, b);System.out.println("""sumInts: (a: Int, b: Int)Int""");$skip(61); 

  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b);System.out.println("""sumCubes: (a: Int, b: Int)Int""");$skip(56); 

  def sumFactorials(a: Int, b: Int) = sum(fact, a, b);System.out.println("""sumFactorials: (a: Int, b: Int)Int""");$skip(59); 

  def fact(x: Int): Int = if (x == 0) 1 else fact(x - 1);System.out.println("""fact: (x: Int)Int""");$skip(30); val res$5 = 

  sum(x => x * x * x, 3, 5);System.out.println("""res5: Int = """ + $show(res$5));$skip(19); val res$6 = 

  sumCubes(3, 5);System.out.println("""res6: Int = """ + $show(res$6));$skip(208); 

  /* Tail Recursive Version of sum */

  def sum2(f: Int => Int, a: Int, b: Int): Int = {

    def loop(p: Int, a: Int): Int = {

      if (a > b) p else loop(p + f(a), a + 1)
    }
    loop(0, a)

  };System.out.println("""sum2: (f: Int => Int, a: Int, b: Int)Int""");$skip(54); 

  def sumInts2(a: Int, b: Int) = sum2(x => x, a, b);System.out.println("""sumInts2: (a: Int, b: Int)Int""");$skip(63); 

  def sumCubes2(a: Int, b: Int) = sum2(x => x * x * x, a, b);System.out.println("""sumCubes2: (a: Int, b: Int)Int""");$skip(56); 
  def sumFactorials2(a: Int, b: Int) = sum2(fact, a, b);System.out.println("""sumFactorials2: (a: Int, b: Int)Int""");$skip(18); val res$7 = 
  sumCubes2(3, 5);System.out.println("""res7: Int = """ + $show(res$7));$skip(31); val res$8 = 

  sum2(x => x * x * x, 3, 5);System.out.println("""res8: Int = """ + $show(res$8));$skip(171); 

  /* Currying */

  def sum3(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  };System.out.println("""sum3: (f: Int => Int)(Int, Int) => Int""");$skip(32); 

  def sumInts3 = sum3(x => x);System.out.println("""sumInts3: => (Int, Int) => Int""");$skip(39); 
  def sumCubes3 = sum3(x => x * x * x);System.out.println("""sumCubes3: => (Int, Int) => Int""");$skip(34); 
  def sumFactorials3 = sum3(fact);System.out.println("""sumFactorials3: => (Int, Int) => Int""");$skip(20); val res$9 = 

  sumCubes3(3, 5);System.out.println("""res9: Int = """ + $show(res$9));$skip(31); val res$10 = 

  sum3(x => x * x * x)(3, 5);System.out.println("""res10: Int = """ + $show(res$10));$skip(28); 

  val funcube = sumCubes3;System.out.println("""funcube  : (Int, Int) => Int = """ + $show(funcube ));$skip(16); val res$11 = 
  funcube(3, 5);System.out.println("""res11: Int = """ + $show(res$11));$skip(115); 

  /* Currying */
  def sum4(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum4(f)(a + 1, b);System.out.println("""sum4: (f: Int => Int)(a: Int, b: Int)Int""");$skip(228); 

  /* Generalize Currying */

  /// Sum function takes 3 attributes f,a,b &  returns int
  //sum(arg1,arg2,arg3)= {E}
  def sumOrig(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sumOrig: (f: Int => Int, a: Int, b: Int)Int""");$skip(283); 

  /// Curried function sumC takes 1 attribute f & returns a function which takes 2 attributes
  // sumC(arg1)= { g(arg2,arg3){E} }
  def sumC(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if (a > b) 0
      else f(a) + sumF(a + 1, b)
    sumF
  };System.out.println("""sumC: (f: Int => Int)(Int, Int) => Int""");$skip(320); 

  /* ********************************************* */

  /* 1. Write a product function that calculates the product of the values of
a function for the points on a given interval */

  /* Linear Recursive */
  def product(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f, a + 1, b)
  };System.out.println("""product: (f: Int => Int, a: Int, b: Int)Int""");$skip(28); val res$12 = 
  product(x => x * x, 3, 4);System.out.println("""res12: Int = """ + $show(res$12));$skip(203); 

  /* Tail Recursive */
  def product2(f: Int => Int, a: Int, b: Int): Int = {
    def loop(p: Int, a: Int, b: Int): Int = {
      if (a > b) p else loop(f(a) * p, a + 1, b)
    }
    loop(1, a, b)
  };System.out.println("""product2: (f: Int => Int, a: Int, b: Int)Int""");$skip(31); val res$13 = 

  product2(x => x * x, 3, 4);System.out.println("""res13: Int = """ + $show(res$13));$skip(122); 

  /* Currried */
  def product3(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else product3(f)(a + 1, b)
  };System.out.println("""product3: (f: Int => Int)(a: Int, b: Int)Int""")}

}
