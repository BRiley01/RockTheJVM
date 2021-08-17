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
  def foreach(func: A => Unit): Unit
  def sort(func: (A, A) => Int): MyList[A]
  def zipWith[B, C](lst: MyList[B], func: (A, B) => C): MyList[C]
  def fold[B](start: B)(func: (B, A) => B): B

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
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(func: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](lst: MyList[B], func: (Nothing, B) => C): MyList[C] =
    if (!lst.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Empty
  def fold[B](start: B)(func: (B, Nothing) => B): B = start

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
  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }
  def sort(func: (A, A) => Int): MyList[A] = {
    val sortedTail = t.sort(func)
    if(!tail.isEmpty && func(sortedTail.head, head) > 0)
      Cons(sortedTail.head, Cons(head, sortedTail.tail).sort(func))
    else
      Cons(head, sortedTail)
  }
  def zipWith[B, C](lst: MyList[B], func: (A, B) => C): MyList[C] = {
    if(lst.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Cons(func(head, lst.head), tail.zipWith(lst.tail, func))
  }

  /*
  [1,2,3].fold(0)(+) =
  = [2,3].fold(1)(+) =
  = [3].fold(3)(+) =
  = [].fold(6)(+)
  = 6
   */
  def fold[B](start: B)(func: (B, A) => B): B = {
    tail.fold(func(start, h))(func)
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
  val anotherListOfIntegers: MyList[Int] = Cons(1, Cons(2, Empty))

  println(listOfIntegers)
  println(listOfStrings)

  val lst = new Cons[Int](5, Empty)
  val updated = lst.add(7).add(16).add(-5)
  println(s"updated: ${updated}")
  println(Empty.isEmpty)
  println(lst.isEmpty)
  println(updated.isEmpty)

  println(updated.map(_ * 2))

  println(updated.filter(_ % 5 == 0))

  println(updated.flatMap(A => new Cons[Int](A, new Cons[Int](A+1, Empty))))

  println(listOfIntegers == cloneListOfIntegers)

  updated.foreach((i => println(s"foreach: ${i}")))

  val sorted = updated.sort((x, y) => y-x)
  println(sorted)

  val zipped = Cons(1, Cons(2, Cons(3, Empty))).zipWith[Int, Int](Cons(4, Cons(5, Cons(6, Empty))), (x, y) => x*y)
  println(zipped)

  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  val folded = Cons(1, Cons(2, Cons(3, Empty))).fold(0)((a, b) => a + b)
  println(folded)

  val forCombination = for {
    n <- listOfIntegers
    string <- listOfStrings
  } yield n + "-" + string
  println(forCombination)

}

