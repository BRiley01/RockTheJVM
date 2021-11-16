package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod: String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod))
  print(fallbackTry)

  // IF you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFailback = betterUnsafeMethod orElse betterBackupMethod

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))
  // => for-comprehensions

  /*
    Exercise
   */
  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if(random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  Try(HttpService.getConnection(hostname, port))
    .map(c =>
      Try(c.get("test.html"))
        .foreach(p => renderHTML(p))
    )

  // for-comprehension with yield
  val pageHtml = for {
    connection <- Try(HttpService.getConnection(hostname, port))
    page <- Try(connection.get("test.html"))
  } yield page
  pageHtml.foreach(renderHTML)

  // for-comprehension
  for {
    connection <- Try(HttpService.getConnection(hostname, port))
    page <- Try(connection.get("test.html"))
  } renderHTML(page)
}
