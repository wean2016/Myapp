import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2018/9/25 11:31
 */
public class TestNode {
    @Test
    public void testEqual(){
        Node node1 = new Node(new Number(1, 1), new Node(null,null,null,0, null), null, 1, null);
        Node node2 = new Node(new Number(1, 1), new Node(null,null,null,0, null), null, 1, null);
        assert node1.equals(node2);
    }

    @Test
    public void testToString(){
        IntStream.range(1,100).mapToObj(operand -> Question.createNodeTree(3,10).toString()).forEach(System.out::println);
    }

    @Test
    public void testFromString(){
        System.out.println(Node.fromString("(0 + 1)×2÷3").value().toString());
    }
}
