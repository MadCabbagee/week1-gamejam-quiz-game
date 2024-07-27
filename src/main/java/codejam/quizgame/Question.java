package codejam.quizgame;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {

//    question: String
//    correctAnswer: String
//    falseAnswers: String[]
//
//    getRandomizedAnswers(): String[]
//    get(): String
//    print(): void

    String question;
    String correctAnswer;
    String[] falseAnswer;

    // Use arrays and one of them should be boolean for 1-to-1 mapping for which ones have already been used
    public void getRandomizedAnswers() {
        String[] falseAnswer = {"one", "two", "three", "four"};
        ArrayList<String> al = new ArrayList<>(Arrays.asList(falseAnswer));

        for (int i = 0; i < falseAnswer.length; i++) {
            int randomIndex = (int) (Math.random() * al.size());
            falseAnswer[i] = al.get(randomIndex);
            al.remove(randomIndex);
        }

        for (String word : falseAnswer) {
            System.out.print(word + " ");
        }
    }

    public static void main(String[] args) {

         new Question().getRandomizedAnswers();
    }
}
