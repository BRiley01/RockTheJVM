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
  val strAdder: (String,String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a+b
  }

  println(strAdder("test ", "string"))

}

trait MyFunction[A, B] {
  def apply(element: A): B = ???
}
