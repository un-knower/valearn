object FunProg1Week1Assignment_Recfun {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  /* Recursion Exercise 1 : Pascal's Triangle*/

  for (row <- 1 to 5) {
    for (col <- 1 to row)
      print(pascal(col, row) + " ")
    println()
  }                                               //> 1 
                                                  //| 1 1 
                                                  //| 1 2 1 
                                                  //| 1 3 3 1 
                                                  //| 1 4 6 4 1 

  def pascal(c: Int, r: Int): Int = {

    if (c == 1 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  }                                               //> pascal: (c: Int, r: Int)Int

  pascal(2, 4)                                    //> res0: Int = 3
  pascal(3, 5)                                    //> res1: Int = 6

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
  }                                               //> balance: (chars: List[Char])Boolean

  /**
   * Exercise 3: Counting Change
   * Write a recursive function that counts how many different ways you can make change for an amount, given a list of coin denominations
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else if (money <= 0 && !coins.isEmpty) 0
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)

  }                                               //> countChange: (money: Int, coins: List[Int])Int
  countChange(4, List(1, 2))                      //> res2: Int = 3

}