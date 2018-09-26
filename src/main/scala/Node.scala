import java.util

import scala.util.control._
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

  def fromString(s:String): Node = {
    // 克隆输入的字符串，实现函数式编程
    var value = new String(s.toCharArray)
    // 给符号都加上空格
    Symbol.values().foreach(symbol => {
      if (symbol.value.equals("+")){
        value = value.replaceAll("\\+", " %s ".format(symbol.value))
      }else{
        value = value.replaceAll(symbol.value, " %s ".format(symbol.value))
      }
    })
    // 给括号都加上空格
    Bracket.values().foreach(bracket => value = value.replaceAll("\\" + bracket.value, " %s ".format(bracket.value)))
    //去空格生成元素数组
    val factor = value.split(" ").toStream.map(s => s.trim).filter(s => !s.isEmpty).toList
    val nodeStack: util.Stack[Node] = new util.Stack[Node]
    val symbolStack: util.Stack[String] = new util.Stack[String]
    for (f <- factor) {
      // 尝试转换成数字
      val value = Number.fromString(f)
      if (value.isDefined) {
        // 是数字
        nodeStack.push(Node(value.get, null, null, 0, null))
      } else {
        // 是符号
        var signal = true
        while (!symbolStack.empty() && !f.equals(Bracket.leftBracket.value) && !((f.equals(Symbol.MULT.value) || f.equals(Symbol.DIV.value)) && (symbolStack.peek().equals(Symbol.ADD.value) || symbolStack.peek().equals(Symbol.SUB.value))) && !(!f.equals(Bracket.rightBracket.value) && symbolStack.peek().equals(Bracket.leftBracket.value)) && signal) {
          val symbol = symbolStack.pop()
          if (symbol.equals(Bracket.leftBracket.value) && f.equals(Bracket.rightBracket.value)) {
            signal = false
          } else {

            val rightNode = nodeStack.pop()
            val leftNode = nodeStack.pop()
            val sym = symbol match {
              case "+" => Symbol.ADD
              case "-" => Symbol.SUB
              case "×" => Symbol.MULT
              case "÷" => Symbol.DIV
              case _ => sys.error("非法运算符！")
            }
            val result = Calculate.calc(leftNode.value, rightNode.value, sym)
            if (result.isEmpty) {
              sys.error("存在不符合规范的算术表达式！")
            }
            val high = math.max(leftNode.high, rightNode.high) + 1
            nodeStack.push(Node(result.get, leftNode, rightNode, high, sym))
          }
        }
        if (!f.equals(Bracket.rightBracket.value)) {
          symbolStack.push(f)
        }
      }
    }
    while(!symbolStack.isEmpty){
      val symbol = symbolStack.pop()
      if (!symbol.equals(Bracket.leftBracket.value)){

        val rightNode = nodeStack.pop()
        val leftNode = nodeStack.pop()
        val sym = symbol match {
          case "+" => Symbol.ADD
          case "-" => Symbol.SUB
          case "×" => Symbol.MULT
          case "÷" => Symbol.DIV
          case _ => sys.error("非法运算符！")
        }
        val result = Calculate.calc(leftNode.value, rightNode.value, sym)
        if (result.isEmpty) {
          sys.error("存在不符合规范的算术表达式！")
        }
        val high = math.max(leftNode.high, rightNode.high) + 1
        nodeStack.push(Node(result.get, leftNode, rightNode, high, sym))
      }
    }
    nodeStack.pop()
  }



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