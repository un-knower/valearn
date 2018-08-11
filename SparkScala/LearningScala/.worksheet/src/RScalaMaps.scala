object RScalaMaps {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(63); 
  println("Welcome to the Scala worksheet");$skip(103); 

  // Map and set
  val a = Map("a" -> "apple", "c1" -> "custard apple", "b" -> "ball", "c" -> "cat");System.out.println("""a  : scala.collection.immutable.Map[String,String] = """ + $show(a ));$skip(46); 
  println("add map: " + (a + (("c", "Cat"))));$skip(40); 
  println("remove map: " + (a - ("a")));$skip(53); 
  println("concat map: " + (a ++ Map("c" -> "cat")));$skip(36); 
  println("lookup : " + a.get("a"));$skip(27); 
  val lookup1 = a.get("a");System.out.println("""lookup1  : Option[String] = """ + $show(lookup1 ));$skip(44); 

  val lookup2 = a.getOrElse("a", "Empty");System.out.println("""lookup2  : String = """ + $show(lookup2 ));$skip(80); 
  val m = lookup1 match {
    case Some(x) => x
    case None    => "Sdfsd"
  };System.out.println("""m  : String = """ + $show(m ));$skip(34); 
  println("lookup1 : " + lookup1);$skip(34); 
  println("lookup2 : " + lookup2);$skip(22); 
  println("m : " + m);$skip(31); 

  val objects1 = Set("ball");System.out.println("""objects1  : scala.collection.immutable.Set[String] = """ + $show(objects1 ));$skip(45); 
  val fruits = Set("apple", "custard apple");System.out.println("""fruits  : scala.collection.immutable.Set[String] = """ + $show(fruits ));$skip(27); 
  val animals = Set("cat");System.out.println("""animals  : scala.collection.immutable.Set[String] = """ + $show(animals ));$skip(204); 
  def fn1(arg: (String, String)): String = {
    if (fruits.contains(arg._2)) "fruits"
    else if (animals.contains(arg._2)) "animals"
    else if (objects1.contains(arg._2)) "objects"
    else ""

  };System.out.println("""fn1: (arg: (String, String))String""");$skip(40); 
  println("groupby: " + a.groupBy(fn1));$skip(117); 

  val map1 = Map(("a", "x") -> "apple", ("c1", "y") -> "custard apple", ("b", "z") -> "ball", ("c", "t") -> "cat");System.out.println("""map1  : scala.collection.immutable.Map[(String, String),String] = """ + $show(map1 ));$skip(109); 
  println(map1.foldLeft(List[(String, String)](), List[(String)]())((x, y) => (x._1 :+ y._1, x._2 :+ y._2)))}

}
