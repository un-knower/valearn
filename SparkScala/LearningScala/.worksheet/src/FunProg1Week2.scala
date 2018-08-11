object FunProg1Week2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 

  println("Welcome to the Higher Order Function worksheet");$skip(83); 

  /* Partially Applied Function */
  val someNumbers = List(11, 10, 5, 0, 5, 10);System.out.println("""someNumbers  : List[Int] = """ + $show(someNumbers ));$skip(28); 
  someNumbers.foreach(cube);$skip(58); 

  def abs(x: Double): Double = { if (x < 0) -x else x };System.out.println("""abs: (x: Double)Double""");$skip(408); 
  /* Functions that take other functions as parameters or that return
     functions as results are called higher order functions. */

  /*Function to compute sum of f() (sum/cube/factorial) numbers between numbers a,b */

  /* Linear Recursive Sum */
  //Take the sum of the integers between a and b:
  def sum(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sum: (f: Int => Int, a: Int, b: Int)Int""");$skip(78); 

  /* Wrapper Functions */
  def sumInts(a: Int, b: Int) = sum(x => x, a, b);System.out.println("""sumInts: (a: Int, b: Int)Int""");$skip(106); 

  //Take the sum of the cubes of all the integers between a and b :
  def cube(x: Int): Int = x * x * x;System.out.println("""cube: (x: Int)Int""");$skip(59); 
  def sumCubes(a: Int, b: Int) = sum(x => x * x * x, a, b);System.out.println("""sumCubes: (a: Int, b: Int)Int""");$skip(54); 
  def sumFactorials(a: Int, b: Int) = sum(fact, a, b);System.out.println("""sumFactorials: (a: Int, b: Int)Int""");$skip(61); 
  def fact(x: Int): Int = if (x == 0) 1 else x * fact(x - 1);System.out.println("""fact: (x: Int)Int""");$skip(30); val res$0 = 

  sum(x => x * x * x, 3, 5);System.out.println("""res0: Int = """ + $show(res$0));$skip(18); val res$1 = 

  sumInts(3, 5);System.out.println("""res1: Int = """ + $show(res$1));$skip(17); val res$2 = 
  sumCubes(3, 5);System.out.println("""res2: Int = """ + $show(res$2));$skip(255); 

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
  def sumFactorials2(a: Int, b: Int) = sum2(fact, a, b);System.out.println("""sumFactorials2: (a: Int, b: Int)Int""");$skip(29); val res$3 = 
  sum2(x => x * x * x, 3, 5);System.out.println("""res3: Int = """ + $show(res$3));$skip(19); val res$4 = 

  sumInts2(3, 5);System.out.println("""res4: Int = """ + $show(res$4));$skip(18); val res$5 = 
  sumCubes2(3, 5);System.out.println("""res5: Int = """ + $show(res$5));$skip(752); 

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
  };System.out.println("""sum3: (f: Int => Int)(Int, Int) => Int""");$skip(21); val res$6 = 

  sum3(cube)(3, 5);System.out.println("""res6: Int = """ + $show(res$6));$skip(29); 

  val funcube = sum3(cube);System.out.println("""funcube  : (Int, Int) => Int = """ + $show(funcube ));$skip(16); val res$7 = 
  funcube(3, 5);System.out.println("""res7: Int = """ + $show(res$7));$skip(60); 

  /*

  sum3(f)(3,5)


  */
  def sumInts3 = sum3(x => x);System.out.println("""sumInts3: => (Int, Int) => Int""");$skip(39); 
  def sumCubes3 = sum3(x => x * x * x);System.out.println("""sumCubes3: => (Int, Int) => Int""");$skip(34); 
  def sumFactorials3 = sum3(fact);System.out.println("""sumFactorials3: => (Int, Int) => Int""");$skip(19); val res$8 = 

  sumInts3(3, 5);System.out.println("""res8: Int = """ + $show(res$8));$skip(18); val res$9 = 
  sumCubes3(3, 5);System.out.println("""res9: Int = """ + $show(res$9));$skip(430); val res$10 = 

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

  sum3(cube)(3, 5);System.out.println("""res10: Int = """ + $show(res$10));$skip(31); val res$11 = 

  sum3(x => x * x * x)(3, 5);System.out.println("""res11: Int = """ + $show(res$11));$skip(769); 

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
  };System.out.println("""sum4: (f: Int => Int)(a: Int, b: Int)Int""");$skip(31); val res$12 = 

  sum4(x => x * x * x)(3, 5);System.out.println("""res12: Int = """ + $show(res$12));$skip(490); 

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

  val funcube2 = sum4(x => x * x * x)(_, _);System.out.println("""funcube2  : (Int, Int) => Int = """ + $show(funcube2 ));$skip(17); val res$13 = 
  funcube2(3, 5);System.out.println("""res13: Int = """ + $show(res$13));$skip(26); 

  val funcube3 = sum4 _;System.out.println("""funcube3  : (Int => Int) => ((Int, Int) => Int) = """ + $show(funcube3 ));$skip(25); val res$14 = 

  funcube3(cube)(3, 5);System.out.println("""res14: Int = """ + $show(res$14));$skip(1009); 

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

  def simpleSum(a: Int, b: Int, c: Int): Int = a + b + c;System.out.println("""simpleSum: (a: Int, b: Int, c: Int)Int""");$skip(61); 

  def simpleSumC0a(a: Int)(b: Int)(c: Int) = { a + b + c };System.out.println("""simpleSumC0a: (a: Int)(b: Int)(c: Int)Int""");$skip(24); val res$15 = 
  simpleSumC0a(1)(2)(3);System.out.println("""res15: Int = """ + $show(res$15));$skip(19); val res$16 = 

  simpleSumC0a _;System.out.println("""res16: Int => (Int => (Int => Int)) = """ + $show(res$16));$skip(146); 

  // def f(args1)....(argsn-1) = { def g(argsn) = E; g }

  def simpleSumC1(a: Int, b: Int): Int => Int = { def sum(c: Int) = a + b + c; sum };System.out.println("""simpleSumC1: (a: Int, b: Int)Int => Int""");$skip(18); val res$17 = 

  simpleSumC1 _;System.out.println("""res17: (Int, Int) => Int => Int = """ + $show(res$17));$skip(25); val res$18 = 

  simpleSumC1(1, 2)(3);System.out.println("""res18: Int = """ + $show(res$18));$skip(22); val res$19 = 

  simpleSumC1(1, 2);System.out.println("""res19: Int => Int = """ + $show(res$19));$skip(71); 

  def simpleSumC1a(a: Int, b: Int)(c: Int) = { c: Int => a + b + c };System.out.println("""simpleSumC1a: (a: Int, b: Int)(c: Int)Int => Int""");$skip(19); val res$20 = 

  simpleSumC1a _;System.out.println("""res20: (Int, Int) => Int => (Int => Int) = """ + $show(res$20));$skip(94); 

  def simpleSumC2(a: Int): (Int, Int) => Int = { def sum(b: Int, c: Int) = a + b + c; sum };System.out.println("""simpleSumC2: (a: Int)(Int, Int) => Int""");$skip(18); val res$21 = 

  simpleSumC2 _;System.out.println("""res21: Int => ((Int, Int) => Int) = """ + $show(res$21));$skip(25); val res$22 = 

  simpleSumC2(1)(2, 3);System.out.println("""res22: Int = """ + $show(res$22));$skip(66); 

  def simpleSumC2a(a: Int)(b: Int, c: Int): Int = { a + b + c };System.out.println("""simpleSumC2a: (a: Int)(b: Int, c: Int)Int""");$skip(19); val res$23 = 

  simpleSumC2a _;System.out.println("""res23: Int => ((Int, Int) => Int) = """ + $show(res$23));$skip(24); val res$24 = 
  simpleSumC2a(1)(2, 3);System.out.println("""res24: Int = """ + $show(res$24));$skip(214); 

  //def f = (args1 => (args2 =>..... (argsn =>  E)...))

  def simpleSumC3(a: Int): Int => Int => Int = {

    def sum1(b: Int): (Int) => Int = {

      def sum(c: Int) = a + b + c; sum
    }

    sum1

  };System.out.println("""simpleSumC3: (a: Int)Int => (Int => Int)""");$skip(18); val res$25 = 

  simpleSumC3 _;System.out.println("""res25: Int => (Int => (Int => Int)) = """ + $show(res$25));$skip(25); val res$26 = 

  simpleSumC3(1)(2)(3);System.out.println("""res26: Int = """ + $show(res$26));$skip(84); 

  def simpleSumC3a(a: Int): Int => Int => Int = { b: Int => c: Int => a + b + c };System.out.println("""simpleSumC3a: (a: Int)Int => (Int => Int)""");$skip(19); val res$27 = 

  simpleSumC3a _;System.out.println("""res27: Int => (Int => (Int => Int)) = """ + $show(res$27));$skip(26); val res$28 = 

  simpleSumC3a(1)(2)(3);System.out.println("""res28: Int = """ + $show(res$28));$skip(200); 

  /// Sum function takes 3 attributes f,a,b &  returns int
  //sum(arg1,arg2,arg3)= {E}

  def sumOrig(f: Int => Int, a: Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sum(f, a + 1, b)
  };System.out.println("""sumOrig: (f: Int => Int, a: Int, b: Int)Int""");$skip(316); 

  /// Curried function sumC takes 1 attribute f & returns a function which takes 2 attributes

  // sumC(arg1)= { def g(arg2,arg3) = E ; g }

  def sumC(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      {
        if (a > b) 0
        else f(a) + sumF(a + 1, b)
      }
    sumF
  };System.out.println("""sumC: (f: Int => Int)(Int, Int) => Int""");$skip(482); 

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
  };System.out.println("""product: (f: Int => Int, a: Int, b: Int)Int""");$skip(28); val res$29 = 
  product(x => x * x, 3, 4);System.out.println("""res29: Int = """ + $show(res$29));$skip(203); 

  /* Tail Recursive */
  def product2(f: Int => Int, a: Int, b: Int): Int = {
    def loop(p: Int, a: Int, b: Int): Int = {
      if (a > b) p else loop(f(a) * p, a + 1, b)
    }
    loop(1, a, b)
  };System.out.println("""product2: (f: Int => Int, a: Int, b: Int)Int""");$skip(31); val res$30 = 

  product2(x => x * x, 3, 4);System.out.println("""res30: Int = """ + $show(res$30));$skip(170); 

  /* Currried */

  def product3(f: Int => Int): (Int, Int) => Int = {
    def multi(a: Int, b: Int): Int = { if (a > b) 1 else f(a) * multi(a + 1, b) }
    multi
  };System.out.println("""product3: (f: Int => Int)(Int, Int) => Int""");$skip(31); val res$31 = 

  product3(x => x * x)(3, 4);System.out.println("""res31: Int = """ + $show(res$31));$skip(41); 

  val funsquare = product3(x => x * x);System.out.println("""funsquare  : (Int, Int) => Int = """ + $show(funsquare ));$skip(18); val res$32 = 
  funsquare(3, 4);System.out.println("""res32: Int = """ + $show(res$32));$skip(171); 

  /* Curried Advance Syntax, Anonymous Inner Function */

  def product4(f: Int => Int)(a: Int, b: Int): Int = {
    if (a > b) 1 else f(a) * product4(f)(a + 1, b)
  };System.out.println("""product4: (f: Int => Int)(a: Int, b: Int)Int""");$skip(31); val res$33 = 

  product4(x => x * x)(3, 4);System.out.println("""res33: Int = """ + $show(res$33));$skip(48); 

  val funsquare2 = product4(x => x * x)(_, _);System.out.println("""funsquare2  : (Int, Int) => Int = """ + $show(funsquare2 ));$skip(21); val res$34 = 

  funsquare2(3, 4);System.out.println("""res34: Int = """ + $show(res$34));$skip(84); 

  /* Factorial in product terms */

  def fact2(n: Int) = product4(x => x)(1, n);System.out.println("""fact2: (n: Int)Int""");$skip(10); val res$35 = 
  fact(5);System.out.println("""res35: Int = """ + $show(res$35));$skip(348); 

  /* More Generalized function of sum and products  */
  /* Let's say f is our map function and combine is the function used to combine results after map function  */

  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
  };System.out.println("""mapReduce: (f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int)Int""");$skip(181); 

  /* implement product function using mapReduce function ie product of squares/f  */

  def product5(f: Int => Int)(a: Int, b: Int): Int = mapReduce(f, (x, y) => x * y, 1)(a, b);System.out.println("""product5: (f: Int => Int)(a: Int, b: Int)Int""");$skip(29); val res$36 = 
  product5(x => x * x)(3, 4);System.out.println("""res36: Int = """ + $show(res$36))}

}
