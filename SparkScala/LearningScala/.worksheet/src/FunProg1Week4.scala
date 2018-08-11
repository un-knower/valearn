object FunProg1Week4 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 
  println("Welcome to the Scala worksheet")

  /**
   * An anonymous function such as
   * (x: Int) => x  x
   * is expanded to:
   *
   * {
   * class AnonFun extends Function1[Int, Int] {
   * def apply(x: Int) = x  x
   * }
   * new AnonFun
   * }
   *
   * or, shorter, using anonymous class syntax:
   *
   * new Function1[Int, Int] {
   * def apply(x: Int) = x  x
   * }
   *
   */

  /**
   * Expansion of Function Calls
   * A function call, such as f(a, b), where f is a value of some class
   * type, is expanded to
   * f.apply(a, b)
   * So the OO - translation of
   * val f = (x: Int) => x * x
   * f(7)
   * would be
   * val f = new Function1[Int, Int] {
   * def apply(x: Int) = x * x
   * }
   * f.apply(7)
   *
   * Note that a method such as
   * def f(x: Int): Boolean = ...
   *
   * is not itself a function value.
   * But if f is used in a place where a Function type is expected, it is
   * converted automatically to the function value
   *
   * (x: Int) => f(x)
   * or, expanded:
   * new Function1[Int, Boolean] {
   * def apply(x: Int) = f(x)
   * }
   *
   */

  /* Case Class */

  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr;$skip(4048); 

  /**
   * Case classes are Scala’s way to allow pattern matching on objects without
   * requiring a large amount of boilerplate.
   * Using Case modifier makes the Scala compiler add some syntactic
   * conveniences to your class
   *
   * #1- First, it adds a factory method with the name of the class.
   * This means you can construct Var object by Var("x") instead of having to write new Var("x")
   * scala> val v = Var("x")
   * v: Var = Var(x)
   * scala> val op = BinOp("+", Number(1), v)
   * op: BinOp = BinOp(+,Number(1.0),Var(x))
   * val op = BinOp("+", Number(1), v)
   * #2 - The second syntactic convenience is that all arguments in the parameter list of a case class
   * implicitly get a val prefix, so they are maintained as fields:
   * scala> v.name
   * res0: String = x
   * scala> op.left
   * res1: Expr = Number(1.0)
   *
   * #3- Third, the compiler adds “natural” implementations of methods toString,
   * hashCode, and equals to your class. They will print, hash, and compare a
   * whole tree consisting of the class and (recursively) all its arguments. Since
   * == in Scala always delegates to equals, this means that elements of case
   * classes are always compared structurally:
   * scala> println(op)
   * BinOp(+,Number(1.0),Var(x))
   * scala> op.right == Var("x")
   * res3: Boolean = true
   * #4 - Finally, the compiler adds a copy method to your class for making modified
   * copies. This method is useful for making a new instance of the class that is
   * the same as another one except that one or two attributes are different. The
   * method works by using named and default parameters (see Section 8.8). You
   * specify the changes you’d like to make by using named parameters. For any
   * parameter you don’t specify, the value from the old object is used. As an
   * example,
   *
   * scala> op.copy(operator = "")
   * res4: BinOp = BinOp(,
   * Number(1.0),Var(x))
   */

  /**
   * Pattern Matching
   * An expression of the form
   * e match f case p1 => e1 ::: case pn => en g
   * matches the value of the selector e with the patterns p1....pn in the order in which they are written.
   * The whole match expression is rewritten to the right-hand side of the first case where the pattern matches the selector e.
   * References to pattern variables are replaced by the corresponding parts in the selector.
   * if none of the patterns match, an exception named MatchError is thrown.
   */

  def simplifyTop(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e))  => e // Double negation
    case BinOp("+", e, Number(0)) => e // Adding zero
    case BinOp("*", e, Number(1)) => e // Multiplying by one
    case _                        => expr
  };System.out.println("""simplifyTop: (expr: FunProg1Week4.Expr)FunProg1Week4.Expr""")}

}
