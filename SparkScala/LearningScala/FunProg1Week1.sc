object FunProg1Week1 {

  println("Welcome to the Function Evaluation worksheet")
                                                  //> Welcome to the Function Evaluation worksheet

  10 / 3                                          //> res0: Int(3) = 3
  10.0 / 3.0                                      //> res1: Double(3.3333333333333335) = 3.3333333333333335
  10 % 3                                          //> res2: Int(1) = 1
  10.0 % 3.0                                      //> res3: Double(1.0) = 1.0

  /* Newton's Substitution Method to compute Square Root*/

  def abs(x: Double): Double = { if (x < 0) -x else x }
                                                  //> abs: (x: Double)Double

  def sqrItr(guess: Double, x: Double): Double = {
    if (isGoodEnough(guess, x)) guess else sqrItr(improve(guess, x), x)
  }                                               //> sqrItr: (guess: Double, x: Double)Double

  def isGoodEnough(guess: Double, x: Double) = {

    abs((guess * guess - x) / x) < 0.001

  }                                               //> isGoodEnough: (guess: Double, x: Double)Boolean

  def improve(guess: Double, x: Double) = (guess + x / guess) / 2
                                                  //> improve: (guess: Double, x: Double)Double

  def sqrt(x: Double) = sqrItr(1.0, x)            //> sqrt: (x: Double)Double

  sqrt(10)                                        //> res4: Double = 3.16245562280389

  /* Tail Recursion */

  def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)              //> gcd: (a: Int, b: Int)Int

  gcd(14, 21)                                     //> res5: Int = 7

  /* Linear Recursion */
  def factorial(n: Int): Int = {

    if (n == 0) 1 else n * factorial(n - 1)

  }                                               //> factorial: (n: Int)Int

  factorial(4)                                    //> res6: Int = 24

  /* Tail Recursive factorial */

  def factorialR(n: Int): Int = {

    def loop(p: Int, n: Int): Int = {

      if (n == 1) p else loop(p * n, n - 1)
    }

    loop(1, n)
  }                                               //> factorialR: (n: Int)Int

  factorialR(4)                                   //> res7: Int = 24

  /* Higher Order Function */

  /*Function to compute sum of f() (sum/cube/factorial) numbers between numbers a,b */

  /* Linear Recursive Sum */

  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int

  /* Wrapper Functions */
  def sumInts(a: Int, b: Int) = sum(x => x, a, b) //> sumInts: (a: Int, b: Int)Int

  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
                                                  //> sumCubes: (a: Int, b: Int)Int
  def sumFactorials(a: Int, b: Int) = sum(fact, a, b)
                                                  //> sumFactorials: (a: Int, b: Int)Int
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)
                                                  //> fact: (x: Int)Int

  sum(x => x * x * x, 3, 5)                       //> res8: Int = 216

  sumInts(3, 5)                                   //> res9: Int = 12
  sumCubes(3, 5)                                  //> res10: Int = 216

}