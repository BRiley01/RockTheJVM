package lectures.part3fp

object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
  val doubler = (x: Int) => x * 2

  // multiple params in  lambda
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) //Function name -- function itself
  println(justDoSomething()) //calls function

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR (type-o?) syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b

  /*
    1. MyList: replace all FunctionX calls with lambdas
    2. Rewrite the "special" adder as an anonymous function
   */

  //Me
  val special: Int => Int => Int = A => _ + A
  println(special(5)(3))

  // class
  var superAdd = (x: Int) => (y: Int) => x + y
  println(special(2)(2))
}

