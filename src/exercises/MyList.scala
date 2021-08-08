package exercises


/*val stringToIntConverter = new Function1[String, Int] {
  override def apply(string: String): Int = string.toInt
}*/

trait MyPredicate[-T] {
  def test(item: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(item: A) : B
}

abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B] = Cons(element, this)
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
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

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}


case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  /*def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements*/

  def map[B](f: MyTransformer[A, B]): MyList[B] = Cons(f.transform(head), tail.map(f))
  def filter(f: MyPredicate[A]): MyList[A] = {
    if(f.test(head)) Cons(head, tail.filter(f))
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
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
    transformer.transform(head) ++ tail.flatMap(transformer)
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

  println(updated.map(new MyTransformer[Int, Int] {
    override def transform(item: Int) : Int = item * 2
  }))

  println(updated.filter(new MyPredicate[Int] {
    override def test(item: Int): Boolean = (item % 5) == 0
  }))

  println(updated.flatMap(new MyTransformer[Int, MyList[Int]] {
    def transform(item: Int) : MyList[Int] = new Cons[Int](item, new Cons[Int](item+1, Empty))
  }))

  println(listOfIntegers == cloneListOfIntegers)
}

