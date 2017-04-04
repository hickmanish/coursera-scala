import math.abs

object session{
// CURRYING
//  def sum(f: Int => Int, a: Int, b: Int): Int = {
//    def loop(a: Int, acc: Int): Int = {
//      if (a > b) acc
//      else loop(a + 1, acc + f(a))
//    }
//    loop(f(a), 0)
//  }
//
//  sum(x => x, 4, 7)

  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF(a: Int, b: Int): Int =
      if(a > b) 0
      else f(a) + sumF(a+1, b)
    sumF
  }

  sum(x => x*x*x)(1,2)


  def product(f: Int => Int)(a: Int, b: Int): Int =
    if(a > b) 1
    else f(a) * product(f)(a + 1, b)

  product(x => x)(2,4)

  def fact(n: Int) = product(x => x)(1, n)

  fact(3)

  def mapRed(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b:Int): Int =
    if(a > b) zero
    else combine(f(a), mapRed(f, combine, zero)(a+1, b))

  def newProduct(f: Int => Int)(a: Int, b: Int): Int = mapRed(f, (x:Int, y:Int) => x * y, 1)(a, b)

  def newFact(n: Int) = newProduct(x => x)(1, n)

  def newSum(f: Int => Int): (Int, Int) => Int = mapRed(f, (x:Int, y:Int) => x + y, 0)

  def newSumInts(n:Int) = newSum(x => x)(1, n)

  def newSumSquares(a:Int, b:Int) = newSum(x => x*x)(a, b)


  newFact(3)

  newSumInts(3)

  newSumSquares(1,2)
  newSumSquares(1,3)


  // FIND FIXED POINTS
  val tolerance = 0.0001
  def isCloseEnough(x:Double, y:Double) = abs((x-y)/x)/x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      println("guess " + guess + "\n")
      val next = f(guess)
      if(isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }

  fixedPoint(x => 1+x/2)(1)

  def sqrt(x: Double) = fixedPoint(y => ((y+x/y)/2))(1.0)

  sqrt(2)


  // FUNCTIONS AS RETURN VALUES

  def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2

  def newsqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1.0)

  newsqrt(2)

  newsqrt(4)
}