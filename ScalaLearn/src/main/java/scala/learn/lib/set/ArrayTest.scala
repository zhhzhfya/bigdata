package scala.learn.lib.set

import java.util.ArrayList
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer
/**
 * @author Administrator
 */
object ArrayTest extends App {

  //   var arr1 = new Array[Int](3)
  //   arr1(0) = 1
  //   arr1(1) = 2
  //   arr1(2) = 3
  //   for( i <- arr1){
  //     println(i)
  //   }

  //类型不一致
  //  var arr2 = Array(1,2,3,4,"呵呵")
  //  for(i <- 0 until arr2.length){
  //    println(i + "->" + arr2(i))
  //  }

  var arr2 = Array(1, 2, 3, 4)
  println(arr2.mkString(" "))
  //  println(arr2.min)  
  //  println(arr2.max) 
  //  println(arr2.sum) 
  //这几个方法，要求数组里面的类型要一致

  //变长数组

  val b = ArrayBuffer[Int]()

  // 或者 new ArrayBuffer[Int]

  // 一个空的数组缓冲，准备存放整数

  b += 1

  // ArrayBuffer(1)

  // 用+=在尾端添加元素

  b += (1, 2, 3, 5)

  // ArrayBuffer(1,  1, 2, 3, 5)

  // 在尾端添加多个元素，以括号包起来

  b ++= Array(8, 13, 21)

  // ArrayBuffer(1, 1, 2, 3, 5,  8, 13, 21)
  // 你可以用++=操作符追加任何集合
  var aarr = ArrayBuffer(1, 2, 3, 4)
  aarr.--=(Array(1))
  print(aarr)

  b.trimEnd(5)

  // ArrayBuffer(1, 1, 2)
  // 移除最后5个元素

  //有时你需要构建一个Array，但不知道最终需要装多少元素。在这种情况下，先构建一个数组缓冲，然后调用：
  // b.toArray

  //scala与java的交换
  import java.util.ArrayList
  var list = new ArrayList[Int]()
  list.add(1)
  list.add(2)
  list.add(3)
  print(list)
  print(list.get(2))

  //filter map用法
  //  var temp=aarr.filter{_%2==0}.map{_*2} 
  var temp = aarr.filter(_ % 2 == 0).map(_ * 2)

  for (i <- temp) {
    println(i)
  }
}