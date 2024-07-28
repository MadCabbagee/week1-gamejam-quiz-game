package codejam.quizgame;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Question implements Serializable {

//    question: String
//    correctAnswer: String
//    falseAnswers: String[]
//
//    getRandomizedAnswers(): String[]
//    get(): String
//    print(): void

    private String content;
    private String correctAnswer;
    private final String[] falseAnswers;

    public Question(String content, String correctAnswer, String[] falseAnswers) {
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.falseAnswers = falseAnswers;
    }

    public String[] getRandomizedAnswers() {
        String[] choices = getAnswers();
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

        if (choices[choice-1].equals(correctAnswer)) {
            System.out.println("Good work! That was correct!");
            return true;
        } else {
            System.out.println("Im sorry, that was not the correct answer.");
            return false;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAnswers() {
        String[] answers = new String[4];
        answers[0] = correctAnswer;
        answers[1] = falseAnswers[0];
        answers[2] = falseAnswers[1];
        answers[3] = falseAnswers[2];
        return answers;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setFalseAnswer(int i, String falseAnswer) {
        falseAnswers[i] = falseAnswer;
    }
}
