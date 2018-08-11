object FunProg1Week2 {

  println("Welcome to the Higher Order Function worksheet")
                                                  //> Welcome to the Higher Order Function worksheet

  /* Partially Applied Function */
  val someNumbers = List(11, 10, 5, 0, 5, 10)     //> someNumbers  : List[Int] = List(11, 10, 5, 0, 5, 10)
  someNumbers.foreach(cube)

  def abs(x: Double): Double = { if (x < 0) -x else x }
                                                  //> abs: (x: Double)Double
  /* Functions that take other functions as parameters or that return
     functions as results are called higher order functions. */

  /*Function to compute sum of f() (sum/cube/factorial) numbers between numbers a,b */

  /* Linear Recursive Sum */
  //Take the sum of the integers between a and b:
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }                                               //> sum: (f: Int => Int, a: Int, b: Int)Int

  /* Wrapper Functions */
  def sumInts(a: Int, b: Int) = sum(x => x, a, b) //> sumInts: (a: Int, b: Int)Int

  //Take the sum of the cubes of all the integers between a and b :
  def cube(x: Int): Int = x * x * x               //> cube: (x: Int)Int
  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b)
                                                  //> sumCubes: (a: Int, b: Int)Int
  def sumFactorials(a: Int, b: Int) = sum(fact, a, b)
                                                  //> sumFactorials: (a: Int, b: Int)Int
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1)
                                                  //> fact: (x: Int)Int

  sum(x => x * x * x, 3, 5)                       //> res0: Int = 216

  sumInts(3, 5)                                   //> res1: Int = 12
  sumCubes(3, 5)                                  //> res2: Int = 216

  /* Tail Recursive Version of sum */

  /* def f(arg1,arg2,arg3,...argn) = {E}  */

  def sum2(f: Int => Int, a: Int, b: Int): Int = {

    def loop(p: Int, a: Int): Int = {

      if (a > b) p else loop(p + f(a), a + 1)
    }
    loop(0, a)

  }                                               //> sum2: (f: Int => Int, a: Int, b: Int)Int

  def sumInts2(a: Int, b: Int) = sum2(x => x, a, b)
                                                  //> sumInts2: (a: Int, b: Int)Int
  def sumCubes2(a: Int, b: Int) = sum2(x => x * x * x, a, b)
                                                  //> sumCubes2: (a: Int, b: Int)Int
  def sumFactorials2(a: Int, b: Int) = sum2(fact, a, b)
                                                  //> sumFactorials2: (a: Int, b: Int)Int
  sum2(x => x * x * x, 3, 5)                      //> res3: Int = 216

  sumInts2(3, 5)                                  //> res4: Int = 12
  sumCubes2(3, 5)                                 //> res5: Int = 216

  /* Currying */
  /*
 	In above examples parameters get passed from SumInts( Wrapper Function) to Sum function without being modified.

 	Can we get rid of this? Like instead of SumInts calling sum function and passing same parameters to it..

 	let's rewrite sum as a function that returns another function.
	The returned function sumF applies the given function parameter f and
	sums the results.
  */

  /* def f(arg1,arg2,arg3,...argn-1) = {def g(argsn)= E ; g}  */
  /* sum3 functions receives function of type Int=>Int and returns a function of type (Int,Int) => Int */

  def sum3(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      {
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      }
    sumF
  }                                               //> sum3: (f: Int => Int)(Int, Int) => Int

  sum3(cube)(3, 5)                                //> res6: Int = 216

  val funcube = sum3(cube)                        //> funcube  : (Int, Int) => Int = FunProg1Week2$$$Lambda$19/357863579@6cd8737
                                                  //| 
  funcube(3, 5)                                   //> res7: Int = 216

  /*

  sum3(f)(3,5)


  */
  def sumInts3 = sum3(x => x)                     //> sumInts3: => (Int, Int) => Int
  def sumCubes3 = sum3(x => x * x * x)            //> sumCubes3: => (Int, Int) => Int
  def sumFactorials3 = sum3(fact)                 //> sumFactorials3: => (Int, Int) => Int

  sumInts3(3, 5)                                  //> res8: Int = 12
  sumCubes3(3, 5)                                 //> res9: Int = 216

  /* In the previous example, can we avoid the sumInts, sumCubes, …
		middlemen?
		Of course:
		sum (cube) (1, 10)
		▶ sum(cube) applies sum to cube and returns the sum of cubes function.
		▶ sum(cube) is therefore equivalent to sumCubes.
		▶ This function is next applied to the arguments (1, 10).
		Generally, function application associates to the left:
		sum(cube)(1, 10) == (sum (cube)) (1, 10)

  */

  sum3(cube)(3, 5)                                //> res10: Int = 216

  sum3(x => x * x * x)(3, 5)                      //> res11: Int = 216

  /*
		  The definition of functions that return functions is so useful in functional
		  programming that there is a special syntax for it in Scala.

		  For example, the following definition of sum4 is equivalent to sum3  with
			the nested sumF function, but shorter-


			 sum3 was -

				def sum3(f: Int => Int): (Int, Int) => Int = {
			    def sumF(a: Int, b: Int): Int =
			      {
			        if (a > b) 0
			        else f(a) + sumF(a + 1, b)
			      }
			    sumF
			  }

				sum4 with advanced Syntax -

		  	def sum4(f: Int => Int)(a: Int, b: Int): Int =
				if (a > b) 0 else f(a) + sum4(f)(a + 1, b)

	  */

  /* Curried advanced Syntax */

  def sum4(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 0 else f(a) + sum4(f)(a + 1, b)
  }                                               //> sum4: (f: Int => Int)(a: Int, b: Int)Int

  sum4(x => x * x * x)(3, 5)                      //> res12: Int = 216

  /*


  f(3) + sum4(f)(4,5)
  			f(4)+sum4(f)(5,5)
  						f(5)+sum4(f)(6,5)
  								0
  */

  /*
  Partially Applied / Unapplied Function

  val funcube2 = sum4(x => x * x * x)

		Error:- missing argument list for method sum4 in object FunProg1Week2 Unapplied methods are only converted to functions when a function type is expected.
		You can make this conversion explicit by writing `sum4 _` or `sum4(_)(_,_)` instead of `sum4`.

	*/

  val funcube2 = sum4(x => x * x * x)(_, _)       //> funcube2  : (Int, Int) => Int = FunProg1Week2$$$Lambda$26/99347477@21bcffb5
                                                  //| 
  funcube2(3, 5)                                  //> res13: Int = 216

  val funcube3 = sum4 _                           //> funcube3  : (Int => Int) => ((Int, Int) => Int) = FunProg1Week2$$$Lambda$28
                                                  //| /1020923989@7a5d012c

  funcube3(cube)(3, 5)                            //> res14: Int = 216

  /*
			  Question: Given
				  def sum4(f: Int => Int)(a: Int, b: Int): Int = {
				    if (a > b) 0 else f(a) + sum4(f)(a + 1, b)
				  }

			  What's the type of sum4  ?

			  Answer:
					(Int => Int) => (Int, Int) => Int
					Note that functional types associate to the right. That is to say that
					Int => Int => Int
					is equivalent to
					Int => (Int => Int)

  */

  /*

		  In general, a definition of a function with multiple parameter lists-

					def f(args1).....(argsn) = E

					def f(args1,......argsn) = E

					where n > 1, is equivalent to

					def f(args1)....(argsn-1) = { def g(argsn) = E; g }

					where g is a fresh identifier. Or for short:

					def f(args1)......(argsn-1) = (argsn => E)


			By repeating the process n times Original Function is shown to be equivalent to-

			def f = (args1 => (args2 =>..... (argsn =>  E)...))

   */

  /* Generalize Currying */

  //def f(args1,......argsn) = E

  def simpleSum(a: Int, b: Int, c: Int): Int = a + b + c
                                                  //> simpleSum: (a: Int, b: Int, c: Int)Int

  def simpleSumC0a(a: Int)(b: Int)(c: Int) = { a + b + c }
                                                  //> simpleSumC0a: (a: Int)(b: Int)(c: Int)Int
  simpleSumC0a(1)(2)(3)                           //> res15: Int = 6

  simpleSumC0a _                                  //> res16: Int => (Int => (Int => Int)) = FunProg1Week2$$$Lambda$31/1798286609@
                                                  //| 79698539

  // def f(args1)....(argsn-1) = { def g(argsn) = E; g }

  def simpleSumC1(a: Int, b: Int): Int => Int = { def sum(c: Int) = a + b + c; sum }
                                                  //> simpleSumC1: (a: Int, b: Int)Int => Int

  simpleSumC1 _                                   //> res17: (Int, Int) => Int => Int = FunProg1Week2$$$Lambda$32/1945604815@2ed9
                                                  //| 4a8b

  simpleSumC1(1, 2)(3)                            //> res18: Int = 6

  simpleSumC1(1, 2)                               //> res19: Int => Int = FunProg1Week2$$$Lambda$33/234698513@3b81a1bc

  def simpleSumC1a(a: Int, b: Int)(c: Int) = { c: Int => a + b + c }
                                                  //> simpleSumC1a: (a: Int, b: Int)(c: Int)Int => Int

  simpleSumC1a _                                  //> res20: (Int, Int) => Int => (Int => Int) = FunProg1Week2$$$Lambda$34/168410
                                                  //| 6402@13fee20c

  def simpleSumC2(a: Int): (Int, Int) => Int = { def sum(b: Int, c: Int) = a + b + c; sum }
                                                  //> simpleSumC2: (a: Int)(Int, Int) => Int

  simpleSumC2 _                                   //> res21: Int => ((Int, Int) => Int) = FunProg1Week2$$$Lambda$35/1308927845@78
                                                  //| 3e6358

  simpleSumC2(1)(2, 3)                            //> res22: Int = 6

  def simpleSumC2a(a: Int)(b: Int, c: Int): Int = { a + b + c }
                                                  //> simpleSumC2a: (a: Int)(b: Int, c: Int)Int

  simpleSumC2a _                                  //> res23: Int => ((Int, Int) => Int) = FunProg1Week2$$$Lambda$37/745160567@246
                                                  //| ae04d
  simpleSumC2a(1)(2, 3)                           //> res24: Int = 6

  //def f = (args1 => (args2 =>..... (argsn =>  E)...))

  def simpleSumC3(a: Int): Int => Int => Int = {

    def sum1(b: Int): (Int) => Int = {

      def sum(c: Int) = a + b + c; sum
    }

    sum1

  }                                               //> simpleSumC3: (a: Int)Int => (Int => Int)

  simpleSumC3 _                                   //> res25: Int => (Int => (Int => Int)) = FunProg1Week2$$$Lambda$38/1644443712@
                                                  //| 5315b42e

  simpleSumC3(1)(2)(3)                            //> res26: Int = 6

  def simpleSumC3a(a: Int): Int => Int => Int = { b: Int => c: Int => a + b + c }
                                                  //> simpleSumC3a: (a: Int)Int => (Int => Int)

  simpleSumC3a _                                  //> res27: Int => (Int => (Int => Int)) = FunProg1Week2$$$Lambda$41/1622006612@
                                                  //| 3f2a3a5

  simpleSumC3a(1)(2)(3)                           //> res28: Int = 6

  /// Sum function takes 3 attributes f,a,b &  returns int
  //sum(arg1,arg2,arg3)= {E}

  def sumOrig(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  }                                               //> sumOrig: (f: Int => Int, a: Int, b: Int)Int

  /// Curried function sumC takes 1 attribute f & returns a function which takes 2 attributes

  // sumC(arg1)= { def g(arg2,arg3) = E ; g }

  def sumC(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      {
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      }
    sumF
  }                                               //> sumC: (f: Int => Int)(Int, Int) => Int

  /* ******************************************************************* */

  /* 1. Write a product function that calculates the product of the values of
      a function for the points on a given interval

		2. Write factorial in terms of product.

		3. Can you write a more general function, which generalizes both sum
		and product?

 */

  /* Linear Recursive */

  def product(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product(f, a + 1, b)
  }                                               //> product: (f: Int => Int, a: Int, b: Int)Int
  product(x => x * x, 3, 4)                       //> res29: Int = 144

  /* Tail Recursive */
  def product2(f: Int => Int, a: Int, b: Int): Int = {
    def loop(p: Int, a: Int, b: Int): Int = {
      if (a > b) p else loop(f(a) * p, a + 1, b)
    }
    loop(1, a, b)
  }                                               //> product2: (f: Int => Int, a: Int, b: Int)Int

  product2(x => x * x, 3, 4)                      //> res30: Int = 144

  /* Currried */

  def product3(f: Int => Int): (Int, Int) => Int = {
    def multi(a: Int, b: Int): Int = { if (a > b) 1 else f(a) * multi(a + 1, b) }
    multi
  }                                               //> product3: (f: Int => Int)(Int, Int) => Int

  product3(x => x * x)(3, 4)                      //> res31: Int = 144

  val funsquare = product3(x => x * x)            //> funsquare  : (Int, Int) => Int = FunProg1Week2$$$Lambda$47/1753447031@35671
                                                  //| 35c
  funsquare(3, 4)                                 //> res32: Int = 144

  /* Curried Advance Syntax, Anonymous Inner Function */

  def product4(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product4(f)(a + 1, b)
  }                                               //> product4: (f: Int => Int)(a: Int, b: Int)Int

  product4(x => x * x)(3, 4)                      //> res33: Int = 144

  val funsquare2 = product4(x => x * x)(_, _)     //> funsquare2  : (Int, Int) => Int = FunProg1Week2$$$Lambda$50/1096283470@90f6
                                                  //| bfd

  funsquare2(3, 4)                                //> res34: Int = 144

  /* Factorial in product terms */

  def fact2(n: Int) = product4(x => x)(1, n)      //> fact2: (n: Int)Int
  fact(5)                                         //> res35: Int = 120

  /* More Generalized function of sum and products  */
  /* Let's say f is our map function and combine is the function used to combine results after map function  */

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  }                                               //> mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b
                                                  //| : Int)Int

  /* implement product function using mapReduce function ie product of squares/f  */

  def product5(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b)
                                                  //> product5: (f: Int => Int)(a: Int, b: Int)Int
  product5(x => x * x)(3, 4)                      //> res36: Int = 144

}