package codejam.quizgame;

public class QuizMaker {
    private final QuizManager qMan;
    private final Menu mainMenu;
    private final Menu playMenu;
    private final Menu quizMenu;


    public QuizMaker() {
        qMan = new QuizManager();

        String[] mainChoices = new String[] {"Play", "Manage Quizes", "Exit"};
        Runnable[] mainActions = new Runnable[] {this::showPlayMenu, this::showQuizMenu, this::exit};
        mainMenu = new Menu("Main Menu", mainChoices, mainActions);

        String[] playChoices = new String[] {"New", "Continue", "Go Back"};
        Runnable[] playActions = {qMan::playQuiz, qMan::continueActiveQuiz, this::closePlayMenu };
        playMenu = new Menu("Play Menu", playChoices, playActions);

        String[] quizChoices = new String[] {"New", "Edit", "Delete", "Go Back"};
        Runnable[] quizActions = new Runnable[] {qMan::createQuiz, qMan::editQuiz, qMan::deleteQuiz, this::closeQuizMenu};
        quizMenu = new Menu("Quiz Menu", quizChoices, quizActions);
    }

    private void closeQuizMenu() {
        quizMenu.stop();
    }

    private void closePlayMenu() {
        playMenu.stop();
    }

    private void exit() {
        mainMenu.stop();
    }

    private void showPlayMenu() {
        playMenu.handleUserSelection();
    }

    private void showQuizMenu() {
        quizMenu.handleUserSelection();
    }

    public void start() {
        printConsoleHeader();
        mainMenu.handleUserSelection();
        Console.close();

        System.out.println("Thanks for trying out our game jam project for the week1 game jam from Cup of Java discord server!");
        System.out.println("Cup of Java Discord invite: https://discord.gg/yZppYe9Av9");
    }

    public static void main(String[] args) {
        QuizMaker qm = new QuizMaker();
        qm.start();

    }

    private void printConsoleHeader() {
        String header = "#######################################\n" +
                "### Cabbage and Zohair's quiz maker ###\n" +
                "#######################################\n";
        System.out.println(header);
    }


}
