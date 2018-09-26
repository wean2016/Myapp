/**
  * @Description
  * @Author liuyuequn weanyq@gmail.com
  * @Date 2018/9/25 9:44
  * @version V1.0.0
  */
case class Node(value:Number, left:Node, right:Node, high:Int, symbol: Symbol) {
  override def toString: String = Node.printNode(this)

  override def equals(obj: Any): Boolean =
     obj match {
      case that: Node =>
        if (that.symbol == null)  {if (symbol == null ) value.equals(that.value) else false}
        else if (symbol == null) false
        else if (left.equals(that.left) && right.equals(that.right) && symbol==that.symbol) true
        else if ((symbol==Symbol.ADD || symbol==Symbol.MULT) && left.equals(that.right) && right.equals(that.left)) true
        else false
      case _ => false

  }

  override def hashCode(): Int = {
    var result = value.hashCode()
    result = 31 * result + (if(left == null) 0 else left.hashCode())
    result = 31 * result + (if(right == null) 0 else right.hashCode())
    result = 31 * result + (if(symbol == null) 0 else symbol.hashCode())
    result
  }
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