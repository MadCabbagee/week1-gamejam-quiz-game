package codejam.quizgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

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
    String[] falseAnswers;

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getFalseAnswers() {
        return falseAnswers;
    }

    public String[] getRandomizedAnswers(String[] falseAnswers) {
        String[] shuffledAnswers = new String[falseAnswers.length];
        boolean[] falseAnswersB = new boolean[falseAnswers.length];

        Random rng = new Random(UUID.randomUUID().hashCode());
        for (int i = 0; i < falseAnswers.length; i++) {
            int j = rng.nextInt(shuffledAnswers.length);
            if (!falseAnswersB[i] && shuffledAnswers[j] == null) {
                shuffledAnswers[j] = falseAnswers[i];
                falseAnswersB[i] = true;
            }
            else {
                i--;
            }
        }
        return shuffledAnswers;
    }
}
