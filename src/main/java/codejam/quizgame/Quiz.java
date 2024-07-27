package codejam.quizgame;

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
