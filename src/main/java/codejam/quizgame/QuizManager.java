package codejam.quizgame;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        saveQuizes();
    }

    public void editQuiz() {
        int quizChoice = Console.promptWithChoicesSpaced("Choose a quiz to edit:", getQuizNames()) - 1;
        Quiz quiz = quizes.get(quizChoice);

        boolean keepEditing = true;
        while (keepEditing) {
            String[] editChoices = {"Rename Quiz", "Reword Question", "Reword Answers", "Cancel"};
            int editChoice = Console.promptWithChoicesSpaced("What do you want to edit about the quiz:", editChoices);

            switch (editChoice) {
                case 1:
                    String newName = Console.promptSpaced("Enter new quiz name:");
                    quiz.setName(newName);
                    saveQuizes();
                    break;
                case 2:
                    int questionChoice = Console.promptWithChoicesSpaced("Which question do you want to reword:", quiz.getRawQuestions()) - 1;
                    Question question = quiz.getQuestion(questionChoice);
                    question.setContent(Console.promptSpaced("Enter new question:"));
                    saveQuizes();
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
                    saveQuizes();
                    break;
                case 4:
                    keepEditing = false;
                    break;
            }
        }
    }

    public void deleteQuiz() {

        saveQuizes();
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

        quizes.get(choice - 1).play();
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
            sb.append(String.format("\t%d. %s\n", i + 1, q.getName()));
        }
        System.out.println(sb);
    }

    public void loadQuizes() {
        // load quizes from file
    }

    public void saveQuizes() {
        System.out.println(Path.of("./"));
        // save created quizes from memory to file
        // create directory for quizzes if it doesn't exist
        // loop through quizzes and save them to their own files in the directory

        //
        try {
            File quizDir = new File("./quizzes/");
            if (quizDir.exists()) {
                File[] f = quizDir.listFiles();
                for (File file : f) {
                    file.delete();
                }
            } else {
                quizDir.mkdirs();
            }
            for (int i = 0; i < quizes.size(); i++) {
                FileOutputStream fout = new FileOutputStream(quizDir.toPath().resolve(UUID.randomUUID() + ".quiz").toString());
                ObjectOutputStream out = new ObjectOutputStream(fout);
                out.writeObject(quizes.get(i));
            }

        } catch (FileNotFoundException | SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
