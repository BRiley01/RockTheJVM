package lectures.part2oop

object OOBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  println(person.greet("Brian"))
  println(person.greet)

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author)) //true
  println(novel.isWrittenBy(imposter)) //false

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
}

//constructor
class Person(name: String, val age: Int) {
  //body
  val x = 2

  println(1 + 3)

  //method
  def greet(name: String): Unit = println(s"${this.name} says: hi, $name")

  //overloading
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors
  //Only good for passing default parameters??
  def this(name: String) = this(name, 0)

  def this() = this("John Doe")
}

  /*
    Novel and a Writer

    Writer: first name, surname, year
      - method fullname

     Novel: name, year of release, author
      - authorAge
      - isWrittenBy
      - copy (new year of release) = new instance of Novel
   */
  class Writer(firstname: String, surname: String, val birthYear: Int) {
    def fullName(): String = firstname + " " + surname
  }

  class Novel(name: String, year: Int, author: Writer) {
    def authorAge: Int = year - author.birthYear
    def isWrittenBy(test: Writer) = author == test
    def copy(year: Int): Novel = new Novel(name, year, author)
  }


  /*
    counter class
      - received an int value
      - method current count
      - method to increment/decrement => new Counter
      - overload in/dec to recieve an amoujnt
   */
  class Counter(val counter: Int = 0) {
    def inc = {
      println("incrementing")
      new Counter(counter + 1)            // immutability
    }
    def dec = {
      println("decrementing")
      new Counter(counter + 1)            // immutability
    }

    def inc(by: Int): Counter = {
      if(by <= 0) this
      else inc.inc(by - 1)
    }

    def dec(by: Int): Counter = {
      if(by <= 0) this
      else dec.dec(by - 1)
    }

    def print = println(counter)
  }


// class parameters are NOT FIELDS
