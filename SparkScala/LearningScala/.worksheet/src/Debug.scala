object Debug {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet")
  import scala.collection.mutable.ArrayBuffer;$skip(84); 
  val arr :Array[Int]  = Array(1,2,3);System.out.println("""arr  : Array[Int] = """ + $show(arr ));$skip(17); 
  val a :Int = 1;System.out.println("""a  : Int = """ + $show(a ));$skip(9); val res$0 = 
  arr(1);System.out.println("""res0: Int = """ + $show(res$0));$skip(48); 
  val arrT = Array(("aa",1),("aa",2),("bb",2) );System.out.println("""arrT  : Array[(String, Int)] = """ + $show(arrT ))}
  
  
}
