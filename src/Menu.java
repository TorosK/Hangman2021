// vi måste fixa spara statistik efter spel avklarad, auto.
//fortsätta med loadgame på rad 377

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Vi har skapat två stringarrayer där vi lagt in strängar för de menyval vi vill ha med. Vi har använt oss av två stycken
 * konstruktorer för att skapa menyerna.
 */
public class Menu {
    final private int SINGLE_PLAYER = 1;
    final private int MULTI_PLAYER = 2;
    final private int LOAD_GAME = 3;
    final private int DELETE_PLAYER = 7;
    final private int HIGH_SCORE = 5;
    final private int PLAYER_STATS = 6;
    final private int QUIT = 4;
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
    private Player currentPlayer = null;

    private HighScore highScore = null;
    private Scanner scanner = new Scanner(System.in);
    private int choice = 0;
    private String[] currentMenu = null;
    private String[] startMenu = {"\nWelcome to the Hangman game! \nThe noose awaits you!" +
            "\n\nPlease choose menu option: ",
            SINGLE_PLAYER + ": Single Player",
            MULTI_PLAYER + ": Multiplayer",
            LOAD_GAME + ": Load Game",
            //DELETE_PLAYER + ": Delete Player",
            //HIGH_SCORE + ": High Score",
            //PLAYER_STATS + ": Player Stats",
            QUIT + ": Quit"};
    private String[] singlePlayerEndOfGameMenu = {SINGLE_PLAYER_START_NEW_GAME + ": Start New Game",
            RETURN_TO_MAIN_MENU + ": Return to Main Menu"};
    private String[] multiPlayerEndOfGameMenu = {MULTI_PLAYER_START_NEW_GAME + ": Start New Game",
            RETURN_TO_MAIN_MENU + ": Return to Main Menu"};
    private String[] singlePlayerMenu = {SINGLE_PLAYER_NEW_PLAYER + ": New Player",
            SINGLE_PLAYER_LOAD_PLAYER + ": Load Player"};
    private String[] multiPlayerMenu = {"2 to 4 Players only\n",
            MULTI_PLAYER_NEW_PLAYER + ": New Player", MULTI_PLAYER_LOAD_PLAYER + ": Load Player"};
    private String[] multiPlayerMenuPlusStart = {"2 to 4 Players only\n",
            MULTI_PLAYER_NEW_PLAYER + ": New Player", MULTI_PLAYER_LOAD_PLAYER + ": Load Player",
            MULTI_PLAYER_START_NEW_GAME + ": Start New Game"};

    public Menu() {
        highScore = new HighScore();
        highScore.loadHighScoreList();
        currentMenu = startMenu;
        show(startMenu);
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
        int menuChoice = getInt();
        switch (menuChoice) {
            case SINGLE_PLAYER: {
                currentMenu = singlePlayerMenu;
                show(singlePlayerMenu);
                break;
            }
            case MULTI_PLAYER: {
                currentMenu = multiPlayerMenu;
                show(multiPlayerMenu);
                break;
            }
            case LOAD_GAME: {
                loadGame();
                break;
            }
            case DELETE_PLAYER: { //7
                break;
            }
            case HIGH_SCORE: { //5
                break;
            }
            case PLAYER_STATS: { //6
                break;
            }
            case QUIT: { //4 flyttad till 4 för att vi använder inte just nu delete, highscore och playerstats.
                System.exit(0);
                break;
            }
            case SINGLE_PLAYER_NEW_PLAYER: { //8
                System.out.println("Please give your player a name: ");
                String playerName = getString();
                playerName = playerName.toUpperCase();
                player1 = new Player(playerName);
                System.out.println(player1.getName());
                new Game(player1, this);
                break;
            }
            case SINGLE_PLAYER_LOAD_PLAYER: { //9
                try {
                    System.out.println("Input name of player: ");
                    String name = getString();
                    name = name.toUpperCase();
                    File file = new File("HangmanPlayerFile" + name + ".txt");
                    Scanner scannerFile = new Scanner(file);
                    while (scannerFile.hasNext()) {
                        if (name.equals(scannerFile.next())) {
                            player1 = new Player(name, scannerFile.nextInt(), scannerFile.nextInt(),
                                    scannerFile.nextInt(), scannerFile.nextInt(), scannerFile.next());
                        }
                    }
                    scannerFile.close();
                } catch (FileNotFoundException e) {//fixat att ladda spelare som inte finns
                    System.out.println();
                    System.out.println("Player not found, please try again!");
                    System.out.println();
                    show(currentMenu);
                }
                new Game(player1, this);
                break;
            }
            case MULTI_PLAYER_NEW_PLAYER: { //10
                System.out.println("Please give your player a name: ");
                String playerName = getString();
                playerName = playerName.toUpperCase();
                Player player = new Player(playerName);
                numberOfPlayersCounter++;
                if (numberOfPlayersCounter == 1) {
                    player1 = player;
                    System.out.println("Player 1: " + player1.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n");
                    show(currentMenu);
                } else if (numberOfPlayersCounter == 2) {
                    currentMenu = multiPlayerMenuPlusStart;
                    player2 = player;
                    System.out.println("Player 2: " + player2.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName() + "\n");
                    show(currentMenu);

                } else if (numberOfPlayersCounter == 3) {
                    currentMenu = multiPlayerMenuPlusStart;
                    player3 = player;
                    System.out.println("Player 3: " + player3.getName() + " is created.");
                    System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName()
                            + "\n" + player3.getName() + "\n");
                    show(currentMenu);
                } else if (numberOfPlayersCounter == 4) {
                    player4 = player;
                    new Game(player1, player2, player3, player4, this);
                }
                break;
            }
            case MULTI_PLAYER_LOAD_PLAYER: { //11
                try {
                    System.out.println("Input name of player: ");
                    String name = getString();
                    name = name.toUpperCase();
                    File file = new File("HangmanPlayerFile" + name + ".txt");
                    Scanner scannerFile = new Scanner(file);
                    while (scannerFile.hasNext()) {
                        if (name.equals(scannerFile.next())) {
                            currentPlayer = new Player(name, scannerFile.nextInt(), scannerFile.nextInt(),
                                    scannerFile.nextInt(), scannerFile.nextInt(), scannerFile.next());
                            numberOfPlayersCounter++;
                            if (numberOfPlayersCounter == 1) {
                                player1 = currentPlayer;
                                System.out.println("Player 1: " + player1.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n");
                                show(currentMenu);
                            } else if (numberOfPlayersCounter == 2) {
                                player2 = currentPlayer;
                                currentMenu = multiPlayerMenuPlusStart;
                                System.out.println("Player 2: " + player2.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName() + "\n");
                                show(currentMenu);
                            } else if (numberOfPlayersCounter == 3) {
                                player3 = currentPlayer;
                                System.out.println("Player 3: " + player3.getName() + " is created.");
                                System.out.println("List of Players: \n" + player1.getName() + "\n" + player2.getName()
                                        + "\n" + player3.getName() + "\n");
                                show(currentMenu);
                            } else if (numberOfPlayersCounter == 4) {
                                player4 = currentPlayer;
                                new Game(player1, player2, player3, player4, this);
                            }
                        }
                    }
                    scannerFile.close();
                } catch (FileNotFoundException e) {
                    System.out.println();
                    System.out.println("Player not found, please try again!");
                    System.out.println();
                    show(currentMenu);
                }
                break;
            }
            case SINGLE_PLAYER_START_NEW_GAME: { //12
                new Game(player1, this);
                break;
            }
            case MULTI_PLAYER_START_NEW_GAME: { //13
                if (numberOfPlayersCounter == 2) {
                    new Game(player1, player2, this);
                } else if (numberOfPlayersCounter == 3) {
                    new Game(player1, player2, player3, this);
                } else if (numberOfPlayersCounter == 4) {
                    new Game(player1, player2, player3, player4, this);
                }
                break;
            }
            case RETURN_TO_MAIN_MENU: { //14
                currentMenu = startMenu;
                numberOfPlayersCounter = 0;
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
                } else if (currentMenu == singlePlayerMenu && (input == SINGLE_PLAYER_NEW_PLAYER ||
                        input == SINGLE_PLAYER_LOAD_PLAYER)) {
                    loop = false;
                    scanner.nextLine();
                } else if (currentMenu == singlePlayerEndOfGameMenu && (input == SINGLE_PLAYER_START_NEW_GAME ||
                        input == RETURN_TO_MAIN_MENU)) {
                    loop = false;
                    scanner.nextLine();
                } else if (currentMenu == multiPlayerEndOfGameMenu && (input == MULTI_PLAYER_START_NEW_GAME ||
                        input == RETURN_TO_MAIN_MENU)) {
                    loop = false;
                    scanner.nextLine();
                } else if ((currentMenu == multiPlayerMenu ||
                        currentMenu == multiPlayerMenuPlusStart) && (input == MULTI_PLAYER_NEW_PLAYER ||
                        input == MULTI_PLAYER_LOAD_PLAYER ||
                        input == MULTI_PLAYER_START_NEW_GAME)) {
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
     *
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

    /**
     * Vi använder den här metoden för att kontrollera i spelet, användarens input. Vi kollar att det verkligen bara
     * är ett tecken och att det är en bokstav.
     *
     * @return
     */
    public char getAlpha(Game game) {
        String input = "";
        char ch = ' ';
        boolean loop = true;
        while (loop) {
            if (scanner.hasNext()) {
                input = scanner.next();
                if (input.length() == 1) {
                    ch = input.charAt(0);
                    if (ch == '+') {
                        saveGame(game);
                        currentMenu = startMenu;
                        show(startMenu);
                        break;
                    }
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

    public void newScanner() {
        scanner = new Scanner(System.in);
    }

    public String[] getSinglePlayerEndOfGameMenu() {
        currentMenu = singlePlayerEndOfGameMenu;
        return singlePlayerEndOfGameMenu;
    }

    public String[] getMultiPlayerEndOfGameMenu() {
        currentMenu = multiPlayerEndOfGameMenu;
        return multiPlayerEndOfGameMenu;
    }

    public void saveGame(Game game) {
        System.out.println("Type name of game: ");
        String fileName = getString();
        int numberOfPlayers = game.getGameType();
        try {
            PrintWriter outSaveGame = new PrintWriter("HangmanSaveFile" + fileName + ".txt");
            for (int i = 0; i < numberOfPlayers; i++) {
                outSaveGame.println(game.getPlayerInfo(i));
            }
            outSaveGame.println(game.getGameInfo());
            outSaveGame.close();
        } catch (FileNotFoundException exception) {
        }
    }

    public void loadGame() {
        ArrayList<String> loadList = new ArrayList<>();
        int fileRowCounter = 0;
        int filePlayerRowCounter = 0;
        try {
            System.out.println("Type name of game: ");
            String gameName = getString();
            File file = new File("HangmanSaveFile" + gameName + ".txt");
            Scanner scannerFile = new Scanner(file);
            while (scannerFile.hasNextLine()) {
                loadList.add(scannerFile.nextLine());
                fileRowCounter++;
            }
            for (int i = 0; i < fileRowCounter - 1; i++) {
                if (i == 0) {
                    String string = loadList.get(i);
                    Scanner scanner = new Scanner(string);
                    player1 = new Player(scanner.next(), scanner.next(), scanner.next(),
                            scanner.next(), scanner.next(), scanner.next());
                    filePlayerRowCounter++;
                } else if (i == 1) {
                    String string = loadList.get(i);
                    Scanner scanner = new Scanner(string);
                    player2 = new Player(scanner.next(), scanner.next(), scanner.next(),
                            scanner.next(), scanner.next(), scanner.next());
                    filePlayerRowCounter++;
                } else if (i == 2) {
                    String string = loadList.get(i);
                    Scanner scanner = new Scanner(string);
                    player3 = new Player(scanner.next(), scanner.next(), scanner.next(),
                            scanner.next(), scanner.next(), scanner.next());
                    filePlayerRowCounter++;
                } else if (i == 3) {
                    String string = loadList.get(i);
                    Scanner scanner = new Scanner(string);
                    player4 = new Player(scanner.next(), scanner.next(), scanner.next(),
                            scanner.next(), scanner.next(), scanner.next());
                    filePlayerRowCounter++;
                }
                scannerFile.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            show(startMenu);
        }

        if (fileRowCounter - filePlayerRowCounter == 1 && filePlayerRowCounter == 1) {
            String string = loadList.get(1);
            Scanner scanner = new Scanner(string);
            new Game(player1, this, scanner.next(), scanner.next(),
                    scanner.next(),
                    scanner.next());
        }
        if (fileRowCounter - filePlayerRowCounter == 1 && filePlayerRowCounter == 2) {
            String string = loadList.get(2);
            Scanner scanner = new Scanner(string);
            new Game(player1, player2, this, scanner.next(), scanner.next(),
                    scanner.next(), scanner.next());
        }
        if (fileRowCounter - filePlayerRowCounter == 1 && filePlayerRowCounter == 3) {
            String string = loadList.get(3);
            Scanner scanner = new Scanner(string);
            new Game(player1, player2, player3, this, scanner.next(), scanner.next(),
                    scanner.next(), scanner.next());
        }
        if (fileRowCounter - filePlayerRowCounter == 1 && filePlayerRowCounter == 4) {
            String string = loadList.get(4);
            Scanner scanner = new Scanner(string);
            new Game(player1, player2, player3, player4, this, scanner.next(), scanner.next(),
                    scanner.next(), scanner.next());
        }
    }

    public HighScore getHighScoreObject() {
        return highScore;
    }
}

