object rationals{

  class Rational(x:Int, y:Int){

    require( y > 0, "denom must be positive")

    def this(x: Int) = this(x, 1)

    private def gcd(a: Int, b:Int): Int = if (b == 0) a else gcd(b, a % b)
    private val g = gcd(x, y)

    def numer = x / g
    def denom = y / g


    def + (that: Rational): Rational ={
      new Rational(numer * that.denom + that.numer * denom,
        denom * that.denom)
    }

    def - (that: Rational): Rational = {
      this + -that
    }

    def unary_- : Rational = {
      new Rational( -numer, denom )
    }

    def mul(that: Rational): Rational = {
      new Rational(that.numer * numer, that.denom * denom)
    }


    def < (that: Rational) = numer * that.denom < that.numer * denom

    def max(that: Rational): Rational = if(this < that) that else this

    override def toString: String = numer + "/" + denom

  }

  val x = new Rational(1,3)
  val y = new Rational(5,7)
  val z = new Rational(3,2)

  val one = new Rational(1)

  val two = new Rational(2)

  x + y

  x + y





  x - y - z

  y - y


  x < y

  y < x

  x.max(y)

  y.max(x)

}

