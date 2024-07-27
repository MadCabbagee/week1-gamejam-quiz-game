package codejam.quizgame;

import java.util.Scanner;

public class QuizManager {
    private Quiz[] quizes;

    public QuizManager() {
        /*this.quizes = new Quiz[2];
        quizes[0] = new Quiz("test1", new Question[5]);
        quizes[1] = new Quiz("test2", new Question[5]);*/
    }

    public void createQuiz() {
        Scanner cin = new Scanner(System.in);
        // ask user stuff for creating quiz then create it and add to list
    }

    public void editQuiz() {
        // ask suer stuff for editing quiz then edit it
    }

    public void deleteQuiz() {

    }

    public void playQuiz() {
        // show quizes and ask to choose one
        printQuizes();

        // user select quiz
        System.out.println("Select a quiz to play.");


        // quiz start running
        // show score at end or keep running score (correctQ/totalQ)
    }

    public void printQuizes() {
        // print all quizes names from the list
        StringBuilder sb = new StringBuilder();
        sb.append("All Quizes:\n");
        for (int i = 0; i < quizes.length; i++) {
            Quiz q = quizes[i];
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
