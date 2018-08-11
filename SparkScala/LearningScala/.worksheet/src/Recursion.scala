object Recursion {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(11); val res$0 = 

  10 / 3;System.out.println("""res0: Int(3) = """ + $show(res$0));$skip(13); val res$1 = 
  10.0 / 3.0;System.out.println("""res1: Double(3.3333333333333335) = """ + $show(res$1));$skip(9); val res$2 = 
  10 % 3;System.out.println("""res2: Int(1) = """ + $show(res$2));$skip(13); val res$3 = 
  10.0 % 3.0;System.out.println("""res3: Double(1.0) = """ + $show(res$3));$skip(119); 

  /* Newton's Substitution Method to compute Square Root*/

  def abs(x: Double): Double = { if (x < 0) -x else x };System.out.println("""abs: (x: Double)Double""");$skip(129); 

  def sqrItr(guess: Double, x: Double): Double = {
    if (isGoodEnough(guess, x)) guess else sqrItr(improve(guess, x), x)
  };System.out.println("""sqrItr: (guess: Double, x: Double)Double""");$skip(100); 

  def isGoodEnough(guess: Double, x: Double) = {

    abs((guess * guess - x) / x) < 0.001

  };System.out.println("""isGoodEnough: (guess: Double, x: Double)Boolean""");$skip(68); 

  def improve(guess: Double, x: Double) = (guess + x / guess) / 2;System.out.println("""improve: (guess: Double, x: Double)Double""");$skip(41); 

  def sqrt(x: Double) = sqrItr(1.0, x);System.out.println("""sqrt: (x: Double)Double""");$skip(13); val res$4 = 

  sqrt(10);System.out.println("""res4: Double = """ + $show(res$4));$skip(97); 

  /* Tail Recursion */

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(16); val res$5 = 

  gcd(14, 21);System.out.println("""res5: Int = """ + $show(res$5));$skip(112); 

  /* Linear Recursion */
  def factorial(n: Int): Int = {

    if (n == 0) 1 else n * factorial(n - 1)

  };System.out.println("""factorial: (n: Int)Int""");$skip(17); val res$6 = 

  factorial(4);System.out.println("""res6: Int = """ + $show(res$6));$skip(184); 

  /* Tail Recursive factorial */

  def factorialR(n: Int): Int = {

    def loop(p: Int, n: Int): Int = {

      if (n == 1) p else loop(p * n, n - 1)
    }

    loop(1, n)
  };System.out.println("""factorialR: (n: Int)Int""");$skip(18); val res$7 = 

  factorialR(4);System.out.println("""res7: Int = """ + $show(res$7));$skip(258); 

  /* Higher Order Function */

  /*Function to compute sum of f() (sum/cube/factorial) numbers between numbers a,b */

  /* Linear Recursive Sum */

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(78); 

  /* Wrapper Functions */
  def sumInts(a: Int, b: Int) = sum(x => x, a, b);System.out.println("""sumInts: (a: Int, b: Int)Int""");$skip(61); 

  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b);System.out.println("""sumCubes: (a: Int, b: Int)Int""");$skip(54); 
  def sumFactorials(a: Int, b: Int) = sum(fact, a, b);System.out.println("""sumFactorials: (a: Int, b: Int)Int""");$skip(61); 
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1);System.out.println("""fact: (x: Int)Int""");$skip(30); val res$8 = 

  sum(x => x * x * x, 3, 5);System.out.println("""res8: Int = """ + $show(res$8));$skip(18); val res$9 = 

  sumInts(3, 5);System.out.println("""res9: Int = """ + $show(res$9));$skip(17); val res$10 = 
  sumCubes(3, 5);System.out.println("""res10: Int = """ + $show(res$10));$skip(255); 

  /* Tail Recursive Version of sum */

  /* def f(arg1,arg2,arg3,...argn) = {E}  */

  def sum2(f: Int => Int, a: Int, b: Int): Int = {

    def loop(p: Int, a: Int): Int = {

      if (a > b) p else loop(p + f(a), a + 1)
    }
    loop(0, a)

  };System.out.println("""sum2: (f: Int => Int, a: Int, b: Int)Int""");$skip(54); 

  def sumInts2(a: Int, b: Int) = sum2(x => x, a, b);System.out.println("""sumInts2: (a: Int, b: Int)Int""");$skip(61); 
  def sumCubes2(a: Int, b: Int) = sum2(x => x * x * x, a, b);System.out.println("""sumCubes2: (a: Int, b: Int)Int""");$skip(56); 
  def sumFactorials2(a: Int, b: Int) = sum2(fact, a, b);System.out.println("""sumFactorials2: (a: Int, b: Int)Int""");$skip(29); val res$11 = 
  sum2(x => x * x * x, 3, 5);System.out.println("""res11: Int = """ + $show(res$11));$skip(19); val res$12 = 

  sumInts2(3, 5);System.out.println("""res12: Int = """ + $show(res$12));$skip(18); val res$13 = 
  sumCubes2(3, 5);System.out.println("""res13: Int = """ + $show(res$13));$skip(378); 

  /* Curried Version of sum */

  /* def f(arg1,arg2,arg3,...argn-1) = {def g(argsn)= E ; g}  */
  /* sum3 functions receives function of type Int=>Int and returns a function of type (Int,Int) => Int */

  def sum3(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      {
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      }
    sumF
  };System.out.println("""sum3: (f: Int => Int)(Int, Int) => Int""");$skip(32); 

  def sumInts3 = sum3(x => x);System.out.println("""sumInts3: => (Int, Int) => Int""");$skip(39); 
  def sumCubes3 = sum3(x => x * x * x);System.out.println("""sumCubes3: => (Int, Int) => Int""");$skip(34); 
  def sumFactorials3 = sum3(fact);System.out.println("""sumFactorials3: => (Int, Int) => Int""");$skip(70); val res$14 = 

  // Avoid middle man use sum directly
  sum3(x => x * x * x)(3, 5);System.out.println("""res14: Int = """ + $show(res$14));$skip(19); val res$15 = 

  sumInts3(3, 5);System.out.println("""res15: Int = """ + $show(res$15));$skip(18); val res$16 = 
  sumCubes3(3, 5);System.out.println("""res16: Int = """ + $show(res$16));$skip(28); 

  val funcube = sumCubes3;System.out.println("""funcube  : (Int, Int) => Int = """ + $show(funcube ));$skip(16); val res$17 = 
  funcube(3, 5);System.out.println("""res17: Int = """ + $show(res$17));$skip(242); 

  /* Currying Advanced Syntax ,shorter than before. anonymous inner function */

  /* def f(arg1,arg2,arg3,...argn) = (argsn => E)  */

  def sum4(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum4(f)(a + 1, b)
  };System.out.println("""sum4: (f: Int => Int)(a: Int, b: Int)Int""");$skip(332); 

  /* val funcube2 = sum4(x => x * x * x)
  missing argument list for method sum4 in object Recursion Unapplied methods are only converted to functions when a function type is expected.
  You can make this conversion explicit by writing `sum4 _` or `sum4(_)(_,_)` instead of `sum4`.*/

  val funcube2 = sum4(x => x * x * x)(_, _);System.out.println("""funcube2  : (Int, Int) => Int = """ + $show(funcube2 ));$skip(17); val res$18 = 
  funcube2(3, 5);System.out.println("""res18: Int = """ + $show(res$18));$skip(384); 

  /* Repeat above process  n times
  def f(arg1,arg2,arg3,...argn) = {E} can be finally converted to

  def f = (arg1 => (arg2 => ...(argm => E)...))
*/

  /* Generalize Currying */

  /// Sum function takes 3 attributes f,a,b &  returns int
  //sum(arg1,arg2,arg3)= {E}
  def sumOrig(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sumOrig: (f: Int => Int, a: Int, b: Int)Int""");$skip(303); 

  /// Curried function sumC takes 1 attribute f & returns a function which takes 2 attributes
  // sumC(arg1)= { g(arg2,arg3){E} }
  def sumC(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      {
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      }
    sumF
  };System.out.println("""sumC: (f: Int => Int)(Int, Int) => Int""");$skip(326); 

  /* ********************************************* */

  /* 1. Write a product function that calculates the product of the values of
      a function for the points on a given interval */

  /* Linear Recursive */
  def product(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f, a + 1, b)
  };System.out.println("""product: (f: Int => Int, a: Int, b: Int)Int""");$skip(28); val res$19 = 
  product(x => x * x, 3, 4);System.out.println("""res19: Int = """ + $show(res$19));$skip(203); 

  /* Tail Recursive */
  def product2(f: Int => Int, a: Int, b: Int): Int = {
    def loop(p: Int, a: Int, b: Int): Int = {
      if (a > b) p else loop(f(a) * p, a + 1, b)
    }
    loop(1, a, b)
  };System.out.println("""product2: (f: Int => Int, a: Int, b: Int)Int""");$skip(31); val res$20 = 

  product2(x => x * x, 3, 4);System.out.println("""res20: Int = """ + $show(res$20));$skip(168); 

  /* Currried */
  def product3(f: Int => Int): (Int, Int) => Int = {
    def multi(a: Int, b: Int): Int = { if (a > b) 1 else f(a) * multi(a + 1, b) }
    multi
  };System.out.println("""product3: (f: Int => Int)(Int, Int) => Int""");$skip(31); val res$21 = 

  product3(x => x * x)(3, 4);System.out.println("""res21: Int = """ + $show(res$21));$skip(41); 

  val funsquare = product3(x => x * x);System.out.println("""funsquare  : (Int, Int) => Int = """ + $show(funsquare ));$skip(18); val res$22 = 
  funsquare(3, 4);System.out.println("""res22: Int = """ + $show(res$22));$skip(171); 

  /* Curried Advance Syntax, Anonymous Inner Function */

  def product4(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product4(f)(a + 1, b)
  };System.out.println("""product4: (f: Int => Int)(a: Int, b: Int)Int""");$skip(31); val res$23 = 

  product4(x => x * x)(3, 4);System.out.println("""res23: Int = """ + $show(res$23));$skip(48); 

  val funsquare2 = product4(x => x * x)(_, _);System.out.println("""funsquare2  : (Int, Int) => Int = """ + $show(funsquare2 ));$skip(21); val res$24 = 

  funsquare2(3, 4);System.out.println("""res24: Int = """ + $show(res$24));$skip(84); 

  /* Factorial in product terms */

  def fact2(n: Int) = product4(x => x)(1, n);System.out.println("""fact2: (n: Int)Int""");$skip(10); val res$25 = 
  fact(5);System.out.println("""res25: Int = """ + $show(res$25));$skip(348); 

  /* More Generalized function of sum and products  */
  /* Let's say f is our map function and combine is the function used to combine results after map function  */

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  };System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int)Int""");$skip(181); 

  /* implement product function using mapReduce function ie product of squares/f  */

  def product5(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b);System.out.println("""product5: (f: Int => Int)(a: Int, b: Int)Int""");$skip(29); val res$26 = 
  product5(x => x * x)(3, 4);System.out.println("""res26: Int = """ + $show(res$26));$skip(257); 

  /*  Find Fixed Point of Function ..*/

  /* Number x is FixedPoint of function f where f(x) = x  */
  /* We can find it iteratively by applying f in repetitive way until it converges..... x, f(x), f(f(x)), f(f(f(x))), ... */

  val tolerance = 0.0001;System.out.println("""tolerance  : Double = """ + $show(tolerance ));$skip(85); 

  def isCloseEnough(x: Double, y: Double) =
    abs(((x - y) / x) / x) < tolerance;System.out.println("""isCloseEnough: (x: Double, y: Double)Boolean""");$skip(268); 

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      println("guess =" + guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  };System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(34); val res$27 = 

  fixedPoint(x => 1 + x / 2)(1);System.out.println("""res27: Double = """ + $show(res$27));$skip(172); 

  /* Sqrt in terms of fixedPoint function */

  /* sqrt(x) is a fixed point of the function (y => x / y) */

  def sqrtF(x: Double): Double = fixedPoint(y => x / y)(1);System.out.println("""sqrtF: (x: Double)Double""");$skip(350); 

  /*
   sqrtF(10)

   y=1 -> 10/1
   y=10 -> 10/10
   y=1 -> 10/1 ...
   ...
   ...

   It doesn't converges
*/

  /*
One way to control such oscillations is to prevent the estimation
from varying too much. This is done by averaging successive values
of the original sequence .
*/

  def sqrtF2(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0);System.out.println("""sqrtF2: (x: Double)Double""");$skip(13); val res$28 = 
  sqrtF2(10);System.out.println("""res28: Double = """ + $show(res$28));$skip(292); 

  //OR

  def fixedPoint2(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = (f(guess) + guess) / 2
      println("guess =" + guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  };System.out.println("""fixedPoint2: (f: Double => Double)(firstGuess: Double)Double""");$skip(57); 

  def sqrtF3(x: Double) = fixedPoint2(y => x / y)(1.0);System.out.println("""sqrtF3: (x: Double)Double""");$skip(15); val res$29 = 

  sqrtF3(10);System.out.println("""res29: Double = """ + $show(res$29));$skip(35); val res$30 = 

  fixedPoint2(x => 1 + x / 2)(1);System.out.println("""res30: Double = """ + $show(res$30))}
}
