package learn.syntax.basic.variable

/**
 * @author Administrator
 */
object TupleTest extends App {
  val t = (1, 3.14, "Fred") // 类型为Tuple3[Int, Double, java.lang.String]
  // 访问组元t._1  t._2 等方法，注意是从1开始的
  // 不过通常是使用模式匹配来获取组元的
  val (first, second, third) = t
  // 如果说不是所有的组元都需要，那么在不需要的地方放上_
  //val (first, second, _) = t
  //有了这个，可以方便地返回多个值，让我想起了Go。

  //zip操作
  val symbols = Array("<", "-", ">")
  val counts = Array(2, 10, 2)
  val pairs = symbols.zip(counts)
  // 得到：Array(("<", 2), ("-", 10), (">", 2))
  // 可以使用toMap方法将对偶的集合转换成Map
  //keys.zip(values).toMap  // 可想而知，这个集合需要是有序排列的
}