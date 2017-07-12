package learn.syntax.mid.fp

/**
 * 函数式编程
 */
object FPDemo {
  def main(args: Array[String]): Unit = {
    //函数和方法一般用def定义；也可以用val定义匿名函数，或者定义函数别名
    def m0(x: Int) = x * x

    val m1 = (x: Int) => x * x // 用(), ()是必须的

    val m2 = { x: Int => x * x } // 用{}, {}是必须的

    print(m0(10)) // 100

    m1(10) // 100

    m2(10) // 100
    //不需要返回值的函数
    def f() { return "hello world" }

    f() // Unit，而不是 "hello world"

    //需要返回值的函数，用 def f() = {...} 或者 def f = {...}

    def f1() = { "hello world" }

    f() // "hello world"

    def f2 = { "hello world" } // f是匿名函数 {"hello world"}的别名

    f // "hello world"
    //函数的调用方法
    foo
    foo()
  }
  def foo(): Unit = {
    println("this is foo.......")
    
  }
}