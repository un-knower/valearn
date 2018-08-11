object recfun_assignment {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(70); 
  println("Welcome to the Scala worksheet");$skip(156); 

  /* Recursion Exercise 1 : Pascal's Triangle*/

  for (row <- 1 to 5) {
    for (col <- 1 to row)
      print(pascal(col, row) + " ")
    println()
  };$skip(125); 

  def pascal(c: Int, r: Int): Int = {

    if (c == 1 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  };System.out.println("""pascal: (c: Int, r: Int)Int""");$skip(17); val res$0 = 

  pascal(2, 4);System.out.println("""res0: Int = """ + $show(res$0));$skip(15); val res$1 = 
  pascal(3, 5);System.out.println("""res1: Int = """ + $show(res$1));$skip(468); 

  /**
   * Recursion Exercise 2:
   * Write a recursive function which verifies the balancing of parentheses in a string
   */
  def balance(chars: List[Char]): Boolean = {
    def iterate(chars: List[Char], acc: Int): Int = {
      if (chars.isEmpty || acc < 0) acc
      else if (chars.head == '(') iterate(chars.tail, acc + 1)
      else if (chars.head == ')') iterate(chars.tail, acc - 1)
      else iterate(chars.tail, acc)
    }
    iterate(chars, 0) == 0
  };System.out.println("""balance: (chars: List[Char])Boolean""");$skip(441); 

  /**
   * Exercise 3: Counting Change
   * Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else if (money <= 0 && !coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  
};System.out.println("""countChange: (money: Int, coins: List[Int])Int""");$skip(29); val res$2 = 
  countChange(4, List(1, 2));System.out.println("""res2: Int = """ + $show(res$2))}
}
