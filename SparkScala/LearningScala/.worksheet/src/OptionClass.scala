object OptionClass {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(64); 
  println("Welcome to the Scala worksheet");$skip(114); 
  println("https://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html");$skip(92); 
  println("https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten");$skip(57); 
  println("https://dev.sortable.com/flatmap-to-option/");$skip(308); 
  /**
   * Option[A] is a container for an optional value of type A. If the value of type A is present, the Option[A] is an instance of Some[A],
   * containing the present value of type A. If the value is absent, the Option[A] is the object None
   */

  val greeting: Option[String] = Some("Hello world");System.out.println("""greeting  : Option[String] = """ + $show(greeting ));$skip(41); 

  val greeting2: Option[String] = None;System.out.println("""greeting2  : Option[String] = """ + $show(greeting2 ));$skip(407); 

  /**
   * However, time and again you will need to interoperate with Java libraries or code in other JVM languages that happily make use of null to denote absent values.
   * For this reason, the Option companion object provides a factory method that creates None if the given parameter is null,
   * otherwise the parameter wrapped in a Some:
   */

  val absentGreeting: Option[String] = Option(null);System.out.println("""absentGreeting  : Option[String] = """ + $show(absentGreeting ));$skip(57); 
  val presentGreeting: Option[String] = Option("Hello!");System.out.println("""presentGreeting  : Option[String] = """ + $show(presentGreeting ));$skip(57); 
  // Some test
  val NullGreeting: Option[String] = null

  //val NotNullGreeting: Option[String] = "Hello!"
  //type mismatch;  found   : String("Hello!")  required: Option[String]

  /**
   * Working with optional values
   * This is all pretty neat, but how do you actually work with optional values? It’s time for an example.
   * Let’s do something boring, so we can focus on the important stuff.
   * Imagine you are working for one of those hipsterrific startups, and one of the first things you need to implement is a repository of users.
   * We need to be able to find a user by their unique id. Sometimes, requests come in with bogus ids. This calls for a return type of Option[User]
   * for our finder method. A dummy implementation of our user repository might look like this:
   */

  case class User(
    id:        Int,
    firstName: String,
    lastName:  String,
    age:       Int,
    gender:    Option[String])

  object UserRepository {
    private val users = Map(
      1 -> User(1, "John", "Doe", 32, Some("male")),
      2 -> User(2, "Johanna", "Doe", 30, None))
    def findById(id: Int): Option[User] = users.get(id) // See function return Type
    def findNameByID(id: Int): Int=(1)
    def findAll = users.values
  };System.out.println("""NullGreeting  : Option[String] = """ + $show(NullGreeting ));$skip(1536); 

  //Now, if you received an instance of Option[User] from the UserRepository and need to do something with it, how do you do that?

  //One way would be to check if a value is present by means of the isDefined method of your option, and, if that is the case, get that value via its get method:

  val user1 = UserRepository.findById(1);System.out.println("""user1  : Option[OptionClass.User] = """ + $show(user1 ));$skip(83); 
  if (user1.isDefined) {
    println(user1.get.firstName)
  };$skip(753);  // will print "John"

  /**
   * This is very similar to how the Optional type in the Guava library is used in Java. If you think this is clunky and expect something more elegant from Scala,
   * you’re on the right track. More importantly, if you use get, you might forget about checking with isDefined before,
   * leading to an exception at runtime, so you haven’t gained a lot over using null.   *
   * You should stay away from this way of accessing options whenever possible!
   */

  /**
   * Providing a default value
   * Very often, you want to work with a fallback or default value in case an optional value is absent.
   * This use case is covered pretty well by the getOrElse method defined on Option:
   */

  val user = User(2, "Johanna", "Doe", 30, None);System.out.println("""user  : OptionClass.User = """ + $show(user ));$skip(93); 
  println("Gender: " + user.gender.getOrElse("not specified"));$skip(719);  // will print "not specified"

  //Please note that the default value you can specify as a parameter to the getOrElse method is a by-name parameter,
  //which means that it is only evaluated if the option on which you invoke getOrElse is indeed None. Hence,
  //there is no need to worry if creating the default value is costly for some reason or another – this will only happen if the default value is actually required.

  /**
   * Pattern matching
   * Some is a case class, so it is perfectly possible to use it in a pattern, be it in a regular pattern matching expression or
   * in some other place where patterns are allowed. Let’s rewrite the example above using pattern matching:
   */

  val userp = User(2, "Johanna", "Doe", 30, None);System.out.println("""userp  : OptionClass.User = """ + $show(userp ));$skip(139); 
  userp.gender match {
    case Some(gender) => println("Gender: " + gender)
    case None         => println("Gender: not specified")
  };$skip(197); 
  //Or, if you want to remove the duplicated println statement and make use of the fact that you are working with a pattern matching expression:

  val userq = User(2, "Johanna", "Doe", 30, None);System.out.println("""userq  : OptionClass.User = """ + $show(userq ));$skip(114); 
  val genderq = userq.gender match {
    case Some(gender) => gender
    case None         => "not specified"
  };System.out.println("""genderq  : String = """ + $show(genderq ));$skip(32); 
  println("Gender: " + genderq);$skip(1555); 

  /**
   * You will hopefully have noticed that pattern matching on an Option instance is rather verbose,
   * which is also why it is usually not idiomatic to process options this way.
   * So, even if you are all excited about pattern matching, try to use the alternatives when working with options.
   *
   * There is one quite elegant way of using patterns with options, which you will learn about in the section on for comprehensions, below.
   */

  /**
   * Options can be viewed as collections
   * So far you haven’t seen a lot of elegant or idiomatic ways of working with options. We are coming to that now.
   *
   * I already mentioned that Option[A] is a container for a value of type A. More precisely, you may think of it as some kind of collection –
   * some special snowflake of a collection that contains either zero elements or exactly one element of type A. This is a very powerful idea!
   *
   * Even though on the type level, Option is not a collection type in Scala, options come with all the goodness you have come to appreciate
   * about Scala collections like List, Set etc – and if you really need to, you can even transform an option into a List, for instance.
   *
   * So what does this allow you to do?
   *
   * Performing a side-effect if a value is present
   * If you need to perform some side-effect only if a specific optional value is present, the foreach method you know from Scala’s collections comes in handy:
   */

  UserRepository.findById(2).foreach(user => println(user.firstName));$skip(1336);  // prints "Johanna"

  //The function passed to foreach will be called exactly once, if the Option is a Some, or never, if it is None.

  /**
   * foreach takes a procedure — a function with a result type Unit — as the right operand. It simply applies the procedure to each List element.
   * The result of the operation is again Unit; no list of results is assembled.
   * foreach takes a procedure that doesn’t return anything, and because the result of foreach is also Unit,
   * the foreach method is typically used for its side effects
   *
   */

  /**
   * Mapping an option
   * The really good thing about options behaving like a collection is that you can work with them in a very functional way,
   * and the way you do that is exactly the same as for lists, sets etc.
   *
   * Just as you can map a List[A] to a List[B], you can map an Option[A] to an Option[B]. This means that if your instance of Option[A] is defined,
   * i.e. it is Some[A], the result is Some[B], otherwise it is None.
   *
   * If you compare Option to List, None is the equivalent of an empty list: when you map an empty List[A], you get an empty List[B],
   * and when you map an Option[A] that is None, you get an Option[B] that is None.
   *
   * Let’s get the age of an optional user:
   */

  val age = UserRepository.findById(1).map(_.age);System.out.println("""age  : Option[Int] = """ + $show(age ));$skip(136);  // age is Some(32)

  /**
   * flatMap and options
   * Let’s do the same for the gender:
   */

  val gender = UserRepository.findById(1).map(_.gender);System.out.println("""gender  : Option[Option[String]] = """ + $show(gender ));$skip(613); 

  /**
   * The type of the resulting gender is Option[Option[String]]. Why is that?
   *
   * Think of it like this: You have an Option container for a User, and inside that container you are mapping the User instance to an Option[String],
   * since that is the type of the gender property on our User class.
   *
   * These nested options are a nuisance? Why, no problem, like all collections, Option also provides a flatMap method.
   * Just like you can flatMap a List[List[A]] to a List[B], you can do the same for an Option[Option[A]]:
   */

  val gender1 = UserRepository.findById(1).flatMap(_.gender);System.out.println("""gender1  : Option[String] = """ + $show(gender1 ));$skip(79); 
  val gender2 = UserRepository.findById(2).flatMap(_.gender);System.out.println("""gender2  : Option[String] = """ + $show(gender2 ));$skip(79);  // gender is None
  val gender3 = UserRepository.findById(3).flatMap(_.gender);System.out.println("""gender3  : Option[String] = """ + $show(gender3 ));$skip(522);  // gender is None

  /**
   * The result type is now Option[String]. If the user is defined and its gender is defined, we get it as a flattened Some.
   * If either the use or its gender is undefined, we get a None.
   *
   * To understand how this works, let’s have a look at what happens when flat mapping a list of lists of strings,
   * always keeping in mind that an Option is just a collection, too, like a List:
   */

  val names: List[List[String]] =
    List(List("John", "Johanna", "Daniel"), List(), List("Doe", "Westheide"));System.out.println("""names  : List[List[String]] = """ + $show(names ));$skip(86); val res$0 = 
                                                  
  names.map(_.map(_.toUpperCase));System.out.println("""res0: List[List[String]] = """ + $show(res$0));$skip(44); val res$1 = 
  
  
  names.flatMap(_.map(_.toUpperCase));System.out.println("""res1: List[String] = """ + $show(res$1));$skip(389); 
  

  /**
   * If we use flatMap, the mapped elements of the inner lists are converted into a single flat list of strings. Obviously,
   * nothing will remain of any empty inner lists.
   *
   * To lead us back to the Option type, consider what happens if you map a list of options of strings:
   *
   */

  val namesp: List[Option[String]] = List(Some("Johanna"), None, Some("Daniel"));System.out.println("""namesp  : List[Option[String]] = """ + $show(namesp ));$skip(82); val res$2 = 
  namesp.map(_.map(_.toUpperCase));System.out.println("""res2: List[Option[String]] = """ + $show(res$2));$skip(75); val res$3 =  // List(Some("JOHANNA"), None, Some("DANIEL"))
  namesp.flatMap(xs => xs.map(_.toUpperCase));System.out.println("""res3: List[String] = """ + $show(res$3));$skip(606); val res$4 =  // List("JOHANNA", "DANIEL")

  /**
   * If you just map over the list of options, the result type stays List[Option[String]].
   * Using flatMap, all elements of the inner collections are put into a flat list: The one element of any Some[String]
   * in the original list is unwrapped and put into the result list, whereas any None value in the original list does not contain any element to be unwrapped.
   * Hence, None values are effectively filtered out.
   *
   * With this in mind, have a look again at what flatMap does on the Option type.
   */
   
   /**
   Another Example
  
   */
  List(1,2).map(UserRepository.findById);System.out.println("""res4: List[Option[OptionClass.User]] = """ + $show(res$4));$skip(45); val res$5 = 
  List(1,2).flatMap(UserRepository.findById);System.out.println("""res5: List[OptionClass.User] = """ + $show(res$5));$skip(742); val res$6 = 
   
   
   
   

  /**
   * For comprehensions
   * Now that you know that an Option can be treated as a collection and provides map, flatMap, filter and other methods you know from collections,
   * you will probably already suspect that options can be used in for comprehensions. Often, this is the most readable way of working with options, especially if you have to chain a lot of map, flatMap and filter invocations. If it’s just a single map, that may often be preferrable, as it is a little less verbose.
   *
   * If we want to get the gender for a single user, we can apply the following for comprehension:
   */

  for {
    user <- UserRepository.findById(1)
    gender <- user.gender
  } yield gender;System.out.println("""res6: Option[String] = """ + $show(res$6));$skip(561); val res$7 =  // results in Some("male")
  /**
   * As you may know from working with lists, this is equivalent to nested invocations of flatMap. If the UserRepository already returns None or the Gender is None,
   * the result of the for comprehension is None. For the user in the example, a gender is defined, so it is returned in a Some.
   *
   * If we wanted to retrieve the genders of all users that have specified it, we could iterate all users, and for each of them yield a gender, if it is defined:
   */

  for {
    user <- UserRepository.findAll
    gender <- user.gender
  } yield gender;System.out.println("""res7: Iterable[String] = """ + $show(res$7));$skip(588); val res$8 = 

  /**
   * Since we are effectively flat mapping, the result type is List[String], and the resulting list is List("male"), because gender is only defined for the first user.
   *
   * Usage in the left side of a generator
   * Maybe you remember from part three of this series that the left side of a generator in a for comprehension is a pattern.
   * This means that you can also patterns involving options in for comprehensions.
   *
   * We could rewrite the previous example as follows:
   */

  for {
    User(_, _, _, _, Some(gender)) <- UserRepository.findAll
  } yield gender

  /**
   * Using a Some pattern in the left side of a generator has the effect of removing all elements from the result collection for which the respective value is None.
   *
   * Chaining options
   * Options can also be chained, which is a little similar to chaining partial functions. To do this, you call orElse on an Option instance,
   * and pass in another Option instance as a by-name parameter. If the former is None, orElse returns the option passed to it,
   * otherwise it returns the one on which it was called.
   *
   * A good use case for this is finding a resource, when you have several different locations to search for it and an order of preference.
   * In our example, we prefer the resource to be found in the config dir, so we call orElse on it, passing in an alternative option:
   */

  case class Resource(content: String);System.out.println("""res8: Iterable[String] = """ + $show(res$8));$skip(907); 
  val resourceFromConfigDir: Option[Resource] = None;System.out.println("""resourceFromConfigDir  : Option[OptionClass.Resource] = """ + $show(resourceFromConfigDir ));$skip(95); 
  val resourceFromClasspath: Option[Resource] = Some(Resource("I was found on the classpath"));System.out.println("""resourceFromClasspath  : Option[OptionClass.Resource] = """ + $show(resourceFromClasspath ));$skip(68); 
  val resource = resourceFromConfigDir orElse resourceFromClasspath;System.out.println("""resource  : Option[OptionClass.Resource] = """ + $show(resource ))}

  /**
   * This is usually a good fit if you want to chain more than just two options – if you simply want to provide a default value in case a given option is absent,
   * the getOrElse method may be a better idea.
   */

}
