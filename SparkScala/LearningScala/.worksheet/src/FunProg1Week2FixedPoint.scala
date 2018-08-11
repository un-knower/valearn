object FunProg1Week2FixedPoint {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  println("Welcome to the Scala worksheet");$skip(58); 

  def abs(x: Double): Double = { if (x < 0) -x else x };System.out.println("""abs: (x: Double)Double""");$skip(373); 

  /* ************** Find Fixed Point of Function  ************** ..*/

  /* Number x is FixedPoint of function f where f(x) = x  */

  /* We can find it iteratively by applying f in repetitive way until it converges..... x, f(x), f(f(x)), f(f(f(x))), ...
  	until the value does not vary anymore (or the change is sufficiently
		small).

	*/

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
  };System.out.println("""fixedPoint: (f: Double => Double)(firstGuess: Double)Double""");$skip(34); val res$0 = 

  fixedPoint(x => 1 + x / 2)(1);System.out.println("""res0: Double = """ + $show(res$0));$skip(172); 

  /* Sqrt in terms of fixedPoint function */

  /* sqrt(x) is a fixed point of the function (y => x / y) */

  def sqrtF(x: Double): Double = fixedPoint(y => x / y)(1);System.out.println("""sqrtF: (x: Double)Double""");$skip(356); 

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

  def sqrtF2(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0);System.out.println("""sqrtF2: (x: Double)Double""");$skip(13); val res$1 = 
  sqrtF2(10);System.out.println("""res1: Double = """ + $show(res$1));$skip(292); 

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

  def sqrtF3(x: Double) = fixedPoint2(y => x / y)(1.0);System.out.println("""sqrtF3: (x: Double)Double""");$skip(15); val res$2 = 

  sqrtF3(10);System.out.println("""res2: Double = """ + $show(res$2));$skip(35); val res$3 = 

  fixedPoint2(x => 1 + x / 2)(1);System.out.println("""res3: Double = """ + $show(res$3))}

}
