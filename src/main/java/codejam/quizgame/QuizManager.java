package codejam.quizgame;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private final List<Quiz> quizes;

    public QuizManager() {
        quizes = new ArrayList<>();
    }

    public void createQuiz() {
        // ask user stuff for creating quiz then create it and add to list
        String quizName = Console.promptSpaced("Enter new quiz name:");

        int quizQuestionCount;
        do {
            quizQuestionCount = Console.promptForIntSpaced("How many questions will your quiz have?(5-10)");
            if (quizQuestionCount < 5 || quizQuestionCount > 10) {
                System.out.println("You must specify a number between 5 and 10 inclusive.");
                quizQuestionCount = 0;
            }
        }
        while (quizQuestionCount == 0);

        Question[] questions = new Question[quizQuestionCount];
        int createdQuestions = 0;

        while (createdQuestions < quizQuestionCount) {
            System.out.println("Question #" + (createdQuestions + 1));

            String questionContent = Console.promptSpaced("Enter the question:");
            String correctAnswer = Console.promptSpaced("Enter the correct answer:");

            String[] falseAnswers = new String[3];
            falseAnswers[0] = Console.promptSpaced("Enter false choice one:");
            falseAnswers[1] = Console.promptSpaced("Enter false choice two:");
            falseAnswers[2] = Console.promptSpaced("Enter false choice three:");

            questions[createdQuestions++] = new Question(questionContent, correctAnswer, falseAnswers);
        }
        quizes.add(new Quiz(quizName, questions));
    }

    public void editQuiz() {
        int quizChoice = Console.promptWithChoicesSpaced("Choose a quiz to edit:", getQuizNames()) - 1;
        Quiz q = quizes.get(quizChoice);

        String[] editChoices = {"Rename Quiz", "Reword Question", "Reword Answers"};

    }

    public void deleteQuiz() {

    }

    public void playQuiz() {
        if (quizes.isEmpty()) {
            System.out.println("You must create a quiz first.");
            return;
        }
        // show quizes and ask to choose one
        String[] quizNames = getQuizNames();

        // user select quiz
        int choice = -1;
        while (choice == -1) {
            try {
                choice = Console.promptWithChoicesSpaced("Select a quiz to play.", quizNames);
            } catch (Exception e) {
                System.out.println("Please enter a valid selection.");
            }
        }

        quizes.get(choice-1).play();
        // quiz start running
        // show score at end or keep running score (correctQ/totalQ)
    }

    private String[] getQuizNames() {
        String[] quizNames = new String[quizes.size()];

        for (int i = 0; i < quizNames.length; i++) {
            quizNames[i] = quizes.get(i).getName();
        }

        return quizNames;
    }

    public void printQuizes() {
        // print all quizes names from the list
        StringBuilder sb = new StringBuilder();
        sb.append("All Quizes:\n");
        for (int i = 0; i < quizes.size(); i++) {
            Quiz q = quizes.get(i);
            sb.append(String.format("\t%d. %s\n", i+1, q.getName()));
        }
        System.out.println(sb);
    }

    public void loadQuizes() {
        // load quizes from file
    }

    public void saveQuizes() {
        // save created quizes from memory to file
    }
}
