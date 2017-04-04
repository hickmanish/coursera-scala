val i = 0
def countChange(money: Int, coins: List[Int]): Int = {
  //println(money + " " + coins + " " )
  if(money == 0) {
    //println("found")
    1
  }
  else if(money > 0 && !coins.isEmpty)
    countChange(money - coins.head, coins) + countChange(money, coins.tail)
  else{
    //println("dead end")
    0
  }
}
countChange(10, List(5,1))


