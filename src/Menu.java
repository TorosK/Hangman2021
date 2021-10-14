import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Vi har skapat två stringarrayer där vi lagt in strängar för de menyval vi vill ha med. Vi har använt oss av två stycken
 * konstruktorer för att skapa menyerna.
 */
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private Player currentPlayer = null;
    private String[] currentMenu = null;
    private String[] startMenu = {"Welcome! Please choose menu option: ", "1: Play", "2: Load Player", "3: Save Player", "4: Quit"};
    private String[] endOfGameMenu = {"1: Play again", "5: Return to Main Menu"};

    public Menu() {
        currentMenu = startMenu;
        show(startMenu);
    }

    /**Den här konstruktorn tar emot spelaren för att kunna starta nytt spel igen.
     */
    public Menu(Player player) {
        currentPlayer = player;
        currentMenu = endOfGameMenu;
        show(endOfGameMenu);
    }

    /**
     * show visar våra menyer.
     * Vi har en switch för att välja menyval. Vi använder metoden getInt för att fånga upp
     * användarens inmatning i menyerna.
     * case 1 vi kollar om det redan finns en spelare, annars frågar vi efter ett namn och skapar då en ny spelare.
     * case 2 Vi frågar efter ett namn, skapar en fil kopplad till en scanner och läser in textfilen på disken kopplad
     * till spelaren. Skapar en ny spelare med den infon från disken. Sen skriver vi ut spelarens instansvariabler.
     * case 3 Vi kollar först att det verkligen finns en spelare att spara. Vi skapar ett printwriter objekt och skriver
     * spelarens instansvariabler till textfilen.
     *
     * @param argument är den array vi skickar till metoden.
     */
    public void show(String[] argument) {
        String[] meny = argument;
        for (int i = 0; i < argument.length; i++) {
            System.out.println(meny[i]);
        }
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
                        System.out.println();
                        System.out.println("Saved player: " + currentPlayer.getName());
                        System.out.println();
                        show(currentMenu);

                    } catch (FileNotFoundException exception) {
                    }
                } else {
                    System.out.println("Before You save a player, You must first create a player");
                    System.out.println();
                    show(currentMenu);
                }
                break;
            }
            case 4: { //quit();
                System.exit(0);
                break;
            }
            case 5: {
                currentMenu = startMenu;
                show(startMenu);
                break;
            }
        }
    }

    /**
     * Den här metoden används för att kontrollera input i våra menyer.
     * Scannern läser användarens input och kontrollerar om det är ett heltal och sedan vilken meny som det gäller.
     * Vi har en while loop för att fortsätta ifall användaren skriver fel saker.
     *
     * @return
     */
    public int getInt() {
        int input = 0;
        boolean loop = true;
        while (loop) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input > 0 && input < 5 && currentMenu == startMenu) {
                    loop = false;
                    scanner.nextLine();
                } else if (input == 1 || input == 5 && currentMenu == endOfGameMenu) {
                    loop = false;
                    scanner.nextLine();
                } else {
                    System.out.println("Incorrect input. Try again");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return input;
    }

    /**
     * Vi använder den här metoden för att läsa in spelarens namn. Vi trimmar bort blanksteg. Vi kollar även att
     * strängen inte är helt tom.
     * @return
     */
    public String getString() {
        String input = "";
        String inputTrim = "";
        boolean loop = true;
        while (loop) {
            if (scanner.hasNext()) {
                input = scanner.next();
                inputTrim = input.trim();
                if (inputTrim.length() == 0) {
                    System.out.println("Incorrect input. Try again");
                    scanner.nextLine();
                }
                loop = false;
                scanner.nextLine();
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return inputTrim;
    }
    /** Vi använder den här metoden för att kontrollera i spelet, användarens input. Vi kollar att det verkligen bara
     * är ett tecken och att det är en bokstav.
     * @return
     */
    public char getAlpha() {
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
                    } else {
                        scanner.nextLine();
                        System.out.println("Incorrect input. Try again");
                    }
                } else {
                    scanner.nextLine();
                    System.out.println("Incorrect input. Try again");
                }
            } else {
                scanner.nextLine();
                System.out.println("Incorrect input. Try again");
            }
        }
        return ch;
    }
    public Scanner getScanner() {
        return scanner;
    }
}
