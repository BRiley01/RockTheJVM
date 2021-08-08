package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    // Won't work: def add(element: A): MyList[A] = ???
    def add[B >: A](element: B): MyList[B] = ??? //must be a super type and then recast the list
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfString  = new MyList[String]

  //generic methods
  object MyList { //Objects cannot be type parameterized
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Variance: if B extends A, should List[B] extend List[A]?

  // 1. yes List[cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION. => we return a list of Animals

  // 2. NO = INVARIANCE
  class InvariantList[A]
  //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] //This won't work
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  /*
  won't work
  class Car
  val newCage = new Cage(new Car)
  */

  // expand MyList to be generic

}
