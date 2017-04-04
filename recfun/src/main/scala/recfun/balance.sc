def balance(chars: List[Char]): Boolean = {
  def nxt(rst: List[Char], opn: Int):Boolean = {
    if(opn < 0 || rst.isEmpty) opn == 0
    else {
      val mod: Int =
        if (rst.head == ')') -1
        else if (rst.head == '(') 1
        else 0
      nxt(rst.tail, opn+mod)
    }
  }

  nxt(chars, 0)
}

balance(")".toList) //false

balance(")(".toList) //false

balance("()(".toList) //false

balance("())()".toList) //false

balance(")sdfd()()()()".toList) //false

balance("()()()()".toList) //true

balance("asdfsdf (sdf) asdfd(sdf(dfd))".toList) //true

balance("".toList) //true

def square(x: Double) = Math.pow(x, x)
square(3.0)

def or(x:Boolean, y: =>Boolean) = if(x) true else y

def isGoodEnough(guess: Double, x: Double): Boolean =
  x * x - guess * guess < .001

def improve(guess: Double, x: Double): Double = (x/guess + guess)/2

def sqrtIter(guess: Double, x: Double): Double =
  if(isGoodEnough(guess, x)) guess
  else sqrtIter(improve(guess, x), x)


