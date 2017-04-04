package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if(c == 0 || c == r) 1
      else pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      //    Good:  "(if (zero? x) max (/ 1 x))"
      //    Bad: :-)
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

    nxt(chars,0)
  }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if(money == 0)
        1
      else if(money > 0 && !coins.isEmpty)
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
      else
        0
    }
  }
