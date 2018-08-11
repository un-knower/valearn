object FunProg1Week2FixedPoint {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  def abs(x: Double): Double = { if (x < 0) -x else x }
                                                  //> abs: (x: Double)Double

  /* ************** Find Fixed Point of Function  ************** ..*/

  /* Number x is FixedPoint of function f where f(x) = x  */

  /* We can find it iteratively by applying f in repetitive way until it converges..... x, f(x), f(f(x)), f(f(f(x))), ...
  	until the value does not vary anymore (or the change is sufficiently
		small).

	*/

  val tolerance = 0.0001                          //> tolerance  : Double = 1.0E-4

  def isCloseEnough(x: Double, y: Double) =
    abs(((x - y) / x) / x) < tolerance            //> isCloseEnough: (x: Double, y: Double)Boolean

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      println("guess =" + guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }                                               //> fixedPoint: (f: Double => Double)(firstGuess: Double)Double

  fixedPoint(x => 1 + x / 2)(1)                   //> guess =1.0
                                                  //| guess =1.5
                                                  //| guess =1.75
                                                  //| guess =1.875
                                                  //| guess =1.9375
                                                  //| guess =1.96875
                                                  //| guess =1.984375
                                                  //| guess =1.9921875
                                                  //| guess =1.99609375
                                                  //| guess =1.998046875
                                                  //| guess =1.9990234375
                                                  //| guess =1.99951171875
                                                  //| res0: Double = 1.999755859375

  /* Sqrt in terms of fixedPoint function */

  /* sqrt(x) is a fixed point of the function (y => x / y) */

  def sqrtF(x: Double): Double = fixedPoint(y => x / y)(1)
                                                  //> sqrtF: (x: Double)Double

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

  def sqrtF2(x: Double) = fixedPoint(y => (y + x / y) / 2)(1.0)
                                                  //> sqrtF2: (x: Double)Double
  sqrtF2(10)                                      //> guess =1.0
                                                  //| guess =5.5
                                                  //| guess =3.659090909090909
                                                  //| guess =3.196005081874647
                                                  //| guess =3.16245562280389
                                                  //| res1: Double = 3.162277665175675

  //OR

  def fixedPoint2(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = (f(guess) + guess) / 2
      println("guess =" + guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }                                               //> fixedPoint2: (f: Double => Double)(firstGuess: Double)Double

  def sqrtF3(x: Double) = fixedPoint2(y => x / y)(1.0)
                                                  //> sqrtF3: (x: Double)Double

  sqrtF3(10)                                      //> guess =1.0
                                                  //| guess =5.5
                                                  //| guess =3.659090909090909
                                                  //| guess =3.196005081874647
                                                  //| guess =3.16245562280389
                                                  //| res2: Double = 3.162277665175675

  fixedPoint2(x => 1 + x / 2)(1)                  //> guess =1.0
                                                  //| guess =1.25
                                                  //| guess =1.4375
                                                  //| guess =1.578125
                                                  //| guess =1.68359375
                                                  //| guess =1.7626953125
                                                  //| guess =1.822021484375
                                                  //| guess =1.86651611328125
                                                  //| guess =1.8998870849609375
                                                  //| guess =1.9249153137207031
                                                  //| guess =1.9436864852905273
                                                  //| guess =1.9577648639678955
                                                  //| guess =1.9683236479759216
                                                  //| guess =1.9762427359819412
                                                  //| guess =1.982182051986456
                                                  //| guess =1.986636538989842
                                                  //| guess =1.9899774042423815
                                                  //| guess =1.992483053181786
                                                  //| guess =1.9943622898863396
                                                  //| guess =1.9957717174147547
                                                  //| guess =1.996828788061066
                                                  //| guess =1.9976215910457995
                                                  //| guess =1.9982161932843496
                                                  //| guess =1.9986621449632622
                                                  //| res3: Double = 1.9989966087224467

}