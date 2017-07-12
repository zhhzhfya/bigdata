package learn.syntax.mid.fp

/**
 * @author Administrator
 */
object CrxyFp extends App{
  
  /*无返回值的函数*/
  //  def add(a:Int , b:Int){
  //    println(a + b)
  //  }
  //  add(1 , 2)

  
  /*有返回值的函数*/
  //scal中Unit类型 （）  
  //函数如果强制指定为Unit类型  返回值一定是Unit类型
//    def add(a: Int, b: Int):Unit= {
//  //    println(a + b)
//      a + b
//    }
//    var c = add(1, 2)
//    println(c)

  
  /*小知识点*/
  def add(a: Int, b: Int):Int= {
    return a + b   //scala中return就相当于函数版的break
  }
  var c = add(1, 2)
  var d = "adsafasdfa" +
           "sdfasdf"
  println(d)
}