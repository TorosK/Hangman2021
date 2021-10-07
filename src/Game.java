import java.util.Scanner;

public class Game {

    private Player player = null;
    private String hiddenWord = "";
    private String incorrectChars = "";
    private String hangMeterAsterisk = "";
    public String theWord = "";
    public char[] charArray = new char[20];
    public String[] updatedArray = new String[20];
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
    public void update(String alpha){
        for (int i = 0; i < numberOfChars; i++){
            if(alpha.equals(charArray[i])){
                updatedArray[i] = alpha;
            }else {
                incorrectChars += alpha;
            }
        }
        showGame();
    }
    public void showGame(){
        System.out.println("Hi " + player.getName() + "! Im thinking about a word with: " + numberOfChars + " characters" );
        System.out.println("So far you have guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + incorrectChars);
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
