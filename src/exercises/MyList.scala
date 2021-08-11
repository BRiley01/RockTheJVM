package exercises


/*val stringToIntConverter = new Function1[String, Int] {
  override def apply(string: String): Int = string.toInt
}*/

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B] = Cons(element, this)

  // higher-order functions
  def map[B >: A](transformer: A => B): MyList[B]
  def flatMap[B >: A](transformer: A => MyList[B]): MyList[B]
  def filter[B >: A](predicate: A => Boolean): MyList[B]

  //concat
  def ++[B >: A](list: MyList[B]): MyList[B]
  //def printElements: String
  // polymorphic call
  //override def toString: String = s"[${printElements}]"
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  //def printElements: String = ""

  def map[B >: Nothing](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B >: Nothing](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter[B >: Nothing](predicate: Nothing => Boolean): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def map[B >: A](transformer: A => B): MyList[B] = Cons(transformer(head), tail.map(transformer))
  def filter[B >: A](f: A => Boolean): MyList[A] = {
    if(f(head)) Cons(head, tail.filter(f))
    else tail.filter(f)
  }

  /*
    [1,2] ++ [2,3,4]
    = new Cons(1, [2] ++ [3,4,5])
    = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, empty)))))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = Cons(head, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
    = [1,2] ++ [2].flatMap(n => [n, n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1]
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  def flatMap[B >: A](transformer: A => MyList[B]): MyList[B] =
    transformer(head) ++ tail.flatMap(transformer)
}

object ListTest extends App {
  val listOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val cloneListOfIntegers: MyList[Int] = Cons(1, Cons(2, Cons(3, Empty)))
  val listOfStrings: MyList[String] = Cons("Hello", Cons("Scala", Empty))

  println(listOfIntegers)
  println(listOfStrings)

  val lst = new Cons[Int](5, Empty)
  val updated = lst.add(7).add(16).add(-5)
  println(updated)
  println(Empty.isEmpty)
  println(lst.isEmpty)
  println(updated.isEmpty)

  println(updated.map(_ * 2))

  println(updated.filter(_ % 5 == 0))

  println(updated.flatMap(A => new Cons[Int](A, new Cons[Int](A+1, Empty))))

  println(listOfIntegers == cloneListOfIntegers)
}

