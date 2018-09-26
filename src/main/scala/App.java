import scala.Option;

import java.io.*;
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

    public static void outputGrade(){
        try (BufferedReader exReader = new BufferedReader(new FileReader("Exercises.txt"));
             BufferedReader anReader = new BufferedReader(new FileReader("Answers.txt"));
             BufferedWriter gradeWriter = new BufferedWriter(new FileWriter("Grade.txt"))){
            String ex, an;
            int c = 0, w = 0;
            StringBuilder correct = new StringBuilder("Correct: %d (");
            StringBuilder wrong = new StringBuilder("Wrong: %d (");
            while ((ex = exReader.readLine()) != null && (an = anReader.readLine()) != null) {
                int exPoint = ex.indexOf(".");
                int anPoint = an.indexOf(".");
                if (exPoint != -1 && anPoint != -1) {
                    int i = Integer.valueOf(ex.substring(0,exPoint).trim());
                    Node node = Node.fromString(ex.substring(exPoint + 1));
                    Option<Number> numberOption = Number.fromString(an.substring(anPoint + 1));
                    if (numberOption.isEmpty()){
                        System.err.println(String.format("第 %d 道答案格式不对", i));
                        return;
                    }
                    Number answer = numberOption.get();
                    if (node.value().toString().equals(answer.toString())) {
                        c++;
                        correct.append(" ").append(i);
                        if (c % 20 == 0) {
                            correct.append("\n");
                        }
                    } else {
                        w++;
                        wrong.append(" ").append(i);
                        if (w % 20 == 0) {
                            wrong.append("\n");
                        }
                    }
                }
            }
            gradeWriter.write(String.format(correct.append(" )\n").toString(),c));
            gradeWriter.write(String.format(wrong.append(" )\n").toString(),w));
            gradeWriter.flush();
        } catch (FileNotFoundException e) {
            System.err.println("没有找到题库或答案文件");
        } catch (IOException e) {
//            e.printStackTrace();
            System.err.println("输出成绩文件失败");
        }
    }
}
