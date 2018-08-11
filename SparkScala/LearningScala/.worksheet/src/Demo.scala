object Demo {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(57); 
  println("Welcome to the Scala worksheet");$skip(44); 
  println("Welcome to the Scala worksheet");$skip(44); 
  println("Welcome to the Scala worksheet")

  import scala.collection.mutable.Stack

  import scala.collection.immutable.Set

  case class bracketsForAttributes(left: String, right: String)
  case class distributive(
    domain:            String,
    source:            String,
    attributes:        Seq[Seq[String]],
    attrValues:        Seq[Seq[String]],
    attrBrackets:      Seq[bracketsForAttributes],
    attrOperators:     Seq[Seq[String]],
    attrjoinOperators: Seq[String],
    conjOperator:      String,
    leftbrackets:      String,
    RightBrackets:     String,
    attributesSet:     Set[String]) {
    override def toString = this.attributes.head.head

  };$skip(865); 

val d1  = distributive("d1",
        "s1",
        Seq(Seq("a1")),
        Seq(Seq("v1")),
        Seq(bracketsForAttributes("", "")),
        Seq(Seq()),
        Seq(),
        "AND",
        "",
        "",
        Set("a1"));System.out.println("""d1  : Demo.distributive = """ + $show(d1 ))}

  object Demo7 {
    def main(args: Array[String]): Unit = {
      val c1 = List(distributive(
        "d1",
        "s1",
        Seq(Seq("a1")),
        Seq(Seq("v1")),
        Seq(bracketsForAttributes("", "")),
        Seq(Seq()),
        Seq(),
        "AND",
        "",
        "",
        Set("a1")))

      val c2 = List(distributive(
        "d1",
        "s1",
        Seq(Seq("a2")),
        Seq(Seq("v2")),
        Seq(bracketsForAttributes("", "")),
        Seq(Seq()),
        Seq(),
        "OR",
        "",
        "",
        Set("a2")))

      println("see here only")
      println(cartesianProduct(c1, c2))
      println("see here only")
      val c3 = List(distributive(
        "d2",
        "s1",
        Seq(Seq("a3")),
        Seq(Seq("v3")),
        Seq(bracketsForAttributes("", "")),
        Seq(Seq()),
        Seq(),
        "AND",
        "",
        "",
        Set("a3")))

      val c4 = List(distributive(
        "d1",
        "s1",
        Seq(Seq("a4")),
        Seq(Seq("v4")),
        Seq(bracketsForAttributes("", "")),
        Seq(Seq()),
        Seq(),
        "AND",
        "",
        "",
        Set("a4")))

      val c5 = cartesianProduct(c1, c2)
      val c6 = cartesianProduct(c3, c4)
      println(cartesianProduct(c5.flatten.flatten, c6.flatten.flatten).flatten.flatten)

      val mergedQueries1 = c5.map(x => x.map(y => mergeQuery(y)))

      val mergedQueries2 = c6.map(x => x.map(y => mergeQuery(y)))
      println("mergedQueries1: " + mergedQueries1)
      println("mergedQueries2: " + mergedQueries2)

      val c7 = cartesianProduct(mergedQueries1.flatten.flatten, mergedQueries2.flatten.flatten)
      val mergedQueries3 = c7.map(x => x.map(y => mergeQuery(y)))
      println("mergedQueries3: " + mergedQueries3)
      println("bracketed query: " + mergedQueries3.map(x => addBrackets(x)))

    }
    //left : new stack right: old stack
    def mergeStack(left: List[distributive], right: List[distributive]): List[distributive] = {

      val pairs = cartesianProduct(left, right)
      val mergedQueries = pairs.map(x => x.map(y => mergeQuery(y)))
      List()
    }

    // Function to generate Cartesian pairs. Result is not flattened as the merging logic requires to maintain such info.
    // Last conjunction operator of left stack is used as Universal conjunction operator.
    // conjunction operator of Last element of Right Stack is set as Left elements conjunction operator.
    def cartesianProduct(left: List[distributive], right: List[distributive]): List[List[List[distributive]]] = {

      val cartesian1 = left.dropRight(1).map(x => {
        val modRight = right.dropRight(1) :+ right.last.copy(conjOperator = x.conjOperator);
        modRight.map(y => List(x.copy(conjOperator = left.last.conjOperator), y))
      })
      val cartesian2 = List(right.map(y => List(left.last, y)))

      cartesian1 ++ cartesian2

    }

    //Function to merge queries if source and domain are same and attributes are different
    def mergeQuery(pairs: List[distributive]): List[distributive] = {
      if (pairs.head.source == pairs.last.source &
        pairs.head.domain == pairs.last.domain &
        pairs.head.attributesSet.size == (pairs.head.attributesSet -- pairs.last.attributesSet).size) {
        List(distributive(
          pairs.head.domain,
          pairs.head.source,
          pairs.head.attributes ++ pairs.last.attributes,
          pairs.head.attrValues ++ pairs.last.attrValues,
          ((bracketsForAttributes(pairs.head.attrBrackets.head.left + "(", pairs.head.attrBrackets.head.right)
            +: pairs.head.attrBrackets.drop(1)) ++
            (pairs.last.attrBrackets.dropRight(1) :+
              bracketsForAttributes(pairs.last.attrBrackets.last.left, pairs.last.attrBrackets.last.right + ")"))),
          pairs.head.attrOperators ++ pairs.last.attrOperators,
          (pairs.head.attrjoinOperators :+ pairs.head.conjOperator) ++ pairs.last.attrjoinOperators,
          pairs.last.conjOperator,
          pairs.head.leftbrackets,
          "",
          pairs.head.attributesSet ++ pairs.last.attributesSet))
      } else pairs
    }

    def addBrackets(a: List[List[distributive]]): List[List[distributive]] = {
      //Function is to insert brackets for applying distributive laws.

      // Rule: Add one extra left bracket to first element of pair,along with all left brackets from first and second elements.
      //       Remove all left brackets from second element of pair
      //       Remove all right brackets from first element of pair
      //       Add one extra right bracket to second element of pair along with all right brackets from first and second elements.
      if (a.size == 1) {
        if (a.head.size > 1) {
          a.map(x => List(
            distributive(
              x.head.domain,
              x.head.source,
              x.head.attributes,
              x.head.attrValues,
              x.head.attrBrackets,
              x.head.attrOperators,
              x.head.attrjoinOperators,
              x.head.conjOperator,
              x.head.leftbrackets ++ "(" + x.last.leftbrackets,
              "",
              x.head.attributesSet),
            distributive(
              x.last.domain,
              x.last.source,
              x.last.attributes,
              x.last.attrValues,
              x.last.attrBrackets,
              x.last.attrOperators,
              x.last.attrjoinOperators,
              x.last.conjOperator,
              "",
              x.head.RightBrackets + ")" + x.last.RightBrackets,
              x.last.attributesSet)))
        } else {
          a
        }
      } else {
        val first = if (a.head.size == 1) List(a.head)
        else {
          List(List(
            distributive(
              a.head.head.domain,
              a.head.head.source,
              a.head.head.attributes,
              a.head.head.attrValues,
              a.head.head.attrBrackets,
              a.head.head.attrOperators,
              a.head.head.attrjoinOperators,
              a.head.head.conjOperator,
              a.head.head.leftbrackets ++ "(" + a.head.last.leftbrackets,
              "",
              a.head.head.attributesSet),
            distributive(
              a.head.last.domain,
              a.head.last.source,
              a.head.last.attributes,
              a.head.last.attrValues,
              a.head.last.attrBrackets,
              a.head.last.attrOperators,
              a.head.last.attrjoinOperators,
              a.head.last.conjOperator,
              "",
              ")" + a.head.last.RightBrackets,
              a.head.last.attributesSet)))
        }
        val last = if (a.last.size == 1) List(a.last)
        else {
          List(List(
            distributive(
              a.last.head.domain,
              a.last.head.source,
              a.last.head.attributes,
              a.last.head.attrValues,
              a.last.head.attrBrackets,
              a.last.head.attrOperators,
              a.last.head.attrjoinOperators,
              a.last.head.conjOperator,
              "(" + a.head.last.leftbrackets,
              "",
              a.last.head.attributesSet),
            distributive(
              a.last.last.domain,
              a.last.last.source,
              a.last.last.attributes,
              a.last.last.attrValues,
              a.last.last.attrBrackets,
              a.last.last.attrOperators,
              a.last.last.attrjoinOperators,
              a.last.last.conjOperator,
              "",
              a.last.head.RightBrackets + ")" + a.last.last.RightBrackets,
              a.last.last.attributesSet)))
        }
        val middle = a.dropRight(1).drop(1).map(x => {
          List(
            distributive(
              x.head.domain,
              x.head.source,
              x.head.attributes,
              x.head.attrValues,
              x.head.attrBrackets,
              x.head.attrOperators,
              x.head.attrjoinOperators,
              x.head.conjOperator,
              "(" + x.last.leftbrackets,
              "",
              x.head.attributesSet),
            distributive(
              x.last.domain,
              x.last.source,
              x.last.attributes,
              x.last.attrValues,
              x.last.attrBrackets,
              x.last.attrOperators,
              x.last.attrjoinOperators,
              x.last.conjOperator,
              "",
              ")" + x.last.RightBrackets,
              x.last.attributesSet))
        })
        first ++ middle ++ last

      }

    }

  }
}
