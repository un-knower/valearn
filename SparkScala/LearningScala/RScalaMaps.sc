object RScalaMaps {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // Map and set
  val a = Map("a" -> "apple", "c1" -> "custard apple", "b" -> "ball", "c" -> "cat")
                                                  //> a  : scala.collection.immutable.Map[String,String] = Map(a -> apple, c1 -> c
                                                  //| ustard apple, b -> ball, c -> cat)
  println("add map: " + (a + (("c", "Cat"))))     //> add map: Map(a -> apple, c1 -> custard apple, b -> ball, c -> Cat)
  println("remove map: " + (a - ("a")))           //> remove map: Map(c1 -> custard apple, b -> ball, c -> cat)
  println("concat map: " + (a ++ Map("c" -> "cat")))
                                                  //> concat map: Map(a -> apple, c1 -> custard apple, b -> ball, c -> cat)
  println("lookup : " + a.get("a"))               //> lookup : Some(apple)
  val lookup1 = a.get("a")                        //> lookup1  : Option[String] = Some(apple)

  val lookup2 = a.getOrElse("a", "Empty")         //> lookup2  : String = apple
  val m = lookup1 match {
    case Some(x) => x
    case None    => "Sdfsd"
  }                                               //> m  : String = apple
  println("lookup1 : " + lookup1)                 //> lookup1 : Some(apple)
  println("lookup2 : " + lookup2)                 //> lookup2 : apple
  println("m : " + m)                             //> m : apple

  val objects1 = Set("ball")                      //> objects1  : scala.collection.immutable.Set[String] = Set(ball)
  val fruits = Set("apple", "custard apple")      //> fruits  : scala.collection.immutable.Set[String] = Set(apple, custard apple)
                                                  //| 
  val animals = Set("cat")                        //> animals  : scala.collection.immutable.Set[String] = Set(cat)
  def fn1(arg: (String, String)): String = {
    if (fruits.contains(arg._2)) "fruits"
    else if (animals.contains(arg._2)) "animals"
    else if (objects1.contains(arg._2)) "objects"
    else ""

  }                                               //> fn1: (arg: (String, String))String
  println("groupby: " + a.groupBy(fn1))           //> groupby: Map(objects -> Map(b -> ball), fruits -> Map(a -> apple, c1 -> cust
                                                  //| ard apple), animals -> Map(c -> cat))

  val map1 = Map(("a", "x") -> "apple", ("c1", "y") -> "custard apple", ("b", "z") -> "ball", ("c", "t") -> "cat")
                                                  //> map1  : scala.collection.immutable.Map[(String, String),String] = Map((a,x)
                                                  //|  -> apple, (c1,y) -> custard apple, (b,z) -> ball, (c,t) -> cat)
  println(map1.foldLeft(List[(String, String)](), List[(String)]())((x, y) => (x._1 :+ y._1, x._2 :+ y._2)))
                                                  //> (List((a,x), (c1,y), (b,z), (c,t)),List(apple, custard apple, ball, cat))

}