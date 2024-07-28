package codejam.quizgame;

public class QuizManager {
    private Quiz[] quizes;

    public QuizManager() {
        /*this.quizes = new Quiz[2];
        quizes[0] = new Quiz("test1", new Question[5]);
        quizes[1] = new Quiz("test2", new Question[5]);*/
    }

    public void createQuiz() {
        // ask user stuff for creating quiz then create it and add to list
        String quizName = Console.promptSpaced("Enter new quiz name:");
        int quizQuestionCount = Integer.parseInt(Console.promptSpaced("How many questions will your quiz have?"));
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
    }

    private boolean isValid(char answer) {
        return answer == 'y' || answer == 'n';
    }

    public void editQuiz() {
        // ask suer stuff for editing quiz then edit it
    }

    public void deleteQuiz() {

    }

    public void playQuiz() {
        if (quizes == null) {
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

        quizes[choice-1].play();
        // quiz start running
        // show score at end or keep running score (correctQ/totalQ)
    }

    private String[] getQuizNames() {
        String[] quizNames = new String[quizes.length];

        for (int i = 0; i < quizNames.length; i++) {
            quizNames[i] = quizes[i].getName();
        }

        return quizNames;
    }

    public void continueActiveQuiz() {
        // check active exists. if not tell them
        // else continue active quiz loaded from file (serialized java object)
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
