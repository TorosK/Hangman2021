import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HighScore {
    String numberOne = "Empty";
    String numberTwo = "Empty";
    String numberThree = "Empty";
    String numberFour = "Empty";
    String numberFive = "Empty";
    int one = 0;
    int two = 0;
    int three = 0;
    int four = 0;
    int five = 0;

    public void updateHighScore(String name, int score){
        if(score > five ){
            if(score <= four){
               numberFive = name;
               five = score;
            }
            if(score > four && score <= three){
                numberFive = numberFour;
                five = four;
                numberFour = name;
                four = score;
            }
            if(score > three && score <= two){
                numberFive = numberFour;
                five = four;
                numberFour = numberThree;
                four = three;
                numberThree = name;
                three = score;
            }
            if(score > two && score <= one){
                numberFive = numberFour;
                five = four;
                numberFour = numberThree;
                four = three;
                numberThree = numberTwo;
                three = two;
                numberTwo = name;
                two = score;
            }
            if(score > one){
                numberFive = numberFour;
                five = four;
                numberFour = numberThree;
                four = three;
                numberThree = numberTwo;
                three = two;
                numberTwo = numberOne;
                two = one;
                numberOne = name;
                one = score;
            }
            saveHighScoreList();
        }
    }

    public void loadHighScoreList(){
        try {
            File file = new File("HangmanHighScore.txt");
            Scanner scannerFile = new Scanner(file);
            String highScoreList = scannerFile.nextLine();
            Scanner scanner = new Scanner(highScoreList);
            numberOne = scanner.next();
            one = Integer.parseInt(scanner.next());
            numberTwo = scanner.next();
            two = Integer.parseInt(scanner.next());
            numberThree = scanner.next();
            three = Integer.parseInt(scanner.next());
            numberFour = scanner.next();
            four = Integer.parseInt(scanner.next());
            numberFive = scanner.next();
            five = Integer.parseInt(scanner.next());
        }
        catch (FileNotFoundException e){
            saveHighScoreList();
        }
    }
    public void saveHighScoreList(){
        try {
            PrintWriter out = new PrintWriter("HangmanHighScore.txt");
            String temp = numberOne + " " + one + " " + numberTwo + " " + two + " " + numberThree + " " + three + " "
                    + numberFour + " " + four + " " + numberFive + " " + five;
            out.println(temp);
            out.close();
        }
        catch (FileNotFoundException ex){

        }
    }
    public String showHighScoreList(){
        String temp = "-=HIGHSCORELIST=-\n"+"1. "+ numberOne + " | Score: " + one +"\n"+"2. "+ numberTwo + " | Score: " + two +"\n"+"3. "+ numberThree + " | Score: " + three +"\n"
                +"4. "+ numberFour + " | Score: " + four +"\n"+"5. "+ numberFive + " | Score: " + five+"\n";
        return temp;
    }
}
