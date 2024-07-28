package codejam.quizgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final String name;
    private final String[] choices;
    private final Map<Integer, Runnable> selectionToActions;
    private boolean run;

    public Menu(String name, String[] choices, Runnable[] actions) {
        this.name = name;
        this.choices = choices;
        this.selectionToActions = new HashMap<>();
        for (int i = 0; i < actions.length; i++) {
            this.selectionToActions.put(i+1, actions[i]);
        }
    }

    public void handleUserSelection() {
        run = true;
        while (run) {
            int choice = Console.promptWithChoicesSpaced(name + ':', choices);

            Runnable action = selectionToActions.get(choice);
            action.run();
        }

    }

    private void display() {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i+1 + ". " + choices[i]);
        }
    }

    public void stop() {
        run = false;
    }
}
