package scala.learn.lib.set

object MapDemo {
  def main(args: Array[String]): Unit = {
    readMap()
  }
  def readMap() {
    //var m3=Map[Int,Int]()
    val m3 = Map(1 -> 100, 2 -> 200) ++ Map(3 -> 300)
    //m3(1)=100; m3(2)=200; m3(3)=300

    // m3.get(1)=Some(100), m3.get(3)=Some(300), m3.get(4)=None

    val v = m3.get(4).getOrElse(-1) // -1

    //或者简化成：
    m3.getOrElse(4, -1) // -1
    println(m3.get(2))
    println(m3.getOrElse(4, -1))
  }
  def getMap(){
    var m = Map[Int, Int]()
    var m1 = Map(1 -> 100, 2 -> 200)
    var m2 = Map((1, 100), (2, 200))

    //相加：
    val m3 = Map(1 -> 100, 2 -> 200) ++ Map(3 -> 300) // Map((1,100), (2,200), (3,300))
    
    //可以用zip()生成Map：
    List(1, 2, 3).zip(List(100, 200, 300)).toMap // Map((1,100), (2,200), (3,300))
  }
  
}