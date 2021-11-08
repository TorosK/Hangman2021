// vi måste fixa spara statistik efter spel avklarad, auto.
// Som load player i switch case.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Vi har skapat två stringarrayer där vi lagt in strängar för de menyval vi vill ha med. Vi har använt oss av två stycken
 * konstruktorer för att skapa menyerna.
 */
public class Menu {
    final private int SINGLE_PLAYER = 1;
    final private int MULTI_PLAYER = 2;
    final private int LOAD_GAME = 3;
    final private int DELETE_PLAYER = 4;
    final private int HIGH_SCORE = 5;
    final private int PLAYER_STATS = 6;
    final private int QUIT = 7;
    final private int SINGLE_PLAYER_NEW_PLAYER = 8;
    final private int SINGLE_PLAYER_LOAD_PLAYER = 9;
    final private int MULTI_PLAYER_NEW_PLAYER = 10;
    final private int MULTI_PLAYER_LOAD_PLAYER = 11;
    final private int SINGLE_PLAYER_START_NEW_GAME = 12;
    final private int MULTI_PLAYER_START_NEW_GAME = 13;
    final private int RETURN_TO_MAIN_MENU = 14;

    private int numberOfPlayersCounter = 0;
    private Player player1 = null;
    private Player player2 = null;
    private Player player3 = null;
    private Player player4 = null;

    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private Player currentPlayer = null;
    private String[] currentMenu = null;
    private String[] startMenu = {"Welcome! Please choose menu option: ",
            SINGLE_PLAYER + ": Single Player",
                //a: New Player
                //b: Load Player
            MULTI_PLAYER + ": Multiplayer", // 2 to 4 players
                //c: New Player
                //d: Load Player
                    //e: Play
            // under spelets gång: "8: Save Game",
            LOAD_GAME + ": Load Game",
            DELETE_PLAYER + ": Delete Player",
            HIGH_SCORE + ": High Score",
            PLAYER_STATS + ": Player Stats",
            QUIT + ": Quit"};

    private String[] singlePlayerEndOfGameMenu = {SINGLE_PLAYER_START_NEW_GAME + ": Start New Game",
            RETURN_TO_MAIN_MENU + ": Return to Main Menu"};
    private String[] multiPlayerEndOfGameMenu = {MULTI_PLAYER_START_NEW_GAME + ": Start New Game",
            RETURN_TO_MAIN_MENU+ ": Return to Main Menu"};
    private String[] singlePlayerMenu = {SINGLE_PLAYER_NEW_PLAYER + ": New Player", SINGLE_PLAYER_LOAD_PLAYER + ": Load Player"};
    private String[] multiPlayerMenu = {"2 to 4 Players Only\n",
            MULTI_PLAYER_NEW_PLAYER + ": New Player", MULTI_PLAYER_LOAD_PLAYER + ": Load Player"};
    public Menu() {
        currentMenu = startMenu;
        show(startMenu);
    }

    /**Den här konstruktorn tar emot spelaren för att kunna starta nytt spel igen.
     */
    public Menu(Player player) {
        currentPlayer = player;
        currentMenu = singlePlayerEndOfGameMenu;
        show(singlePlayerEndOfGameMenu);
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
        String[] menu = argument;
        for (int i = 0; i < argument.length; i++) {
            System.out.println(menu[i]);
        }
        if (numberOfPlayersCounter > 1) {
            System.out.println(MULTI_PLAYER_START_NEW_GAME + ": Start Game");
        }
        int menuChoice = getInt();
        switch (menuChoice) {
            case SINGLE_PLAYER: {
                show(singlePlayerMenu);
                currentMenu = singlePlayerMenu;
                break;
            }
            case MULTI_PLAYER: {
                currentMenu = multiPlayerMenu;
                show(multiPlayerMenu);
                break;
            }
            case DELETE_PLAYER: { //4
                break;
            }
            case HIGH_SCORE: { //5
                break;
            }
            case PLAYER_STATS: { //6
                break;
            }
            case QUIT: { //7
                System.exit(0);
                break;
            }
            case SINGLE_PLAYER_NEW_PLAYER: { //8
                System.out.println("Please give your player a name: ");
                String playerName = getString();
                Player player = new Player(playerName);
                currentPlayer = player;
                System.out.println(currentPlayer.getName());
                new Game(currentPlayer, this);
                break;
            }
            case SINGLE_PLAYER_LOAD_PLAYER: { //9
                try {
                    System.out.println("Input name of player: ");
                    String name = getString();
                    File file = new File("hangman" + name + ".txt");
                    Scanner scannerFile = new Scanner(file);
                    while (scannerFile.hasNext()) {
                        if (name.equals(scannerFile.next())) {
                            currentPlayer = new Player(name, scannerFile.nextInt(), scannerFile.nextInt());
                            show(currentMenu);
                            }
                            //System.out.println("Name of player: " + currentPlayer.getName());
                            //System.out.println("Games played: " + currentPlayer.getGamesPlayed());
                            //System.out.println("Games won: " + currentPlayer.getGamesWon());
                            // "Single Player Max Points: " + ...
                            // "Tot.p
                            // "Medelp.
                            // Multiplayer Max
                            // Multiplayer Tot
                            // Multiplayer Average
                        }
                    scannerFile.close();
                } catch (FileNotFoundException e) {//fixat att ladda spelare som inte finns
                    System.out.println();
                    System.out.println("Player not found, please try again!");
                    System.out.println();
                    show(currentMenu);
                }
                break;
            }
            case MULTI_PLAYER_NEW_PLAYER: { //10
                System.out.println("Please give your player a name: ");
                String playerName = getString();
                Player player = new Player(playerName);

                numberOfPlayersCounter++;
                if (numberOfPlayersCounter == 1) {
                    player1 = player;
                    System.out.println("Player 1: " + player1.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n");
                    show(currentMenu);
                }
                else if (numberOfPlayersCounter == 2) {
                    player2 = player;
                    System.out.println("Player 2: " + player2.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName() + "\n");
                    show(currentMenu);

                }
                else if (numberOfPlayersCounter == 3) {
                    player3 = player;
                    System.out.println("Player 3: " + player3.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName()
                            + "\n" + player3.getName() + "\n");
                    show(currentMenu);
                    //System.out.println(MULTI_PLAYER_START_NEW_GAME + ": Start Game");
                }
                else if (numberOfPlayersCounter == 4) {
                    player4 = player;
                    numberOfPlayersCounter = 0;
                    new Game(player1, player2, player3, player4, this);
                }
                break;
            }
            case MULTI_PLAYER_LOAD_PLAYER: { //11
                try {
                    System.out.println("Input name of player: ");
                    String name = getString();
                    File file = new File("hangman" + name + ".txt");
                    Scanner scannerFile = new Scanner(file);
                    while (scannerFile.hasNext()) {
                        if (name.equals(scannerFile.next())) {
                            currentPlayer = new Player(name, scannerFile.nextInt(), scannerFile.nextInt());
                            numberOfPlayersCounter++;
                            if (numberOfPlayersCounter == 1) {
                                player1 = currentPlayer;
                                System.out.println("Player 1: " + player1.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n");
                                show(currentMenu);
                            }
                            else if (numberOfPlayersCounter == 2) {
                                player2 = currentPlayer;
                                System.out.println("Player 2: " + player2.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName() + "\n");
                                show(currentMenu);
                                System.out.println(MULTI_PLAYER_START_NEW_GAME + ": Start Game");
                            }
                            else if (numberOfPlayersCounter == 3) {
                                player3 = currentPlayer;
                                System.out.println("Player 3: " + player3.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName()
                                        + "\n" + player3.getName() + "\n");
                                show(currentMenu);
                                System.out.println(MULTI_PLAYER_START_NEW_GAME + ": Start Game");
                            }
                            else if (numberOfPlayersCounter == 4) {
                                player4 = currentPlayer;
                                numberOfPlayersCounter = 0;
                                new Game(player1, player2, player3, player4, this);
                            }
                            //System.out.println("Name of player: " + currentPlayer.getName());
                            //System.out.println("Games played: " + currentPlayer.getGamesPlayed());
                            //System.out.println("Games won: " + currentPlayer.getGamesWon());
                            // "Single Player Max Points: " + ...
                            // "Tot.p
                            // "Medelp.
                            // Multiplayer Max
                            // Multiplayer Tot
                            // Multiplayer Average
                        }
                    }
                    scannerFile.close();
                } catch (FileNotFoundException e) {//fixat att ladda spelare som inte finns
                    System.out.println();
                    System.out.println("Player not found, please try again!");
                    System.out.println();
                    show(currentMenu);
                }
                break;
            }
            case SINGLE_PLAYER_START_NEW_GAME: {
                break;
            }
            case MULTI_PLAYER_START_NEW_GAME: {
                if (numberOfPlayersCounter == 2) {
                    numberOfPlayersCounter = 0;
                    new Game(player1, player2, this);
                }
                else {
                    numberOfPlayersCounter = 0;
                    new Game(player1, player2, player3, this);
                }
                break;
            }
            case RETURN_TO_MAIN_MENU: {
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
                if (input > 0 && input < 8 && currentMenu == startMenu) {
                    loop = false;
                    scanner.nextLine();
                } else if (input == SINGLE_PLAYER_START_NEW_GAME || input == RETURN_TO_MAIN_MENU && currentMenu == singlePlayerEndOfGameMenu) {
                    loop = false;
                    scanner.nextLine();
                } else if (input == MULTI_PLAYER_NEW_PLAYER || input == MULTI_PLAYER_LOAD_PLAYER || input == MULTI_PLAYER_START_NEW_GAME && currentMenu == multiPlayerMenu) {
                    loop = false;
                    scanner.nextLine();
                }
                else {
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

    public String[] getSinglePlayerEndOfGameMenu() {
        return singlePlayerEndOfGameMenu;
    }
    /*public void savePlayer (Player player){
        try {
            PrintWriter out = new PrintWriter("hangman" + player.getName() + ".txt");
            out.println(player.getName());
            out.close();
        } catch (FileNotFoundException exception) {
        }
    }*/
}
