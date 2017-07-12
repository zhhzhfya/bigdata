package sca.progrm.book

/**
 * @author Administrator
 */
object HelloWord {
  def main(args: Array[String]): Unit = {
    println("hello world ob)");
    
    val greetStrings=new Array[String](3)
    greetStrings(0)="hello"
    greetStrings(2)=","
    greetStrings(1)="you"
    
    for(i<- 0 to 2)
      println(greetStrings(i))
  }

}