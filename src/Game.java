import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    private Player player1 = null;
    private Player player2 = null;
    private Player player3 = null;
    private Player player4 = null;
    private Player firstPlayer = null;
    private Player secondPlayer = null;
    private Player thirdPlayer = null;
    private Player fourthPlayer = null;
    private Player holderPlayer = null;
    private Menu menu = null;
    private String hiddenWord = "";
    private String usedChars = "";
    private String hangMeterAsterisk = "";
    private String theWord = "";
    private String word = "";
    private boolean ONE_PLAYER_GAME = false;
    private boolean TWO_PLAYER_GAME = false;
    private boolean THREE_PLAYER_GAME = false;
    private boolean FOUR_PLAYER_GAME = false;
    private boolean correctGuessedChar = false;
    private boolean gameRunning = true;
    private boolean player1Winner = false;
    private boolean player2Winner = false;
    private boolean player3Winner = false;
    private boolean player4Winner = false;

    private char[] charArray = new char[20];
    private String[] updatedArray = new String[30];
    private String[] wordArray194k = new String[194433];
    private ArrayList<Player> playerArrayList = new ArrayList<>();

    private int numberOfHangedPlayers = 0;
    private int numberOfChars = 0;
    private int correctGuessCounter = 0;

    //private double player1Points = 0;
    //private double player2Points = 0;
    //private double player3Points = 0;
    //private double player4Points = 0;

    private File wordFile = new File("english3.txt");
    private Scanner scannerWordFile;

    /**
     * Konstruktorn tar emot en spelare och meny. Anropar getWord() för att få ett ord att spela med. Anropar showGame()
     * för att skriva ut spelet. While loopar spelarens förslag, anropar getAlpha() för att kontrollera inmatning och
     * sedan update() för att uppdatera spelet med den gissade bokstaven.
     *
     * @param player1
     * @param menu
     */
    public Game(Player player1, Menu menu) {
        ONE_PLAYER_GAME = true;
        this.player1 = player1;
        firstPlayer = player1;
        this.menu = menu;
        word = getWord();
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Menu menu) {
        TWO_PLAYER_GAME = true;
        this.player1 = player1;
        this.player2 = player2;
        playerArrayList.add(player1);
        playerArrayList.add(player2);
        Collections.shuffle(playerArrayList);
        firstPlayer = playerArrayList.get(0);
        secondPlayer = playerArrayList.get(1);
        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " + playerArrayList.get(1).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Player player3, Menu menu) {
        THREE_PLAYER_GAME = true;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        playerArrayList.add(player1);
        playerArrayList.add(player2);
        playerArrayList.add(player3);
        Collections.shuffle(playerArrayList);
        firstPlayer = playerArrayList.get(0);
        secondPlayer = playerArrayList.get(1);
        thirdPlayer = playerArrayList.get(2);
        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Player player3, Player player4, Menu menu) {
        FOUR_PLAYER_GAME = true;
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        playerArrayList.add(player1);
        playerArrayList.add(player2);
        playerArrayList.add(player3);
        playerArrayList.add(player4);
        Collections.shuffle(playerArrayList);
        firstPlayer = playerArrayList.get(0);
        secondPlayer = playerArrayList.get(1);
        thirdPlayer = playerArrayList.get(2);
        fourthPlayer = playerArrayList.get(3);
        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + ", "
                + playerArrayList.get(3).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Menu menu,
                String theWord, String hiddenWord, String usedChars) {
        ONE_PLAYER_GAME = true;
        this.firstPlayer = player1;
        playerArrayList.add(firstPlayer);
        this.menu = menu;
        this.theWord = theWord;
        this.hiddenWord = hiddenWord;
        this.usedChars = usedChars;
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        System.out.println("Welcome Back " + playerArrayList.get(0).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Menu menu,
                String theWord, String hiddenWord, String usedChars) {
        TWO_PLAYER_GAME = true;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
        playerArrayList.add(firstPlayer);
        playerArrayList.add(secondPlayer);
        this.menu = menu;
        this.theWord = theWord;
        this.hiddenWord = hiddenWord;
        this.usedChars = usedChars;
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        System.out.println("Welcome Back " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Player player3, Menu menu,
                String theWord, String hiddenWord, String usedChars) {
        THREE_PLAYER_GAME = true;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
        this.thirdPlayer = player3;
        playerArrayList.add(firstPlayer);
        playerArrayList.add(secondPlayer);
        playerArrayList.add(thirdPlayer);
        this.menu = menu;
        this.theWord = theWord;
        this.hiddenWord = hiddenWord;
        this.usedChars = usedChars;
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        System.out.println("Welcome Back " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    public Game(Player player1, Player player2, Player player3, Player player4, Menu menu,
                String theWord, String hiddenWord, String usedChars) {
        FOUR_PLAYER_GAME = true;
        this.firstPlayer = player1;
        this.secondPlayer = player2;
        this.thirdPlayer = player3;
        this.fourthPlayer = player4;
        playerArrayList.add(firstPlayer);
        playerArrayList.add(secondPlayer);
        playerArrayList.add(thirdPlayer);
        playerArrayList.add(fourthPlayer);
        this.menu = menu;
        this.theWord = theWord;
        this.hiddenWord = hiddenWord;
        this.usedChars = usedChars;
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        System.out.println("Welcome Back " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + ", "
                + playerArrayList.get(3).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha(this));
                update(guessedChar);
            }
        }
    }

    /**
     * update() tar in den gissade bokstaven, lägger den till variabeln usedChars. Vi lägger till den gissade bokstaven
     * till en char[] charAlpha. Vi for-loopar sedan igenom och kollar om den bokstaven finns i vår andra char[]
     * charArray som innehåller hela det korrekta ordet. Om den finns så lägger vi till den till en String[]
     * updatedArray och räknar upp vår räknare. Om bokstaven inte var korrekt lägger vi till en asterisk till vår
     * hangMeterAsterisk. Vi använder sedan en for-loop för att bygga ordet som det just nu ser ut med understreck och
     * rätt gissade bokstäver.
     *
     * @param alpha
     */
    public void update(String alpha) {
        correctGuessCounter = 0;
        if (!alpha.equals("+")) {
            usedChars += alpha;
            String string = "";
            char[] charAlpha = alpha.toCharArray();
            for (int i = 0; i < numberOfChars; i++) {
                if (charAlpha[0] == charArray[i]) {
                    updatedArray[i] = alpha;
                    correctGuessCounter++;
                    correctGuessedChar = true;
                }
            }
            if (correctGuessedChar) {
                System.out.println("Correct character!");
                correctGuessedChar = false;
                for (int j = 0; j < numberOfChars; j++) {
                    string += updatedArray[j];
                    hiddenWord = string;
                }
                if (hiddenWord.equals(word)) {
                    setPoints();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gameRunning = false;
                    System.out.println("The correct word was: " + word);
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("CONGRATULATIONS! - Word complete! +1 point for " + firstPlayer.getName());
                    System.out.println("-----------------------------------------------------------");
                    word = getWord();
                }
            }
            if (correctGuessCounter == 0) {
                firstPlayer.setLives();
                System.out.println("Incorrect! " + firstPlayer.getName() + " lost one healthpoint! ");
                System.out.print("HealthBar: ");
                healthBarDisplay(firstPlayer.getLives());
                System.out.println();
                if (firstPlayer.getLives() == 0) {
                    System.out.println();
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(firstPlayer.getName() + " has been hanged");
                    System.out.println("-----------------------------------------------------------");
                    firstPlayer.setPlayerGameOver(true);
                    numberOfHangedPlayers++;
                    if (ONE_PLAYER_GAME && numberOfHangedPlayers == 1) {
                        numberOfHangedPlayers = 0;
                        gameOverDisplay();
                        player1.setResetLives();
                        player1.setPlayerGameOver(false);
                        ONE_PLAYER_GAME = false;
                        menu.show(menu.getSinglePlayerEndOfGameMenu());
                    } else if (TWO_PLAYER_GAME && numberOfHangedPlayers == 2) {
                        numberOfHangedPlayers = 0;
                        gameOverDisplay();
                        player1.setResetLives();
                        player2.setResetLives();
                        player1.setPlayerGameOver(false);
                        player2.setPlayerGameOver(false);
                        TWO_PLAYER_GAME = false;
                        menu.show(menu.getMultiPlayerEndOfGameMenu());
                    } else if (THREE_PLAYER_GAME && numberOfHangedPlayers == 3) {
                        numberOfHangedPlayers = 0;
                        player1.setResetLives();
                        player2.setResetLives();
                        player3.setResetLives();
                        player1.setPlayerGameOver(false);
                        player2.setPlayerGameOver(false);
                        player3.setPlayerGameOver(false);
                        gameOverDisplay();
                        THREE_PLAYER_GAME = false;
                        menu.show(menu.getMultiPlayerEndOfGameMenu());
                    } else if (FOUR_PLAYER_GAME && numberOfHangedPlayers == 4) {
                        numberOfHangedPlayers = 0;
                        identifyingWinners();
                        player1.updatePlayerData();
                        player2.updatePlayerData();
                        player3.updatePlayerData();
                        player4.updatePlayerData();
                        gameOverDisplay();
                        player1.setResetLives();
                        player2.setResetLives();
                        player3.setResetLives();
                        player4.setResetLives();
                        player1.resetCurrentGamePoints();
                        player2.resetCurrentGamePoints();
                        player3.resetCurrentGamePoints();
                        player4.resetCurrentGamePoints();
                        player1.setPlayerGameOver(false);
                        player2.setPlayerGameOver(false);
                        player3.setPlayerGameOver(false);
                        player4.setPlayerGameOver(false);
                        FOUR_PLAYER_GAME = false;
                        menu.newScanner();
                        menu.show(menu.getMultiPlayerEndOfGameMenu());
                    }
                }

                if (TWO_PLAYER_GAME) {
                    holderPlayer = firstPlayer;
                    firstPlayer = secondPlayer;
                    secondPlayer = holderPlayer;
                } else if (THREE_PLAYER_GAME) {
                    do {
                        holderPlayer = firstPlayer;
                        firstPlayer = secondPlayer;
                        secondPlayer = thirdPlayer;
                        thirdPlayer = holderPlayer;
                    }
                    while (firstPlayer.isPlayerGameOver());
                } else if (FOUR_PLAYER_GAME) {
                    do {
                        holderPlayer = firstPlayer;
                        firstPlayer = secondPlayer;
                        secondPlayer = thirdPlayer;
                        thirdPlayer = fourthPlayer;
                        fourthPlayer = holderPlayer;
                    }
                    while (firstPlayer.isPlayerGameOver());
                }
            }
        }
        showGame();
    }

    /**
     * Den här metoden skriver ut "spelet" rad för rad. Variabler uppdateras i update och sedan skrivs spelet ut på
     * nytt. Vi kollar om seger eller förlust har uppnåtts och vi uppdaterar även spelarens data.
     */
    public void showGame() {
        System.out.println("\nHi " + firstPlayer.getName() + "\nI'm thinking about an English word with: " + numberOfChars + " characters");
        System.out.println("So far you have correctly guessed: " + hiddenWord);
        System.out.println("The following letters have been guessed: " + usedChars);
        System.out.print("HealthBar: ");
        healthBarDisplay(firstPlayer.getLives());
        System.out.println();
        System.out.println("Which character do you guess? (Press + and ENTER to save)");
        gameRunning = true;
    }

    /**
     * Den här metoden slumpar fram ett ord ur en ordlista som är 194433 ord lång. Vi kopplar scannern till filen och
     * fyller vår array wordArray194k med hjälp av en for-loop. Vi slumpar fram ett tal mellan 0 och 194433 som vi
     * använder som index för att välja ett ord i arrayen. Vi stoppar in det framslumpade ordet i en charArray och
     * räknar antalet tecken som vi sparar i variabeln numberOfChars.
     * Vi fyller även variabeln hiddenword med det antal understreck som motsvarar ordets längd och vi fyller även vår
     * hjälp-array updatedArray med rätt antal understreck.
     *
     * @return
     */
    public String getWord() {
        try {
            scannerWordFile = new Scanner(wordFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long wordRandomizeNr = Math.round(Math.random() * 194433);
        for (int i = 0; scannerWordFile.hasNext(); i++) {
            wordArray194k[i] = scannerWordFile.next();
        }
        theWord = wordArray194k[(int) wordRandomizeNr];
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        hiddenWord = "";
        for (int i = 0; i < numberOfChars; i++) {
            hiddenWord += "_";
            updatedArray[i] = "_";
        }
        usedChars = "";
        return theWord;
    }

    public void healthBarDisplay(int lives) {
        for (int i = 0; i < lives; i++) {
            System.out.print("*");
        }
    }

    public void gameOverDisplay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //gameRunning = false;
        System.out.println();
        System.out.println("-----------------------------------------------------------");
        System.out.println("GAME OVER!");
        System.out.println();
        if (ONE_PLAYER_GAME) {
            firstPlayerGameOverDisplay();
        } else if (TWO_PLAYER_GAME) {
            if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
            }
        } else if (THREE_PLAYER_GAME) {
            if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()
                    && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
            }
        } else if (FOUR_PLAYER_GAME) {
            if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()
                    && playerArrayList.get(2).getCurrentGameWinner() && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()
                    && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()
                    && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()
                    && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner() && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner() && playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner() && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            } else if (playerArrayList.get(2).getCurrentGameWinner() && playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
            } else if (playerArrayList.get(0).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(1).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                secondPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(2).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                thirdPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                fourthPlayerGameOverDisplay();
            } else if (playerArrayList.get(3).getCurrentGameWinner()) {
                System.out.print("WINNER: ");
                fourthPlayerGameOverDisplay();
                firstPlayerGameOverDisplay();
                secondPlayerGameOverDisplay();
                thirdPlayerGameOverDisplay();
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        System.out.println("The word was: " + word);
        System.out.println();
    }

    public void firstPlayerGameOverDisplay() {
        System.out.println(playerArrayList.get(0).getName() + ": ");
        System.out.print("Score: " + playerArrayList.get(0).getCurrentGamePoints());
        System.out.print(" Max points: " + playerArrayList.get(0).getMaxPoints());
        System.out.println(" Total points: " + playerArrayList.get(0).getTotalPoints());
        System.out.print(" Average points: " + playerArrayList.get(0).getAveragePoints());
        System.out.print(" Games played: " + playerArrayList.get(0).getGamesPlayed());
        System.out.println(" Games won: " + playerArrayList.get(0).getGamesWon());
        System.out.println();
    }

    public void secondPlayerGameOverDisplay() {
        System.out.println(playerArrayList.get(1).getName() + ": ");
        System.out.print("Score: " + playerArrayList.get(1).getCurrentGamePoints());
        System.out.print(" Max points: " + playerArrayList.get(1).getMaxPoints());
        System.out.println(" Total points: " + playerArrayList.get(1).getTotalPoints());
        System.out.print("Average points: " + playerArrayList.get(1).getAveragePoints());
        System.out.print(" Games played: " + playerArrayList.get(1).getGamesPlayed());
        System.out.println(" Games won: " + playerArrayList.get(1).getGamesWon());
        System.out.println();
    }

    public void thirdPlayerGameOverDisplay() {
        System.out.println(playerArrayList.get(2).getName() + ": ");
        System.out.print("Score: " + playerArrayList.get(2).getCurrentGamePoints());
        System.out.print(" Max points: " + playerArrayList.get(2).getMaxPoints());
        System.out.println(" Total points: " + playerArrayList.get(2).getTotalPoints());
        System.out.print("Average points: " + playerArrayList.get(2).getAveragePoints());
        System.out.print(" Games played: " + playerArrayList.get(2).getGamesPlayed());
        System.out.println(" Games won: " + playerArrayList.get(2).getGamesWon());
        System.out.println();
    }

    public void fourthPlayerGameOverDisplay() {
        System.out.println(playerArrayList.get(3).getName() + ": ");
        System.out.print("Score: " + playerArrayList.get(3).getCurrentGamePoints());
        System.out.print(" Max points: " + playerArrayList.get(3).getMaxPoints());
        System.out.println(" Total points: " + playerArrayList.get(3).getTotalPoints());
        System.out.print("Average points: " + playerArrayList.get(3).getAveragePoints());
        System.out.print(" Games played: " + playerArrayList.get(3).getGamesPlayed());
        System.out.println(" Games won: " + playerArrayList.get(3).getGamesWon());
        System.out.println();
    }

    public int getGameType() {
        int returnValue = 0;
        if (ONE_PLAYER_GAME) {
            returnValue = 1;
        } else if (TWO_PLAYER_GAME) {
            returnValue = 2;
        } else if (THREE_PLAYER_GAME) {
            returnValue = 3;
        } else if (FOUR_PLAYER_GAME) {
            returnValue = 4;
        }
        return returnValue;
    }

    public String getPlayerInfo(int number) {
        String returnValue = "";
        if (number == 0) {
            returnValue += firstPlayer.getName() + " ";
            returnValue += firstPlayer.getGamesPlayed() + " ";
            returnValue += firstPlayer.getGamesWon() + " ";
            returnValue += firstPlayer.getLives() + " ";
            returnValue += firstPlayer.isPlayerGameOver();
        } else if (number == 1) {
            returnValue += secondPlayer.getName() + " ";
            returnValue += secondPlayer.getGamesPlayed() + " ";
            returnValue += secondPlayer.getGamesWon() + " ";
            returnValue += secondPlayer.getLives() + " ";
            returnValue += secondPlayer.isPlayerGameOver();
        } else if (number == 2) {
            returnValue += thirdPlayer.getName() + " ";
            returnValue += thirdPlayer.getGamesPlayed() + " ";
            returnValue += thirdPlayer.getGamesWon() + " ";
            returnValue += thirdPlayer.getLives() + " ";
            returnValue += thirdPlayer.isPlayerGameOver();
        } else if (number == 3) {
            returnValue += fourthPlayer.getName() + " ";
            returnValue += fourthPlayer.getGamesPlayed() + " ";
            returnValue += fourthPlayer.getGamesWon() + " ";
            returnValue += fourthPlayer.getLives() + " ";
            returnValue += fourthPlayer.isPlayerGameOver();
        }
        return returnValue;
    }

    public String getGameInfo() {
        String returnValue = "";
        //Ordning av sparad data i rad för speldata. theword, hiddenWord, usedChars
        returnValue += theWord + " ";
        returnValue += hiddenWord + " ";
        returnValue += usedChars;
        return returnValue;
    }

    public void setPoints() {
        if (firstPlayer.getName().equals(player1.getName())) {
            player1.setCurrentGamePoints();
        } else if (firstPlayer.getName().equals(player2.getName())) {
            player2.setCurrentGamePoints();
        } else if (firstPlayer.getName().equals(player3.getName())) {
            player3.setCurrentGamePoints();
        } else if (firstPlayer.getName().equals(player4.getName())) {
            player4.setCurrentGamePoints();
        }
    }

    //INCOMPLETE - måste fixa ONE + TWO + THREE_PLAYER_GAME
    public void identifyingWinners() {
        ArrayList<Integer> pointsList = new ArrayList<>();
        if (FOUR_PLAYER_GAME) {
            pointsList.add(playerArrayList.get(0).getCurrentGamePoints());
            pointsList.add(playerArrayList.get(1).getCurrentGamePoints());
            pointsList.add(playerArrayList.get(2).getCurrentGamePoints());
            pointsList.add(playerArrayList.get(3).getCurrentGamePoints());
        }
        double largest = playerArrayList.get(0).getCurrentGamePoints();
        for (int i = 1; i < pointsList.size(); i++) {
            if (pointsList.get(i) > largest) {
                largest = pointsList.get(i);
            }
            if (pointsList.get(0) == largest) {
                playerArrayList.get(0).setCurrentGameWinner();
            }
            if (pointsList.get(1) == largest) {
                playerArrayList.get(1).setCurrentGameWinner();
            }
            if (pointsList.get(2) == largest) {
                playerArrayList.get(2).setCurrentGameWinner();
            }
            if (pointsList.get(3) == largest) {
                playerArrayList.get(3).setCurrentGameWinner();
            }
        }
    }
}
