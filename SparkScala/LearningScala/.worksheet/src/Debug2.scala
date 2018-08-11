object Debug2 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  println("Welcome to the Scala worksheet");$skip(60); 

  //def fn1 = List(1, 2, 3, 4)
  val a = List(2, 3, 4, 5);System.out.println("""a  : List[Int] = """ + $show(a ));$skip(187); 
  //a.flatMap(x => fn1)
  //println(a.flatMap(x => fn1))

  def flatMapGaurav(a: List[String]): List[Int] = {
    if (a.isEmpty) List()
    else fn1(a.head) ++ flatMapGaurav(a.tail)
  };System.out.println("""flatMapGaurav: (a: List[String])List[Int]""");$skip(133); 

  def mapGaurav(a: List[String]) :List[List[Int]]= {
    if (a.isEmpty) List()
    else List(fn1(a.head)) ++ mapGaurav(a.tail)
  };System.out.println("""mapGaurav: (a: List[String])List[List[Int]]""");$skip(42); 

  def fn1(a: String) = List(1, 2, 3, 4);System.out.println("""fn1: (a: String)List[Int]""");$skip(60); 
  
  println(flatMapGaurav(List("Ramji","Gaurav","Nisha")));$skip(53); 
  println(mapGaurav(List("Ramji","Gaurav","Nisha")))}
  
}
