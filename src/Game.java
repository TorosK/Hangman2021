import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private Player player = null;
    private Menu menu = null;
    private String hiddenWord = "";
    private String usedChars = "";
    private String hangMeterAsterisk = "";
    private String theWord = "";
    private char[] charArray = new char[20];
    private String[] updatedArray = new String[30];
    private String[] wordArray194k = new String[194433];
    private int numberOfChars = 0;
    private int incorrectGuessCounter = 0;
    private int correctGuessCounter = 0;
    private final int GAMEOVER = 10;
    private File wordFile = new File("english3.txt");
    private Scanner scannerWordFile;
    private boolean flag = true;

    /**
     * Konstruktorn tar emot en spelare och meny. Anropar getWord() för att få ett ord att spela med. Anropar showGame()
     * för att skriva ut spelet. While loopar spelarens förslag, anropar getAlpha() för att kontrollera inmatning och
     * sedan update() för att uppdatera spelet med den gissade bokstaven.
     * @param player
     * @param menu
     */
    public Game(Player player, Menu menu) {
        this.player = player;
        this.menu = menu;
        String word = getWord();
        showGame();
        flag = true;
        while (flag) {
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
            }
        }
        if (correctGuessCounter == 0) {
            incorrectGuessCounter++;
            hangMeterAsterisk += "*";
            if (incorrectGuessCounter == GAMEOVER) {
                showGame();
            }
        }
        for (int j = 0; j < numberOfChars; j++) {
            string += updatedArray[j];
            hiddenWord = string;
        }
        showGame();
    }
    /**
     * Den här metoden skriver ut "spelet" rad för rad. Variabler uppdateras i update och sedan skrivs spelet ut på
     * nytt. Vi kollar om seger eller förlust har uppnåtts och vi uppdaterar även spelarens data.
     */
    public void showGame() {
        System.out.println("Hi " + player.getName() + "! I'm thinking about an English word with: " + numberOfChars + " characters");
        System.out.println("So far you have correctly guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + usedChars);
        System.out.println("Hang-O-meter: " + hangMeterAsterisk);
        if (hiddenWord.equals(theWord)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            System.out.println();
            System.out.println("Victory! Congratulations!");
            System.out.println();
            player.setGamesPlayed(1);
            player.setGamesWon(1);
            new Menu(player);
        }
        if (incorrectGuessCounter != GAMEOVER) {
            System.out.println("Which character do you guess? ");
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
            System.out.println();
            System.out.println("Game Over!");
            System.out.println("The word was: " + theWord);
            System.out.println();
            player.setGamesPlayed(1);
            new Menu(player);
        }
    }
    /**
     * Den här metoden slumpar fram ett ord ur en ordlista som är 194433 ord lång. Vi kopplar scannern till filen och
     * fyller vår array wordArray194k med hjälp av en for-loop. Vi slumpar fram ett tal mellan 0 och 194433 som vi
     * använder som index för att välja ett ord i arrayen. Vi stoppar in det framslumpade ordet i en charArray och
     * räknar antalet tecken som vi sparar i variabeln numberOfChars.
     * Vi fyller även variabeln hiddenword med det antal understreck som motsvarar ordets längd och vi fyller även vår
     * hjälp-array updatedArray med rätt antal understreck.
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
        for (int i = 0; i < numberOfChars; i++) {
            hiddenWord += "_";
            updatedArray[i] = "_";
        }
        return theWord;
    }
}
