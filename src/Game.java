import java.util.Scanner;

public class Game {

    private Player player = null;
    private String hiddenWord = "";
    private String incorrectChars = "";
    private String hangMeterAsterisk = "";
    private int numberOfChars = 0;
    private Scanner scanner = new Scanner(System.in);

    public Game(Player player){
        this.player = player;
    }
    String theWord = "apple";
    String[] randomWord = {"apple"};
    double random = Math.random()*2;

    public void update(String alpha){
    }
    public void showGame(){
        System.out.println("Hi " + player.getName() + "! Im thinking about a word with: " + numberOfChars + " characters" );
        System.out.println("So far you have guessed: " + hiddenWord);
        System.out.println("You have guessed the following letters: " + incorrectChars);
        System.out.println("Hang-O-meter: " + hangMeterAsterisk);
        System.out.println("Which character do you guess? " );
    }
}
