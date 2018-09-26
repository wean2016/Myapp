/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2018/9/25 11:47
 */
public enum Symbol {

    ADD("+"),
    SUB("-"),
    MULT("×"),
    DIV("÷");

    String value;

    Symbol(String value) {
        this.value = value;
    }

}
