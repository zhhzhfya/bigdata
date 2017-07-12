package scala.learn.syntax.oop

class Test {

}
object MyTest extends App{
//  def f(i:(Int)=>Int)= i(3)
//  var v = f _
//  
//  
//  var p = (i:Int)=> i+3
  
  
//  def f1(f : Int) = (x:Int)=>(x+3) * f
//  def f2(f : Int)(x:Int) = (x+3) * f
//  
//  var ff = f1(2)
  
  println((1 to 5).reduce(_+_))
  val (x,y) = (10, "hello") 
  println(x+y)
  
}



/**演示对象
  object ID{
    private var id = 0
    def next = {id+=1; id}
  }
  
  println(ID.next)
  println(ID.next)
**/

/**演示伴生类和伴生对象
  object Person{
    private var id = 0
    def next = {id+=1; id}
  }
  class Person{
    private var id = 0
    def getID = Person.next
  }

  println(Person.next)
  var p1 = new Person()
  p1.getID
  var p2 = new Person()
  p2.getID
  var p3 = new Person()
  println(p3.getID)

 **/


/** 演示伴生对象的apply方法
  object Person{
    private var id = 0
    def next = {
      id+=1; id
    }
    
    def apply()={
      new Person
    }
  }
  
  class Person{
    private var age = 0
    def getID = Person.next
    
    override def toString() = "i am Person instance"
  }

  
  
  //var p1 = new Person() //最普通的创建类的实例化
  //var p1 = new Person //实例化时，如果没有参数，那么小括号可以省略
  var p1 = Person() //调用伴生对象的apply方法
  //var p1 = Person //把对象Person赋值给p1
  println(p1.toString)
 * */
 

/**提取器

  object Fac{
    def unapply(o:Fac) = Some(o.a, o.b)
  }
  
  class Fac(var a:Int, var b:Int){
    def *(o:Fac)= new Fac(a*o.a, b*o.b)
    
    def show = a+","+b
  }
  
  var Fac(a,b) = new Fac(1,2) * new Fac(2,3)
  println(a+","+b)
 * */


/***文件操作
var source=Source.fromFile("C://a.txt","UTF-8")
for(i <- source.getLines.toArray)println(i)
source.close 
 * **/



/**trait
   trait Logger{
    def log(words:String){}
  }
  
  trait ConsoleLogger extends Logger{
    override def log(words:String){println("writing in console:"+words)}
  }

  
  trait FileLogger extends Logger{
    override def log(words:String){println("writing to file, contents :"+words)}
  }
  
  class Homework extends Logger{
    def dohome(sth:String){log(sth)}
  }

  (new Homework with ConsoleLogger).dohome("homework")
  (new Homework with FileLogger).dohome("homework")
  
 
**/