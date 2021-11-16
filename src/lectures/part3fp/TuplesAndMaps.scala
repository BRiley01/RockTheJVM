package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  var aTuple = new Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  var aTupleB = Tuple2(2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)
  var aTupleC = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1)  // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap)  // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a, b)
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  println(phoneBook("JimX"))  // without "withDefaultValue" Throws exception is not exists

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  //functions on maps
  // map, flatMap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phoneBook.mapValues(number => "0245-" + number * 10).toMap)

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if in "toLowerCase map above" I had 2 original entries "Jim" => 555 and "JIM" -> 900

      !!! Careful with mapping keys.

    2. overly simplified social network based on maps
      Person = String
      - add a person to the network
      - remove
      - friend (mutual)
      - unfriend

      - number of friends of a given person
      - person with most friends
      - how may people have no friends
      - if there is a social connection between two people (direct or not)
   */

  //1.
  val TwoJims = phoneBook + ("JIM" -> 900)
  println(TwoJims)
  println(TwoJims.map(pair => pair._1.toLowerCase -> pair._2))
  //since "jim" already exists the second time, it finds the existing key and updates the value

  //2.


}
