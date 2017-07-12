package scala.learn.syntax.oop

class User {
  var name = "anonymous"
  var age: Int = _

  val country = "china"
  
  def email = name + "@mail"
  
  override def toString() = {
    "name：\t"+name
  }
}
object User {
  def main(args: Array[String]): Unit = {
    val u = new User
    // var定义的属性可读可写
    u.name = "qh"; u.age = 30

    println(u.name + ", " + u.age) // "qh, 30"
    println(u)
  }
}