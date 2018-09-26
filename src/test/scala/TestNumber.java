import org.junit.Test;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2018/9/24 20:58
 */
public class TestNumber {

    @Test
    public void  isNeg() {
        assert !(Number.isNeg(1, 1));
        assert (Number.isNeg(-1, 1));
        assert !(Number.isNeg(0, 1));
    }

    @Test
    public void gcd(){
        assert (Number.gcd(4,2) == 2);
        assert (Number.gcd(-1,1) == 1);
        assert (Number.gcd(0,1) == 1);
    }

    @Test
    public void testGenerateAndToString(){
        System.out.println(Number.generate(1,0));
        System.out.println(Number.generate(1,1).get().toString());
        System.out.println(Number.generate(5,2).get().toString());
    }

    @Test
    public void testRandomNumber(){
        for (int i = 0; i < 10; i++) {
            System.out.println(Number.randomNumber(10));
        }
    }

    @Test
    public void testFromString(){
        System.out.println(Number.fromString("1'2/3"));
        System.out.println(Number.fromString("2/3"));
        System.out.println(Number.fromString("3"));
        System.out.println(Number.fromString(""));
    }


}
