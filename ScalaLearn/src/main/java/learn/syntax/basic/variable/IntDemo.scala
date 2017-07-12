package learn.syntax.basic.variable

object IntDemo extends App{

   // 2.3.1.  Int
-3 abs // 3

-3 max -2 // -2

-3 min -2 // -3

1.4 round // 1 四舍五入

1.6 round // 2 四舍五入

1.1 ceil // 2.0 天花板

1.1 floor // 1.0 地板

def even(n:Int) = 0==(n & 1)

def odd(n:Int) = !even(n)
}