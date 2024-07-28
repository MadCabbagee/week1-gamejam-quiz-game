package codejam.quizgame;

import java.util.Random;
import java.util.UUID;

public class Quiz {

//    Quiz:
//    name: String
//    questions: codejam.quizgame.Question[]
//
//    play(): void - randomize a local copy of the questions
//    print(): void

    private String name;
    private Question[] questions;

    public Quiz(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;
    }

    void play() {
        // todo loop through questions and ask them; track score
        int correct = 0;
        Question[] shuffled = getQuestionsShuffled();
        for (Question q : questions) {
            if (q.ask()) {
                correct++;
            }
        }

        String scoreString = String.format("Quiz finished! You got %d/%d", correct, shuffled.length);
        System.out.println(scoreString);
    }

    private Question[] getQuestionsShuffled() {
        Random rng = new Random(UUID.randomUUID().hashCode());

        Question[] shuffled = questions.clone();
        for (int i = 0; i < shuffled.length; i++) {
            int j = rng.nextInt(shuffled.length);
            Question temp = shuffled[j];
            shuffled[j] = shuffled[i];
            shuffled[i] = temp;
        }
        return shuffled;
    }

    void print() {
        // Im not sure how to link the quiz with it's appropriate questions/answers
    }

    public String getName() {
        return name;
    }

    public Question[] getQuestions() {
        return questions;
    }
}
