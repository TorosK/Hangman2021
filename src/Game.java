import java.util.Scanner;

public class Game {

    public boolean sol = false;
    private Player player = null;
    private String hiddenWord = "";
    private String usedChars = "";
    private String hangMeterAsterisk = "";
    public String theWord = "";
    public char[] charArray = new char[20];
    public String[] updatedArray = new String[20];    // ändra till charArray
    private int numberOfChars = 0;
    private Scanner scanner = new Scanner(System.in);

    public Game(Player player){
        this.player = player;
        String word = getWord();
        showGame();
        boolean flag = true;
        while (flag){
            if(scanner.hasNext()) {
                String guessedChar = scanner.next();
                update(guessedChar);
            }
        }
    }
    public void update(String alpha) {
        usedChars += alpha;
        for (int i = 0; i < numberOfChars; i++) {
            if (alpha.equals(charArray[i])) {
                updatedArray[i] = alpha;
                for (int j = 0; j < numberOfChars; j++){
                    String string = "";
                    string += updatedArray[j];
                    hiddenWord = string;
                }
                //hiddenWord = hiddenWord.replace(x[i], y[j]);
            }
        }
        showGame();
    }
    public void showGame(){
        System.out.println("Hi " + player.getName() + "! Im thinking about a word with: " + numberOfChars + " characters" );
        System.out.println("So far you have guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + usedChars);
        System.out.println("Hang-O-meter: " + hangMeterAsterisk);
        System.out.println("Which character do you guess? " );
    }
    public String getWord(){
        theWord = "apple";
        charArray = theWord.toCharArray();
        numberOfChars = charArray.length;
        for(int i = 0; i < numberOfChars; i++){
            hiddenWord += "_";
            updatedArray[i] += "_";
        }
        //System.out.println(charArray[0]);
        return theWord;
    }
}
