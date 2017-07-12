package spark.crxy.cdh.client

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
/**
 * Created by goosoog on 15/6/8.
 */

object Client {
  def main(args: Array[String]) {
    val sc = new SparkContext
    val wc = sc.textFile(args(0))
    wc.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).sortByKey(false).saveAsTextFile(args(1))

  }
  
}

