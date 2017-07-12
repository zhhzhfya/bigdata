package learn.syntax.basic.ctrl

object IfDemo {
  def main(args: Array[String]){
    // <- 这个符号是一个整体
    //for(i<-1 to 10; j=i*i) println(j)
    for(i<- 1 to 10 reverse) print(i+" ")
    for(i<- 1 to 10 ;if i%2==1) print(i+" ")
    println()
    //3是步长
    for(i <- 1 to (10 ,3)) print(i+" ")
    println()
    //双重循环
    for(i<- 1 to 4; j <- 1 to 4){
      println(i+" "+j)
    }
    
    var a= for(i <- 1 to 13 ; if i%3==0) yield(i*2-19)
    println(a)
    
  }
 
}