package part4pm

object BracelessSyntax {

  // if - expressions
  val anIfExpress = if(2 > 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2>3) {
      "bigger"
    } else {
      "smaller"
    }

  //compact style
  val anIfExpression_v3 =
    if(2>3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpression_v4 =
    if 2>3 then
      "bigger" // higher indentation than the if part
    else
      "smaller"

  val anIfExpression_v5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpress_v6 = if 2 > 3 then "bigger" else "smalle"

  // for comprehensions


  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)
  }

}
