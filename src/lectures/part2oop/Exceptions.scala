package lectures.part2oop

import scala.math.abs

object Exceptions extends App {

  val x: String = null
  //println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing and catching exceptions

  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("no int for you!")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // options
    // does not influence the return type of this expression
    // use for things like logging and sideeffects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  //throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with a SOError
    3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiple(x, y)
        - divide(x, y)

        Throw
          -OverflowException if add(x,y) exceeds Int.MAX_VALUe
          -UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          -MathCalculationException for dividion by 0
   */

  //  1. Crash your program with an OutOfMemoryError
  // this caused heap overflow
  /*def outOfMemoryError(str: String): Unit = {
    val newStr = str + str
    outOfMemoryError(newStr)
  }
  outOfMemoryError("1234567890")*/
  //  OOM
  // val array = Array.ofDim(Int.MaxValue)

  //  2. Crash with a SOError
  /*def StackOverflow(): String = {
    StackOverflow()
    "test"
  }
  StackOverflow()*/

  /*  3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiple(x, y)
        - divide(x, y)

        Throw
          -OverflowException if add(x,y) exceeds Int.MAX_VALUe
          -UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          -MathCalculationException for division by 0
   */
  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception("Division by 0")
  /*object Calculator {
    def add(x: Int, y: Int): Int = {
      if(x < 0 && y >= 0) subtract(y, -x)
      else if(y < 0 && x >= 0) subtract(x, -y)
      else if (abs(x) > abs(Int.MaxValue-y)) throw new OverflowException
      else x + y
    }
    def subtract(x: Int, y: Int): Int = {
      if(Int.MinValue - x <= y ) throw new UnderflowException
      else x - y
    }
    def multiple(x: Int, y: Int): Int = {
      if(Int.MaxValue / x <= y) throw new OverflowException
      else x * y
    }
    def divide(x: Int, y: Int): Int = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }
  println(Calculator.add(2, 6))
  println(Calculator.add(Int.MaxValue - 10, 9))
  println(Calculator.add(Int.MaxValue - 10, 10))
  println(Calculator.add(10,Int.MaxValue - 10))
  //println(Calculator.add(Int.MaxValue - 10, 11)) (correct)
  println(Calculator.add(Int.MinValue, -1))
  println(Calculator.subtract(20, 10))
  //println(Calculator.subtract(Int.MinValue, 1))
  println(Calculator.multiple(2, 5))
  //println(Calculator.multiple(Int.MaxValue / 3, 5))
  //println(Calculator.multiple(Int.MaxValue / 3, -5))
  println(Calculator.divide(10, 2))
  //println(Calculator.divide(10, 0))*/

  //them:
  object PocketCalculator {
    def add(x: Int, y: Int) = {
      var result = x + y

      if(x > 0 && y > 0 && result < 0) throw new StackOverflowError()
      else if(x < 0 && y < 0 && result > 0) throw new UnderflowException
      result
    }
    def subtract(x: Int, y: Int): Unit = {
      val result = x-y
      if(x > 0 && y<0 && result < 0) throw new OverflowException
      else if (x<0 && y>0 && result > 0) throw new UnderflowException
      else result
    }
    def multiple(x: Int, y: Int) = {
      val result = x * y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if(x < 0 && y < 0 && result < 0) throw new OverflowException
      else if(x > 0 && y < 0 && result > 0) throw new OverflowException
      else if(x < 0 && y > 0 && result > 0) throw new OverflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if(y == 0) throw new MathCalculationException
      else x / y
    }
  }
  //println(PocketCalculator.multiple(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
  //println(PocketCalculator.multiple(Int.MaxValue, Int.MinValue))

}
