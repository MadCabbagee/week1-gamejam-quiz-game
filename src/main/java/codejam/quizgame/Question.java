package codejam.quizgame;

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

    private final String content;
    private final String correctAnswer;
    private final String[] falseAnswers;

    public Question(String content, String correctAnswer, String[] falseAnswers) {
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.falseAnswers = falseAnswers;
    }

    public String[] getRandomizedAnswers() {
        String[] choices = new String[4];
        choices[0] = correctAnswer;
        choices[1] = falseAnswers[0];
        choices[2] = falseAnswers[1];
        choices[3] = falseAnswers[2];
        String[] shuffledAnswers = new String[4];
        boolean[] falseAnswersB = new boolean[4];

        Random rng = new Random(UUID.randomUUID().hashCode());
        for (int i = 0; i < choices.length; i++) {
            int j = rng.nextInt(shuffledAnswers.length);
            if (!falseAnswersB[i] && shuffledAnswers[j] == null) {
                shuffledAnswers[j] = choices[i];
                falseAnswersB[i] = true;
            }
            else {
                i--;
            }
        }
        return shuffledAnswers;
    }

    void print() {
        System.out.println(this.getContent());
        System.out.println(this.getCorrectAnswer());

        for (String falseAnswer : falseAnswers) {
            System.out.println(falseAnswer);
        }
    }

    public String getContent() {
        return content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getFalseAnswers() {
        return falseAnswers;
    }

    public boolean ask() {
        String[] choices = getRandomizedAnswers();

        int choice = Console.promptWithChoicesSpaced(content, choices);

        if (choices[choice].equals(correctAnswer)) {
            System.out.println("Good work! That was correct!");
            return true;
        } else {
            System.out.println("Im sorry, that was not the correct answer.");
            return false;
        }
    }
}
