package codejam.quizgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final String[] choices;
    private final Map<Integer, Runnable> selectionToActions;

    public Menu(String[] choices, Runnable[] actions) {
        this.choices = choices;
        this.selectionToActions = new HashMap<>();
        for (int i = 0; i < actions.length; i++) {
            this.selectionToActions.put(i+1, actions[i]);
        }
    }

    public void handleUserInput() {
        Scanner consoleIn = new Scanner(System.in);

        display();
        System.out.print("Enter your choice: ");
        while (!consoleIn.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            if (consoleIn.hasNext()) {
                consoleIn.next(); // i think this is here to flush extra stuff in the scanner, donno will see if its even needed
            }
        }
        int choice = consoleIn.nextInt();
        if (choice > 0 && choice < choices.length) {
            Runnable action = selectionToActions.get(choice);
            action.run();
        } else {
            System.out.println("Invalid selection. Please enter one of the numbers from the menu.");
        }
        consoleIn.close();
    }

    private void display() {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i+1 + ". " + choices[i]);
        }
    }
}
