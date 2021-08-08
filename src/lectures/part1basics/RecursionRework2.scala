package lectures.part1basics

import scala.annotation.tailrec

object RecursionRework2 extends App {
  //string n times
  def stringNTimes(str: String, times: Int) = {
    @tailrec
    def stringNTimesHelper(i: Int, Accumulator: String): String =
      if(i == 0) Accumulator
      else stringNTimesHelper(i-1, str + Accumulator)

    stringNTimesHelper(times, "")
  }
  println(stringNTimes("test ", 5))

  //factorial
  def factorial(num: Int): Int = {
    @tailrec
    def factorialHelper(i: Int, Accumulator: Int): Int =
      if(i <= 1) Accumulator
      else factorialHelper(i - 1, Accumulator * i)

    factorialHelper(num, 1)
  }
  println(factorial(5)) //expect 120

  //isprime
  def isPrime(num: Int): Boolean = {
    @tailrec
    def isPrimeHelper(i: Int, stillPrime: Boolean): Boolean = {
      if (i == 1) stillPrime
      else isPrimeHelper(i - 1, stillPrime && (num % i != 0))
    }
    isPrimeHelper(num / 2, true)
  }
  println(isPrime(3))
  println(isPrime(6))
  println(isPrime(13))

  //fibonacci
  def fibonacci(num: Int): Int = {
    @tailrec
    def fibonacciHelper(i: Int, last: Int, nextLast: Int): Int = {
      if (i == num) last + nextLast
      else fibonacciHelper(i + 1, last + nextLast, last)
    }
    if(num <= 2) 1
    else fibonacciHelper(3, 1, 1)
  }
  println(fibonacci(1)) //1
  println(fibonacci(2)) //1
  println(fibonacci(3)) //2
  println(fibonacci(4)) //3
  println(fibonacci(5)) //5
  println(fibonacci(6)) //8
}
