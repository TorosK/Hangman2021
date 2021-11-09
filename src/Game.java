//Korrigera från rad 253, vi måste ändra vad som händer vid gameover och nästa ord
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
    private Player currentPlayer = null;
    private Player nextPlayer = null;
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
    private boolean incorrectGuessedChar = false;
    private boolean gameRunning = true;
    private boolean GAMEOVER = false;

    private char[] charArray = new char[20];
    private String[] updatedArray = new String[30];
    private String[] wordArray194k = new String[194433];
    private ArrayList<Player> playerArrayList = new ArrayList<>();

    private int numberOfHangedPlayers = 0;
    private int numberOfChars = 0;
    private int incorrectGuessCounter = 0;
    private int correctGuessCounter = 0;
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
        currentPlayer = player1;
        this.menu = menu;
        word = getWord();
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha());
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
        currentPlayer = playerArrayList.get(0);
        nextPlayer = playerArrayList.get(1);
        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " + playerArrayList.get(1).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha());
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

        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha());
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

        this.menu = menu;
        word = getWord();
        System.out.println("Welcome " + playerArrayList.get(0).getName() + ", " +
                playerArrayList.get(1).getName() + ", " + playerArrayList.get(2).getName() + ", "
                + playerArrayList.get(3).getName() + "!");
        showGame();
        gameRunning = true;
        while (gameRunning) {
            if (menu.getScanner().hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha());
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
        if (correctGuessCounter == 0) {
            incorrectGuessedChar = true;
            incorrectGuessCounter++;
            currentPlayer.setLives();
            if(TWO_PLAYER_GAME){
                holderPlayer = currentPlayer;
                currentPlayer = nextPlayer;
                nextPlayer = holderPlayer;
            }else if(THREE_PLAYER_GAME){

            }else if(FOUR_PLAYER_GAME){

            }
            if (currentPlayer.getLives() == 0) {
                currentPlayer.setPlayerGameOver(true);
                numberOfHangedPlayers++;
                if(ONE_PLAYER_GAME){
                    numberOfHangedPlayers = 0;
                    GAMEOVER = true;
                    gameOverDisplay();
                    menu.show(menu.getSinglePlayerEndOfGameMenu());
                }else if(TWO_PLAYER_GAME && numberOfHangedPlayers == 1){
                    numberOfHangedPlayers = 0;
                    GAMEOVER = true;
                    gameOverDisplay();
                    menu.show(menu.getMultiPlayerEndOfGameMenu());
                    //nextPlayer.setGamesWon()
                    //skriva grattis till segern
                    //ny meny(multiplayerGameOverMenu)
                }else if(THREE_PLAYER_GAME && numberOfHangedPlayers == 2){
                    numberOfHangedPlayers = 0;
                    GAMEOVER = true;
                    gameOverDisplay();
                    //nextPlayer.setGamesWon()
                    //skriva grattis till segern
                    //ny meny(multiplayerGameOverMenu)
                }else if(FOUR_PLAYER_GAME && numberOfHangedPlayers == 3){
                    numberOfHangedPlayers = 0;
                    GAMEOVER = true;
                    gameOverDisplay();
                    //nextPlayer.setGamesWon()
                    //skriva grattis till segern
                    //ny meny(multiplayerGameOverMenu)
                }
            }
        }
        for (int j = 0; j < numberOfChars; j++) {
            string += updatedArray[j];
            hiddenWord = string;
        }
        if(correctGuessedChar){
            System.out.println("Correct character!");
            correctGuessedChar = false;
        }else if(incorrectGuessedChar){
            System.out.println("Incorrect! "+ nextPlayer.getName()+ " lost one healthpoint! ");
            System.out.print("HealthBar: ");
            healthBarDisplay(nextPlayer.getLives());
            System.out.println();
            incorrectGuessedChar = false;
        }
        if (hiddenWord.equals(word)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameRunning = false;
            System.out.println("The correct word was: " + word);
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("CONGRATULATIONS! - Word complete! +1 point for "+ currentPlayer.getName());
            System.out.println("-----------------------------------------------------------------------");
            word = getWord();
        }
        showGame();
    }

    /**
     * Den här metoden skriver ut "spelet" rad för rad. Variabler uppdateras i update och sedan skrivs spelet ut på
     * nytt. Vi kollar om seger eller förlust har uppnåtts och vi uppdaterar även spelarens data.
     */
    public void showGame() {
        gameRunning = true;

        System.out.println("\nHi "+currentPlayer.getName() + "\nI'm thinking about an English word with: " + numberOfChars + " characters");
        System.out.println("So far you have correctly guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + usedChars);
        System.out.print("HealthBar: ");
        healthBarDisplay(currentPlayer.getLives());
        System.out.println();
        System.out.println("Which character do you guess? ");
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

    public void healthBarDisplay(int lives){
        for (int i = 0; i < lives; i++) {
            System.out.print("*");
        }
    }
    public void gameOverDisplay(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameRunning = false;
        System.out.println();
        System.out.println("Game Over!");
        System.out.println("The word was: " + word);
        System.out.println();
        //player1.setGamesPlayed(1);
        //new Menu(player1);
    }
}
