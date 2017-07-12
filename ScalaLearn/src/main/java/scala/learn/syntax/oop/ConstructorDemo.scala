package scala.learn.syntax.oop
object ConstructorDemo {
  class c1(name: String, age: Int) { // (1)直接在类定义处

    def this() { this("anonymous", 20) } // (2)用this定义

    def m1() = { printf("%s=%d\n", name, age) }

  }
  class c2(name:String, age:Int, female:Boolean=false) extends c1(name,age) {

    override def toString = { name + "," +  age + "," + female }

}

  def main(args: Array[String]) = {

    new c1().m1()
    new c1("qh", 30).m1()
    printf(new c2("zhang3",23,true).toString())

  }
}
class ConstructorDemo {

}