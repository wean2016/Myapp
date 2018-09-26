import org.junit.Test;
import scala.collection.immutable.List;
import scala.collection.mutable.HashSet;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2018/9/26 13:54
 */
public class TestQuestion {
    @Test
    public void testBatch(){
        System.out.println(Question.batchNodeGenerator(10000, 3, 10));
    }
}
