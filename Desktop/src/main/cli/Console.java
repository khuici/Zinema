package main.cli;

import java.io.IOException;
import java.util.Scanner;

import main.models.Day;
import main.models.enums.Color;

public class Console extends Menus {
    public static final Scanner INPUT_SCANNER = new Scanner(System.in);
    public static Day currentDay = null;

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void exit(int statusCode) {
        clear();
        System.exit(statusCode);
    }

    public static void exit(int statusCode, String message) {
        clear();

        if (statusCode == 1) {
            System.out.println(Color.paint(Color.RED, message));
        } else {
            System.out.println(message);
        }

        System.out.println();

        System.exit(statusCode);
    }

    public static void waitForEnter() {
        System.out.println();
        System.out.println(String.format("Sakatu \"%s\" botoia bueltatzeko...", Color.paint(Color.PURPLE, "Enter")));
        System.out.println();

        INPUT_SCANNER.nextLine();
    }

    public void run() {
        MenuState state = MenuState.MAIN_MENU;

        while (state != MenuState.EXIT) {
            clear();

            state = switch(state) {
                case MAIN_MENU -> handleMainMenu();
                case WEEKDAY_SELECTION_MENU -> handleWeekdaySelectionMenu(true);
                case INDIVIDUAL_DAY_OPTIONS_MENU -> handleIndividualDayOptionsMenu(true, currentDay);
                case TICKET_PURCHASE_MENU -> handleTicketPurchaseMenu(currentDay);

                default -> MenuState.EXIT;
            };
        }

        exit(0, Color.paint(Color.CYAN_BOLD, "Agur!"));
    }
}
