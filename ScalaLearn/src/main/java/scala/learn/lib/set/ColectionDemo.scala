package scala.learn.lib.set

object ColectionDemo {
  def main(args: Array[String]): Unit = {
    //Paralllel collection 多运行几次，注意打印顺序会有不同
    //(1 to 15).par foreach println
    iteratorTest()
  }
  def iteratorTest(): Unit = {
    // 1、使用while
    val it = Iterator(1,3,5,7)  //val it = List(1, 3, 5, 7).iterator

    while (it.hasNext) println(it.next)

    // 2、使用for

   for (e <- Iterator(1, 3, 5, 7)) println(e)

    // 3、使用foreach

   Iterator(1, 3, 5, 7) foreach println
   
   //Iterator也可以使用map的方法：

Iterator(1,3,5,7) map (10*) toList // List(10, 30, 50, 70)

Iterator(1,3,5,7) dropWhile (5>) toList // List(5,7)
  }
}