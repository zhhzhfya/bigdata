package learn.syntax.basic.variable

/**
 * @author Administrator
 */
object MapDemo extends App {
  //map中的键值称为对偶

  // 创建一个内容不可变的Map[String, Int],可以添加对偶，但是不能改变对偶的值
  val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  // 创建一个内容可变的Map[String, Int]
  val scores2 = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  scores2("Cindy") = 888
  println(scores2("Cindy"))

  //想要添加多个对偶，则使用+=操作：
  scores2 += ("Bob" -> 10, "Fred" -> 7)
  //移除某个对偶使用-=：
  scores2 -= "Alice"

  // 创建一个空Map[String, Int]
  //val scores3 = new scala.collection.mutable.Map[String, Int]

  // 替换掉->操作符
  val scores4 = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))

  //获取值
  println(scores("Bob"))
  //如果Map中没有包含这个键值对，会抛出一个异常。用 contains()方法来检查是否包含指定的键。
  val bobsScore = if (scores.contains("Bob")) scores("Bob") else 0
  //由于这个使用方法很多，所以有一个更加快捷的写法
  //val bobsScore = scores.getOrElse("Bob", 0)
  println(bobsScore)

  //迭代Map
  for (i <- scores) {
    println(i)
  }
  for ((k, v) <- scores) {
    println(k + "->" + v)
  }

  for ((k, v) <- scores) {}
  // 单独拿出键
  for (k <- scores.keySet) {}
  // 单独拿出值
  for (v <- scores.values) {}

  //与Java的互操作与数组中类似，引入函数后就可以触发转换。
  // 从Java Map到Scala Map，适用于可变树形映射
  import scala.collection.JavaConversions.mapAsScalaMap
  // 从Java Properties到Scala Map
  import scala.collection.JavaConversions.propertiesAsScalaMap
  // 从Scala Map到Java Map
  import scala.collection.JavaConversions.mapAsJavaMap
}