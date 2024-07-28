package codejam.quizgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static final BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

    private Console() {
        // hide implicit public constructor
    }

    public static String prompt(String prompt) {
        System.out.println(prompt);
        String answer;
        try {
            answer = cin.readLine();
        } catch (IOException ignored) {
            answer = "";
        }

        return answer;
    }

    public static String promptSpaced(String prompt) {
        String answer = prompt(prompt);
        System.out.println();
        return answer;
    }

    public static int promptWithChoices(String prompt, String[] choices) {
        int selection = -1;
        while (selection < 1 || selection > choices.length) {
            System.out.println(prompt);
            for (int i = 0; i < choices.length; i++) {
                System.out.println("\t" + (i + 1) + ". " + choices[i]);
            }

            try {
                String input = cin.readLine();
                selection = Integer.parseInt(input);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Invalid selection. Please enter a number corresponding to the menu options.");
            }
        }
        return selection;
    }

    public static int promptWithChoicesSpaced(String prompt, String[] choices) {
        int choice = promptWithChoices(prompt, choices);
        System.out.println();
        return choice;
    }

    public static void close() {
        try {
            cin.close();
        } catch (IOException e) {

        }
    }


    public static int promptForIntSpaced(String prompt) {
        int val = -1;
        while (val == -1) {
            try {
                val = Integer.parseInt(promptSpaced(prompt));

            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
        return val;
    }
}
