package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if(n <= 1) 1
    else{
      println("computing factorial of " + n + " - I first need factorial of " + (n-1))
      n * factorial(n-1)
      val result = n * factorial(n-1)
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  //println(factorial(5000))

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x < 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION = use recursive call as the LAST expression

    factHelper(n, 1)
  }

  /*
    anotherFactoria(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 1 * 2, 3 * 4 * ... * 10)
    = 1 * 2, 3 * 4 * ... * 10
   */
  //println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS, USE __TAIL__ RECURSION

  /*
    1. Concatenate string n times using tail recursion
    2. IsPrime function tail recursive
    3. Fibonacci tail recursion
   */

  // 1. Me:
  def concatString(str: String, times: Int): String = {
    @tailrec
    def counterHelper(x: Int, fullStr: String): String ={
      if(x == 0) fullStr
      else counterHelper(x - 1, str + fullStr)
    }
    counterHelper(times, "")
  }
  println(concatString("test", 5))

  // 2. Me:
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }
  println(isPrime(8))
  println(isPrime(13))

  // 3. Me: Can't be done?
  /*@tailrec
  def fibonacci(n: Int): Int = {
    if(n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }
  println(fibonacci(4))*/

  // 3. them:
  def fibonacci(n: Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if( i >= n) last
      else fiboTailrec(i+1, last + nextToLast, last)

    if(n <= 2)1
    fiboTailrec(2, 1, 1)
  }
  println(fibonacci(8))

}
