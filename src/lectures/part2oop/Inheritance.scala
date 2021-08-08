package lectures.part2oop

object Inheritance extends App {

  sealed class Animal { //prevents other types of animals from being created besides cat and dog
    val creatureType = "Wild"
    /*protected*/ def eat = println("nomnom")
  }

  final class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding can be done in constructor or in class - works the same way
  class Dog(override val creatureType: String) extends Animal {
    //override val creatureType = "domestic"
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // super

  // preventing overrides
  // 1 - use final on member
  // 2 - use final on class
  // 3 - seal the class = extend classes in THIS FILE, prevents extension in other files
}
