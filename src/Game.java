import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private Player player = null;
    private Menu menu = null;
    private String hiddenWord = "";
    private String usedChars = "";
    private String hangMeterAsterisk = "";
    public String theWord = "";
    public char[] charArray = new char[20];
    private String[] wordArray194k = new String[194433];
    public String[] updatedArray = new String[30];
    private int numberOfChars = 0;
    private int incorrectGuessCounter = 0;
    private int correctGuessCounter = 0;
    private final int GAMEOVER = 10;
    File wordFile = new File("english3.txt");
    private Scanner scanner = new Scanner(System.in);
    private Scanner scannerWordFile;

    public Game(Player player, Menu menu) {
        this.player = player;
        this.menu = menu;
        String word = getWord();
        showGame();
        boolean flag = true;
        while (flag) {
            if (scanner.hasNext()) {
                String guessedChar = String.valueOf(menu.getAlpha());
                update(guessedChar);
            }
        }
    }

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

    public void showGame() {
        System.out.println("Hi " + player.getName() + "! Im thinking about a word with: " + numberOfChars + " characters");
        System.out.println("So far you have guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + usedChars);
        System.out.println("Hang-O-meter: " + hangMeterAsterisk);
        if (hiddenWord.equals(theWord)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Victory! Congratulations!");
            player.setGamesPlayed(1);
            player.setGamesWon(1);
            new Menu(player);
        }
        if (incorrectGuessCounter != GAMEOVER) {
            System.out.println("Which character do you guess? ");
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            System.out.println("Game Over!");
            player.setGamesPlayed(1);
            new Menu(player);
        }
    }

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
