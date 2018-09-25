/**
  * @Description
  * @Author liuyuequn weanyq@gmail.com
  * @Date 2018/9/25 9:44
  * @version V1.0.0
  */
case class Node(value:Number, left:Node, right:Node, high:Int, symbol: Symbol) {
  override def toString: String = Node.printNode(this)
}

object Node{
  def printNode(node: Node):String = {
    if (node == null) return ""

    val midValue = if (node.symbol == null) node.value.toString else node.symbol.value
    // 左右的原始值
    var leftValue = printNode(node.left)
    var rightValue = printNode(node.right)
    // 判断是否要加括号，如果要，就加上去
    if (node.symbol != null && (node.symbol == Symbol.MULT || node.symbol == Symbol.DIV)){
      val leftSymbol = node.left.symbol
      val rightSymbol = node.right.symbol
      if (leftSymbol != null && (leftSymbol == Symbol.ADD || leftSymbol == Symbol.SUB)) leftValue = Bracket.leftBracket.value + leftValue + Bracket.rightBracket.value
      if (rightSymbol != null && (rightSymbol == Symbol.ADD || rightSymbol == Symbol.SUB)) rightValue = Bracket.leftBracket.value + rightValue + Bracket.rightBracket.value
    }
    leftValue + midValue + rightValue
  }
}