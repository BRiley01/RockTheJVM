package lectures.part2oop

import scala.language.postfixOps

object Objects {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  // objects do not receive parameters
  object Person { // type + its only instance
    // "Static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    //factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) {
    //instance-level functionality
  }
  // COMPANIONS

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john) //false

    var person1 = Person
    val person2 = Person
    println(person1 == person2) //true

    val bobbie = Person(mary, john)

    // Scala applications = Scala object with
    // def main(args: Array[String]): Unit
  }


}
