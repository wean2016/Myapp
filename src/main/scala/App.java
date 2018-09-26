import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @version V1.0.0
 * @Description
 * @Author liuyuequn weanyq@gmail.com
 * @Date 2018/9/26 23:11
 */
public class App {
    public static void outputQuestion(int questionSum, int bound){
        ArrayList<Node> nodes = Question.batchNodeGenerator(questionSum, 3, bound);
        try (BufferedWriter exercisesWriter = new BufferedWriter(new FileWriter("Exercises.txt"));
             BufferedWriter answerWriter = new BufferedWriter(new FileWriter("Answers.txt"))){
            for (int i = 1; i <= questionSum; i++) {
                exercisesWriter.write(i + "." + nodes.get(i - 1).toString() + "\n");
                answerWriter.write(i + "." + nodes.get(i - 1).value().toString() + "\n");
            }
            exercisesWriter.flush();
            answerWriter.flush();
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.print("创建题库失败");
        }
    }
}
