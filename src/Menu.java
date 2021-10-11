import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    File hangMan2021 = new File("hangman2021.txt");
    Player currentPlayer = null;
    String[] currentMenu = null;
    String[] startMenu = {"Welcome! Please choose menu option: ", "1: Play", "2: Load Player", "3: Save Player", "4: Quit"};
    String[] endOfGameMenu = {"1: Play again", "5: Return to Main Menu"};

    public Menu() {
        currentMenu = startMenu;
        show(startMenu);
    }

    public Menu(Player player) {
        currentPlayer = player;
        currentMenu = endOfGameMenu;
        show(endOfGameMenu);
    }

    public void show(String[] argument) {
        String[] meny = argument;
        for (int i = 0; i < argument.length; i++) {
            System.out.println(meny[i]);
        }
        //Behöver hantera större intar än 4
        int menuChoice = getInt();
        switch (menuChoice) {
            case 1: { //new Game();
                if (currentPlayer == null) {
                    System.out.println("Please give your player a name: ");
                    String playerName = getString();
                    Player player = new Player(playerName);
                    currentPlayer = player;
                    System.out.println(currentPlayer.getName());
                    new Game(currentPlayer, this);
                } else {
                    new Game(currentPlayer, this);
                }

                break;
            }
            case 2: { //loadPlayer();
                try {
                    System.out.println("Input name of player: ");
                    String name = getString();
                    File file = new File("hangman" + name + ".txt");
                    Scanner scannerFile = new Scanner(file);
                    //Scanner scannerFile = new Scanner(hangMan2021);
                    while (scannerFile.hasNext()) {
                        if (name.equals(scannerFile.next())) {
                            currentPlayer = new Player(name, scannerFile.nextInt(), scannerFile.nextInt());
                            System.out.println();
                            System.out.println("Name of player: " + currentPlayer.getName());
                            System.out.println("Games played: " + currentPlayer.getGamesPlayed());
                            System.out.println("Games won: " + currentPlayer.getGamesWon());
                            System.out.println();
                            show(currentMenu);
                        }
                    }
                    scannerFile.close();
                } catch (FileNotFoundException e) {
                }
                break;
            }
            case 3: { //savePlayer();
                if (currentPlayer != null) {
                    try {
                        PrintWriter out = new PrintWriter("hangman" + currentPlayer.getName() + ".txt");
                        out.println(currentPlayer.getName());
                        out.println(currentPlayer.getGamesPlayed());
                        out.println(currentPlayer.getGamesWon());
                        out.close();
                        show(currentMenu);
                        System.out.println("Saved");
                    } catch (FileNotFoundException exception) {
                    }
                }
                else {
                    System.out.println("Before You save a player, You must first create a player");
                    System.out.println();
                    show(currentMenu);
                }
                break;
            }
            case 4: {

                System.exit(0);
                //quit();
                break;
            }
            case 5: {
                currentMenu = startMenu;
                show(startMenu);
                break;
            }
        }
    }

    public int getInt() {
        int input = 0;
        boolean loop = true;
        while (loop) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                loop = false;
                scanner.nextLine();
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return input;
    }

    public String getString() {
        String input = "";
        boolean loop = true;
        while (loop) {
            if (scanner.hasNext()) {
                input = scanner.next();
                loop = false;
                scanner.nextLine();
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return input;
    }
    // blir error med fel inmatning
    // Vi behöver fixa mer
    public char getAlpha() { //getChar()
        String input = "";
        char ch = ' ';
        boolean loop = true;
        while (loop) {
            if (scanner.hasNext()) {
                input = scanner.next();
                if (input.length() == 1) {
                    ch = input.charAt(0);
                    boolean isLetter = Character.isLetter(ch);
                    if (isLetter) {
                        loop = false;
                        scanner.nextLine();
                    }
                    else {
                        scanner.nextLine();
                        System.out.println("1Incorrect input. Try again");
                    }
                }
                else {
                    scanner.nextLine();
                    System.out.println("2Incorrect input. Try again");
                }
            }
            else {
                scanner.nextLine();
                System.out.println("3Incorrect input. Try again");
            }
        }
        return ch;
    }
}
