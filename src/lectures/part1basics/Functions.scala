package lectures.part1basics

import scala.reflect.internal.util.TriState.False

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  //My attempt before video -- NOTICE DIDN'T NEED "RETURN"
  def concatString(str: String, times: Int): String = {
    if(times > 0)
      return str + " " + concatString(str, times - 1)
    return ""
  }
  println(concatString("test", 5))

  //From video
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }
  println(aRepeatedFunction("hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int) = a + b
    aSmallerFunction(n, n-1)
  }

  /*
    1. a greeting function (name, age) => "Hi, my name is $name and I am $age years old"
    2. Factorial function product of all numbers up to N
    3. A Fibonacci function
      f(1) = 1
      f(2) = 1
      f(n) = f(n-1) + f(n-2)
    4. tests if a number is prime.
   */

  // Me: 1.
  def Greeting(name: String, Age: Int) = {
    println("Hi, my name is " + name + " and I am " + Age + " years old")
  }
  Greeting("Brian", 40)
  // Class: 1
  def greetingforKids(name: String, Age: Int) =
    "Hi, my name is " + name + " and I am " + Age + " years old"
  println(Greeting("david", 12))

  // 2. me
  def Factorial(num: Int): Int = {
    if(num <= 1) 1
    else num * Factorial(num - 1)
  }
  println(Factorial(5)) //expected 120 -- success!

  // 2. class
  def factorial(n: Int): Int = {
    if(n <= 0) 1
    else n * factorial(n - 1)
  }
  println(factorial(5)) //expected 120 -- success!


  // 3. me
  def Fibonacci(n: Int): Int = {
    if(n == 1) 1
    else if(n == 2) 1
    else Fibonacci(n - 1) + Fibonacci(n - 2)
  }
  println(Fibonacci(4))

  // 3. them
  def fibonacci(n: Int): Int = {
    if(n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(4))

  // 4. me
  def IsPrime(n: Int): Boolean = {
    def TestPrime(dividend: Int, divisor: Int): Boolean = {
      if(divisor > 1) {
        if (dividend % divisor != 0)
          TestPrime(dividend, divisor - 1)
        else
          true
      }
      else
        false
    }
    TestPrime(n, n - 1)
  }
  println(IsPrime(8))

  // 4. class
  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }
  println(isPrime(8))
  println(isPrime(13))


}
