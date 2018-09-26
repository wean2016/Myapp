import scala.util.Random

/**
  * @Description
  * @Author liuyuequn weanyq@gmail.com
  * @Date 2018/9/24 20:42
  * @version V1.0.0
  */
case class Number(a:Int, b:Int) {

  override def toString: String = {
    if (b == 1) a.toString else {
      val i = a / b
      if (i != 0) i + "'" + Math.abs(a - i * b) + "/" + b else a + "/" + b
    }
  }
}

object Number {



  def fromString(s:String):Option[Number] = {
    val value = s.trim
    try{
      if (!value.contains("/")){
        // 非分数
        Number.generate(s.toInt, 1)
      }else if (!value.contains("'")){
        // 是普通分数
        val nums = value.split("/")
        Number.generate(nums(0).toInt,nums(1).toInt)
      }else {
        val nums = value.split("'|/")
        Number.generate(nums(0).toInt * nums(2).toInt + nums(1).toInt, nums(2).toInt)
      }
    }catch {
      case e:NumberFormatException => {
        Option.empty
      }
    }

  }

  def randomNumber(bound:Int):Number = if (Random.nextBoolean()){
        Number(Random.nextInt(bound),1)
      }else {
        val b = Random.nextInt(bound - 1) + 1 // 分母
        val a = Random.nextInt(bound * b) // 分子
        Number.generate(a,b).get
      }



  /**
    * 构造一个分数
    * @param in1 输入的分子
    * @param in2 输入的分母
    * @return
    */
  def generate(in1:Int, in2:Int):Option[Number] = {
    // 如果分母小于等于 0 ，返回空 Option
    if (in2 <= 0) return Option.empty
    val g = gcd(in1,in2)
    val a = if (isNeg(in1, in2)) - Math.abs(in1) / g else Math.abs(in1) / g
    val b = Math.abs(in2) / g
    Option.apply(Number(a,b))
  }

  /**
    * 是否是负数
    * @param in1
    * @param in2
    * @return
    */
  def isNeg(in1:Int, in2:Int):Boolean = if ((in1 >> 31 ^ in2 >> 31) == -1) true else false


  def isNeg(number: Number):Boolean = isNeg(number.a,number.b)

  def gcd(in1:Int, in2: Int):Int = {
    val n = in1 % in2
    if (n == 0) in2 else gcd(in2, n)
  }

  def max(number1:Number, number2:Number) = if (!isNeg(Calculate.sub(number1,number2).get)) number1 else number2

  def abs(number: Number) = generate(math.abs(number.a), math.abs(number.b)).get
}
