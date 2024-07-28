package codejam.quizgame;

public class QuizMaker {
    private final QuizManager qMan;
    private final Menu mainMenu;
    private final Menu quizMenu;

    public static void main(String[] args) {
        QuizMaker qm = new QuizMaker();
        qm.start();
    }

    public QuizMaker() {
        qMan = new QuizManager();

        String[] quizChoices = new String[] {"New", "Edit", "Delete", "Go Back"};
        Runnable[] quizActions = new Runnable[] {qMan::createQuiz, qMan::editQuiz, qMan::deleteQuiz, this::closeQuizMenu};
        quizMenu = new Menu("Manage Quizes", quizChoices, quizActions);

        String[] mainChoices = new String[] {"Play", "Manage Quizes", "Exit"};
        Runnable[] mainActions = new Runnable[] {qMan::playQuiz, quizMenu::handleUserSelection, this::exit};
        mainMenu = new Menu("Main Menu", mainChoices, mainActions);
    }

    private void closeQuizMenu() {
        quizMenu.stop();
    }

    private void exit() {
        mainMenu.stop();
    }

    public void start() {
        printConsoleHeader();
        mainMenu.handleUserSelection();
        Console.close();

        System.out.println("Thanks for trying out our game jam project for the week1 game jam from Cup of Java discord server!");
        System.out.println("Cup of Java Discord invite: https://discord.gg/yZppYe9Av9");
    }

    private void printConsoleHeader() {
        String header = "#######################################\n" +
                "### Cabbage and Zohair's quiz maker ###\n" +
                "#######################################\n";
        System.out.println(header);
    }


}
