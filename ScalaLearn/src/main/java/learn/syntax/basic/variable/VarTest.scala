package learn.syntax.basic.variable

object VarTest extends App {
  
  var d: Double = _ // d = 0.0

  var i: Int = _ // i = 0

  var s: String = _ // s = null

  //var t: T = _ // 泛型T对应的默认值
  
  
  val pi = 3. // 相当于3.0d
  val pi2 = 3.f // 相当于3.0f

 val x,y = 0 // 赋同一初始值
  val (v1, v2) = (10, "hello")
  println(x + y)
  
  val regex = "(\\d+)/(\\d+)/(\\d+)".r

  
  val Array(a, b, _, _, c @ _*) = Array(1, 2, 3, 4, 5, 6, 7)  // 也可以用List，Seq

a // 1

b // 2

c // Array(5, 6, 7), _*匹配0个到多个这个也太花了吧！

val regex(year, month, day) = "2010/1/13"

// year: String = 2010

// month: String = 1

// day: String = 13
  

}