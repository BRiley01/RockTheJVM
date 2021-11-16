package lectures.part3fp

import scala.util.Random

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None
  println(myFirstOption)
  println(noOption)

  // unsafe APIs
  def unsafeMethod(): String = null
  val result = Some(unsafeMethod()) // WRONG
  val potentialResult = Some(null)  // Some should NEVER return NULL
  val correctResult = Option(unsafeMethod()) //Builds Some or None
  println(correctResult)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod: Option[String] = Some("A valid result")
  val betterChainedResult = betterBackupMethod orElse betterBackupMethod

  // function on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get)  // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
    Exercise.
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
// (my attempt - though the problem statement was a bit vauge)
  val hosta = config.get("host")
  val porta = config.get("port")
  if(!hosta.isEmpty && !porta.isEmpty) {
    var conn: Option[Connection] = None
    do {
      conn = Connection(hosta.get, porta.get)
    }while(conn.isEmpty)
    println("A:" + conn.get.connect)
  }

// course solution
  val host = config.get("host")
  val port = config.get("port")
  /*
  if(h != null)
    if(p != null)
      return Connection.apply(h,p)
  return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    if (c != null)
      return c.connect
    return nul
   */
  val connectionStatus = connection.map(c => c.connect)
  // if(connectionStatus == null) println(None) else print(Some(connectionStatus.get))
  println("B:" + connectionStatus)
  /*
    if(status != null)
      println(status)
   */
  print("C:")
  connectionStatus.foreach(println)
  println()

  // chained calls
  print("D:")
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)
  println()

  // for-comprehensions
  print("E:")
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
