package lectures.part3fp

object  WhatsAFunction extends App {

  // DREAM: use function as as first class elements
  // problem: oop

  val doubler = new MyFunction[Int, Int] {
   override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("10") + 4)

  val adder: (Int,Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a+b
  }

  // function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function which takes an argument int and returns another function which takes an int and returns an int
      - what's the type of this function
      - how to do it
   */
  // 1.
  val strAdder = (a: String, b: String) => a + b

  // 3.
  val func = new (Int => (Int => Int)) {
    override def apply(v1: Int): (Int) => Int = {
      A => A + v1
    }
  }

  println(func(6)(5)) //curried function


  println(strAdder("test ", "string"))

}

trait MyFunction[A, B] {
  def apply(element: A): B = ???
}
