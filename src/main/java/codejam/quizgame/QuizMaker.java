package codejam.quizgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuizMaker {

    private boolean run;
    private static BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
    private final Menu mainMenu;
    private final Menu playMenu;
    private final Menu quizMenu;

    public QuizMaker() {
        QuizManager qMan = new QuizManager();

        String[] mainChoices = new String[] {"Play", "Manage Quizes", "Exit"};
        Runnable[] mainActions = new Runnable[] {this::showPlayMenu, this::showQuizMenu, () -> run = false};
        mainMenu = new Menu(mainChoices, mainActions);

        String[] playChoices = new String[] {"New", "Continue", "Go Back"};
        Runnable[] playActions = new Runnable[] {() -> {
            // show list of quizes
            qMan.printQuizes();
                // if quizes list empty tell them to add some quizes first
                // ask them to choose quiz
                // play the quiz


        }};
        playMenu = new Menu(playChoices, playActions);

        String[] quizChoices = new String[] {"New", "Edit", "Delete", "Go Back"};
        Runnable[] quizActions = new Runnable[] {};
        quizMenu = new Menu(quizChoices, quizActions);
    }

    private void showPlayMenu() {
        playMenu.handleUserInput();
    }

    private void showQuizMenu() {
        quizMenu.handleUserInput();
    }

    public void run() {
        run = true;
        printConsoleHeader();

        // main loop, show the main menu until they exit
        while (run) {
            mainMenu.handleUserInput();
        }

        System.out.println("Thanks for trying out our game jam project for the week1 game jam from Cup of Java discord server!");
        System.out.println("Cup of Java Discord invite: https://discord.gg/yZppYe9Av9");
    }

    public static void main(String[] args) {
        QuizMaker qm = new QuizMaker();
        qm.run();

    }

    private void printConsoleHeader() {
        String header = "#######################################\n" +
                "### Cabbage and Zohair's quiz maker ###\n" +
                "#######################################\n";
        System.out.println(header);
    }


}
