package lectures.part2oop

//import playground._//{Cinderella, PrinceCharming}
import playground.{PrinceCharming, Cinderella => Princess}

import java.sql
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  val writer = new Writer("Brian", "RockTheJVM", 2018)

  // import the package
  val princess = new Princess // playground.Cinderella fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  //imports
  val prince = new PrinceCharming

  // 1. Use FQ names
  val date = new Date()
  val sqlDate1 = new java.sql.Date(2018, 5, 4)
  val sqlDate2 = new SqlDate(2018, 5, 4)

  // 2. use aliasing

  // default imports
  // java.lang - String, Objects, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
