package scala.learn.syntax.oop

class Person(ln: String, fn: String, s: Person = null) {

  def lastName = ln; // 用def定义后才是属性，ln，fn，s不可见
  def firstName = fn;
  def spouse = s;
  def introduction(): String =
    return (

      ("Hi, " + firstName + " " + lastName) +
      (if (spouse != null) " and spouse, " + spouse.firstName + " " + spouse.lastName + "."
      else "."));

}
object Person{
  def main(args: Array[String]): Unit = {
    print(new Person("aa","bb", new Person("cc","dd")).introduction());
  }
  
}