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
    private final QuizManager qMan;

    public QuizMaker() {
        qMan = new QuizManager();
        final Runnable returnToMainMenu = () -> {/* will return to main loop which will prompt with main menu again */};

        String[] mainChoices = new String[] {"Play", "Manage Quizes", "Exit"};
        Runnable[] mainActions = new Runnable[] {this::showPlayMenu, this::showQuizMenu, () -> run = false};
        mainMenu = new Menu(mainChoices, mainActions);

        String[] playChoices = new String[] {"New", "Continue", "Go Back"};
        Runnable[] playActions = {this::playNewQuiz, this::continueActiveQuiz, returnToMainMenu };
        playMenu = new Menu(playChoices, playActions);

        String[] quizChoices = new String[] {"New", "Edit", "Delete", "Go Back"};
        Runnable[] quizActions = new Runnable[] {qMan::createQuiz, qMan::editQuiz, qMan::deleteQuiz, returnToMainMenu};
        quizMenu = new Menu(quizChoices, quizActions);
    }

    private void continueActiveQuiz() {
        // check active exists. if not tell them
        // else continue active quiz loaded from file (serialized java object)
    }

    private void playNewQuiz() {

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
