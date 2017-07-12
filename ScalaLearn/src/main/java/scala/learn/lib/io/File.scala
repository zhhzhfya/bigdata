package scala.learn.lib.io

import scala.io.Source

/**
 * @author Administrator
 */
object File {
  def main(args: Array[String]): Unit = {
    for (line <- Source.fromFile("d:\\hadoop-common\\testdata.txt").getLines()) {
      //    	for(line <- Source.fromFile("d:\\hadoop-common\\testdata.txt")){
      println(line)
    }
    //Source.fromFile("d:\\hadoop-common\\testdata.txt").getLines()

  }
}