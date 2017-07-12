package scala.learn.lib.io
import scala.io._
import java.io._, java.nio.channels._, java.nio._
import java.net.{URL, URLEncoder} 
import scala.io.Source.fromURL
object IODemo {
  def main(args: Array[String]): Unit = {
    // Source.fromFile(new java.io.File("D:\\testdata\\scala.txt"), "utf8").getLines().foreach(println)
    //writeFileTest()
    //appendFileTest()
    //copyFileTest()
    netIOTest()
  }
  def writeFileTest() {
    val f = new FileOutputStream("D:\\testdata\\scala_output.txt").getChannel

    f write ByteBuffer.wrap("a little bit long ...".getBytes)

    f close
  }
  def appendFileTest(): Unit = {
    var out = new java.io.FileWriter("D:\\testdata\\scala_output.txt", true) // FileWriter("./out.txt", true) 为追加模式

    out.write("hello again\n")
    out.write("this is appended\n")

    out close
  }
  def copyFileTest() {
    val in = new FileInputStream("D:\\testdata\\scala_output.txt").getChannel
    val out = new FileOutputStream("D:\\testdata\\scala_output_copy.txt").getChannel

    in transferTo (0, in.size, out)
  }
  def netIOTest(){
    print(fromURL(new URL("http://www.baidu.com")).mkString)
    //fromURL(new URL("http://qh.appspot.com"))(io.Codec.UTF8).mkString
  }
}