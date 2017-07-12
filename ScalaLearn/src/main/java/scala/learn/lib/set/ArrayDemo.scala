package scala.learn.lib.set

/**
 *数组演示
 * @author xuefeng
 */
object ArrayDemo {
  def main(args: Array[String]): Unit = {
    //定长数组
    val numbers = Array(1, 2, 3, 4)
    val first = numbers(0) // read the first element
    numbers(3) = 100 // replace the 4th array element with 100
    val biggerNumbers = numbers.map(_ * 2) // multiply all numbers by two
    
    println(numbers.mkString("@"))
    
    //遍历
    for (i <- 0 until numbers.length){
      println(numbers(i))
    }
    //val a=numbers((_: Int)+(_: Int))
    
    //变长数组
      
    
  }
}