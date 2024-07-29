package codejam.quizgame;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class QuizManager {
    private final List<Quiz> quizzes;

    public QuizManager() {
        quizzes = new ArrayList<>();
        loadQuizes();
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
        quizzes.add(new Quiz(quizName, questions));
        saveQuizes();
    }

    public void editQuiz() {
        String[] options = addOption(getQuizNames(), "Go Back");
        int quizChoice = Console.promptWithChoicesSpaced("Choose a quiz to edit:", options) - 1;
        if (quizChoice == options.length-1) return;
        Quiz quiz = quizzes.get(quizChoice);

        boolean keepEditing = true;
        while (keepEditing) {
            String[] editChoices = {"Rename Quiz", "Reword Question", "Reword Answers", "Cancel"};
            int editChoice = Console.promptWithChoicesSpaced("What do you want to edit about the quiz:", editChoices);

            switch (editChoice) {
                case 1:
                    String newName = Console.promptSpaced("Enter new quiz name:");
                    quiz.setName(newName);
                    break;
                case 2:
                    int questionChoice = Console.promptWithChoicesSpaced("Which question do you want to reword:", quiz.getRawQuestions()) - 1;
                    Question question = quiz.getQuestion(questionChoice);
                    question.setContent(Console.promptSpaced("Enter new question:"));
                    break;
                case 3:
                    questionChoice = Console.promptWithChoicesSpaced("Which question is the answer you want to change in:", quiz.getRawQuestions()) - 1;
                    Question chosenQuestion = quiz.getQuestions()[questionChoice];
                    int answerChoice = Console.promptWithChoicesSpaced("Which answer do you want to reword:", chosenQuestion.getAnswers());
                    switch (answerChoice) {
                        case 1:
                            String correctAnswer = Console.promptSpaced("Enter reworded correct answer: ");
                            chosenQuestion.setCorrectAnswer(correctAnswer);
                            break;
                        case 2:
                            String falseOne = Console.promptSpaced("Enter reworded false answer #1:");
                            chosenQuestion.setFalseAnswer(0, falseOne);
                            break;
                        case 3:
                            String falseTwo = Console.promptSpaced("Enter reworded false answer #2:");
                            chosenQuestion.setFalseAnswer(1, falseTwo);
                            break;
                        case 4:
                            String falseThree = Console.promptSpaced("Enter reworded false answer #3:");
                            chosenQuestion.setFalseAnswer(2, falseThree);
                            break;
                    }

                    break;
                case 4:
                    keepEditing = false;
                    break;
            }
            saveQuizes();
        }
    }

    public void deleteQuiz() {
        String[] qNames = getQuizNames();
        String[] options = addOption(qNames, "Go Back");
        int choice = Console.promptWithChoicesSpaced("Choose a quiz to delete:", options);
        if (choice == options.length) return;
        quizzes.remove(choice-1);
        System.out.println("The quiz was deleted.\n");
        saveQuizes();
    }

    public void playQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("You must create a quiz first.");
            return;
        }
        // show quizes and ask to choose one
        String[] quizNames = getQuizNames();

        // user select quiz
        int choice = -1;
        while (choice == -1) {
            String[] options = addOption(quizNames, "Go Back");

            try {
                choice = Console.promptWithChoicesSpaced("Select a quiz to play.", options);
                if (choice == options.length) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid selection.");
            }
        }

        quizzes.get(choice - 1).play();
    }

    private String[] addOption(String[] quizNames, String extraOption) {
        var a = Arrays.copyOf(quizNames, quizNames.length + 1);
        a[a.length -1] = "Go Back";
        return a;
    }

    private String[] getQuizNames() {
        String[] quizNames = new String[quizzes.size()];

        for (int i = 0; i < quizNames.length; i++) {
            quizNames[i] = quizzes.get(i).getName();
        }

        return quizNames;
    }

    public void printQuizes() {
        // print all quizes names from the list
        StringBuilder sb = new StringBuilder();
        sb.append("All Quizes:\n");
        for (int i = 0; i < quizzes.size(); i++) {
            Quiz q = quizzes.get(i);
            sb.append(String.format("\t%d. %s\n", i + 1, q.getName()));
        }
        System.out.println(sb);
    }

    public void loadQuizes() {
        // load quizes from file
        Path quizzesDirPath = Path.of(System.getProperty("user.dir")).resolve("quizzes");
        try {
            File quizDirFile = quizzesDirPath.toFile();

            if (quizDirFile.exists()) {
                File[] inputFiles = quizDirFile.listFiles();
                if (inputFiles == null || inputFiles.length == 0) return;

                for (File in : inputFiles) {
                    FileInputStream fin = new FileInputStream(in);
                    ObjectInputStream oin = new ObjectInputStream(fin);
                    quizzes.add((Quiz) oin.readObject());
                    oin.close();
                    fin.close();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveQuizes() {
        Path quizzesDirPath = Path.of(System.getProperty("user.dir")).resolve("quizzes");
        try {
            File quizDirFile = quizzesDirPath.toFile();
            if (quizDirFile.exists()) {
                // delete old save files
                File[] f = quizDirFile.listFiles();
                for (File file : f) {
                    System.out.println("delete");
                    file.delete();
                }
            } else {
                quizDirFile.mkdirs();
            }
            for (Quiz quiz : quizzes) {
                FileOutputStream fout = new FileOutputStream(quizzesDirPath.resolve(UUID.randomUUID() + ".quiz").toString());
                ObjectOutputStream out = new ObjectOutputStream(fout);
                out.writeObject(quiz);
                out.close();
                fout.close();
            }

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
