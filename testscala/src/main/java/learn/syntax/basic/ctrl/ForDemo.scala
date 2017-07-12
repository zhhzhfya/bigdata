/**
 *
 */
package learn.syntax.basic.ctrl

/**
 * @author Administrator
 *
 */
object ForDemo {
  def main(args: Array[String]): Unit = {

    List(1, 2, 3).foreach(print)
    println()
    (1 to 3).foreach(print)
    (11 until 14) foreach print
    Range(20,23) foreach print
  }
  
   /*循环while*/
  //  while(true){
  //    
  //  }

  /*for的基本形态*/
  //  for(int i = 0 ; i < 10 ; i ++ ){}
  //  for(i : List){}

  //  println(1 to 10)
  //  b1 * a1
  //  for(i <- (1 until 10).reverse){
  //    println(i)
  //  }

  /*高级for循环*/
  //  for (i <- 1 until 10 reverse ; if i % 3 == 0) {  //守卫
  //    println(i)
  //  }

  //  for (i <- 1 until (10,3) ) {  //修改步长
  //    println(i)
  //  }

  //双重for循环
  //  for (i <- 1 until 10 ; j <- 1 until 5) { 
  //    println(i + "->" + j)
  //  }

  //for的推导式
  var c = for (i <- 1 until 10)yield (i * 2 - 1)
  println(c)
}