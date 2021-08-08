package lectures.part1basics

import scala.annotation.tailrec

object RecursionRework1 extends App {
  //string n times
  def stringTimes(str: String, times: Int): String = {
    @tailrec
    def stringTimesHelper(I: Integer, accumulator: String): String = {
      if (I <= 0) accumulator
      else stringTimesHelper(I - 1, str + accumulator)
    }
    stringTimesHelper(times, "")
  }
  println(stringTimes("test ", 5))


  //factorial
  def factorial(num: Integer): Integer = {
    @tailrec
    def factorialHelper(I: Integer, accumulator: Integer): Integer ={
      if(I <= 1) return accumulator
      else factorialHelper(I - 1, I * accumulator)
    }
    factorialHelper(num, 1)
  }
  println(factorial(5))

  //is prime
  def isPrime(num: Integer): Boolean = {
    @tailrec
    def isPrimeHelper(I: Integer, isStillPrime: Boolean): Boolean ={
      if(I <= 2) isStillPrime
      else isPrimeHelper(I-1, isStillPrime && num % I != 0)
    }
    isPrimeHelper(num/2, true)
  }
  println(isPrime(6))
  println(isPrime(13))

  def easyFibonacci(num: Integer): Int = {
    if(num <= 2) 1
    else easyFibonacci(num-1) + easyFibonacci(num - 2)
  }

  //fibonacci
  def fibonacci(num: Integer): Int = {
    @tailrec
    def fibonacciHelper(I: Integer, last: Integer, priorLast: Integer): Int = {
      if(I >= num) last + priorLast
      else fibonacciHelper(I + 1, last + priorLast, last)
    }
    if(num <= 2) 1
    else fibonacciHelper(3, 1, 1)
  }
  fibonacci(1)
  fibonacci(2)
  fibonacci(3)
  fibonacci(4)

  println(fibonacci(1)) //1
  println(fibonacci(2)) //1
  println(fibonacci(3)) //2
  println(fibonacci(4)) //3
  println(fibonacci(5)) //5
  println(fibonacci(6)) //8
}
