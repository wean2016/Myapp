import java.util.NoSuchElementException

import scala.collection.mutable
import scala.util.Random

/**
  * @Description
  * @Author liuyuequn weanyq@gmail.com
  * @Date 2018/9/25 9:42
  * @version V1.0.0
  */
object Question {

  /**
    * 构造表达式
    * @param symbolSum 剩余符号数
    * @param bound 表达式钟的数字不大于 bound
    * @return 生成的节点树的根
    */
  def createNodeTree(symbolSum:Int, bound:Int):Node = {
    if (symbolSum == 0){
      // 中间节点构造完成，生成叶子节点
      Node(Number.randomNumber(bound), null, null, 0, null)
    }else {
      val left = Random.nextInt(symbolSum)
      val right = symbolSum - left - 1
      var leftNode = createNodeTree(left,bound)
      var rightNode = createNodeTree(right,bound)
      // 本节点的符号
      var symbol = Symbol.values()(Random.nextInt(Symbol.values().length))
      // 如果是除号且右节点是 0 ，那么重新生成一个不是除号的符号
      if (symbol == Symbol.DIV && rightNode.value.a == 0){
        while (symbol == Symbol.DIV){
          symbol = Symbol.values()(Random.nextInt(Symbol.values().length))
        }
      }
      // 本节点的结果
      var value = Calculate.calc(leftNode.value,rightNode.value,symbol).get
      if (Number.isNeg(value)) {
        // 如果运算结果是负数，交换左右节点，并把结果取绝对值
        value = Number.abs(value)
        val tempNode = leftNode
        leftNode = rightNode
        rightNode = tempNode
      }
      val high = math.max(leftNode.high, rightNode.high) + 1
      // 生成本节点
      Node(value,leftNode,rightNode,high,symbol)
    }
  }

  def batchNodeGenerator(sum:Int, symbolSum:Int, bound:Int) ={
    val resultSet:mutable.HashSet[Node] = mutable.HashSet()
    while (resultSet.size < sum){
      resultSet.add(createNodeTree(Random.nextInt(symbolSum) + 1,bound))
    }
    resultSet
  }

}
