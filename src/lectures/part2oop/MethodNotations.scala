package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int) {
    def likes(movie: String): Boolean = movie == favoriteMovie
    def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(title: String): Person = new Person(s"${name} (${title})", favoriteMovie, age)
    def unary_! : String = s"$name, what the heck?"
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def learns(subject: String) : String = s"${name} learns ${subject}"
    def learnsScala : String = this learns "Scala"
    def isAlive : Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    def apply(num: Int): String = s"$name watched $favoriteMovie $num times"
  }

  val mary = new Person("Mary", "Inception", 30)
  println(mary.likes("Inception"))
  println(mary likes "Inception") //equivalent
  //infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club", 26)
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1+2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // prefix notation
  val x = -1 //equivalent with 1.unary_-
  val y = 1.unary_-
  //unary_ prefix only works with

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) //equivalent

  /*
    1.  Overload the + operator
      mary + "The rockstar" => new person "MAry (the rockstar)"*/
println((mary + "the rockstar").name)
  println((mary + "the rockstar")())
  println((mary + "the rockstar").apply())

    /*
    2.  Add an age to the Person class
        Add a unary + operator => new person with the age + 1
        +mary => mary with the age incrementer*/
  println((+mary).age)

/*
    3.  Add a "learns" method in the Person class => "Mary learns Scala"
        Add a learnsScala method, calls learn method with "Scala".
        Use it in postfix notation */
  println(mary learnsScala)

/*
    4.  Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"
   */

  println(mary(2))
  println(mary.apply(2))
}
