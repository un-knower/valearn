object Scala_MapVsFlatMap {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  //def fn1 = List(1, 2, 3, 4)
  val a = List(2, 3, 4, 5)                        //> a  : List[Int] = List(2, 3, 4, 5)
  //a.flatMap(x => fn1)
  //println(a.flatMap(x => fn1))

  def flatMapGaurav(a: List[String]): List[Int] = {
    if (a.isEmpty) List()
    else fn1(a.head) ++ flatMapGaurav(a.tail)
  }                                               //> flatMapGaurav: (a: List[String])List[Int]

  def mapGaurav(a: List[String]): List[List[Int]] = {
    if (a.isEmpty) List()
    else List(fn1(a.head)) ++ mapGaurav(a.tail)
  }                                               //> mapGaurav: (a: List[String])List[List[Int]]

  def fn1(a: String) = List(1, 2, 3, 4)           //> fn1: (a: String)List[Int]

  println(flatMapGaurav(List("Ramji", "Gaurav", "Nisha")))
                                                  //> List(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
  println(mapGaurav(List("Ramji", "Gaurav", "Nisha")))
                                                  //> List(List(1, 2, 3, 4), List(1, 2, 3, 4), List(1, 2, 3, 4))
}