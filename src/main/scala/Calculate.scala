/**
  * @Description
  * @Author liuyuequn weanyq@gmail.com
  * @Date 2018/9/25 9:32
  * @version V1.0.0
  */
object Calculate {
  def add(number1:Number, number2:Number):Option[Number] = Number.generate(number1.a * number2.b + number2.a * number1.b, number1.b * number2.b)

  def sub(number1:Number, number2:Number):Option[Number] = Number.generate(number1.a * number2.b - number2.a * number1.b, number1.b * number2.b)

  def mult(number1:Number, number2:Number):Option[Number] = Number.generate(number1.a * number2.a, number1.b * number2.b)

  def div(number1:Number, number2:Number):Option[Number] =Number.generate(number1.a * number2.b, number1.b * number2.a)

  def calc(number1:Number, number2:Number, symbol: Symbol): Option[Number] = symbol match {
    case Symbol.ADD => add(number1,number2)
    case Symbol.SUB => sub(number1,number2)
    case Symbol.MULT => mult(number1,number2)
    case Symbol.DIV => div(number1,number2)
  }
}
