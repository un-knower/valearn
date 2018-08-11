object FunProg1Week1 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(82); 

  println("Welcome to the Function Evaluation worksheet");$skip(11); val res$0 = 

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
  sumCubes(3, 5);System.out.println("""res10: Int = """ + $show(res$10))}

}
