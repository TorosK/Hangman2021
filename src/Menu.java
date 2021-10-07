import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private int choice = 0;

    File hangMan2021 = new File("hangman2021.txt");
   // int [][] playerData = new int[4][2];
    //String[] playerNames = new String[4];//

    Player currentPlayer = null;
    String[] currentMenu = null;

    public Menu() {
    String[] startMenu = {"Welcome! Please choose menu option: ", "1: Play", "2: Load Player", "3: Save Player", "4: Quit"};
    currentMenu = startMenu;
    show(startMenu);
    }

    public Menu(int menuChoice) {

    }
    public void show(String[] argument) {
        String[] meny = argument;
        for(int i=0; i < 5; i++){
            System.out.println(meny[i]);
        }
        //Behöver hantera större intar än 4
        int menuChoice = getInt();
            switch (menuChoice) {
                case 1: { //new Game();
                    if(currentPlayer == null){
                        System.out.println("Please give your player a name: ");
                        String playerName = getString();
                        Player player = new Player(playerName);
                        currentPlayer = player;
                        System.out.println(currentPlayer.getName());
                        show(currentMenu);
                    }

                    break;
                }
                case 2: { //loadPlayer();
                    try{
                        System.out.println("Input name of player: ");
                        String name = getString();
                        File file = new File("hangman"+ name + ".txt");
                        Scanner scannerFile = new Scanner(file);
                    //Scanner scannerFile = new Scanner(hangMan2021);
                        while(scannerFile.hasNext()) {
                            System.out.println("Hello");
                            if(name.equals(scannerFile.next())){                         //Error
                                System.out.println("Hello2");
                                currentPlayer = new Player(name, scannerFile.nextInt(), scannerFile.nextInt());
                                System.out.println(currentPlayer.getName());
                                System.out.println(currentPlayer.getGamesPlayed());
                                System.out.println(currentPlayer.getGamesWon());
                                show(currentMenu);
                            }
                        }
                        scannerFile.close();
                    }
                    catch (FileNotFoundException e){}
                    break;
                }
                case 3: { //savePlayer();
                    try{
                       PrintWriter out = new PrintWriter("hangman"+ currentPlayer.getName() +".txt");
                        //PrintWriter out = new PrintWriter(hangMan2021);

                        out.println(currentPlayer.getName());
                        out.println(currentPlayer.getGamesPlayed());
                        out.println(currentPlayer.getGamesWon());
                        out.close();
                        show(currentMenu);
                    }
                    catch(FileNotFoundException exception){
                    }

                    break;
                }
                case 4: {

                    System.exit(0);
                    //quit();
                    break;
                }
            }
        }
    /*

        choice = scanner.nextInt();
        switch (
        int choice)

        case 1:
            )
*/
    public int getInt() {
        int input = 0;
        boolean loop = true;
        while (loop) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                loop = false;
                scanner.nextLine();
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return input;
    }
    public String getString(){
        String input = "";
        boolean loop = true;
        while (loop) {
            if (scanner.hasNext()) {
                input = scanner.next();
                loop = false;
                scanner.nextLine();
            } else {
                System.out.println("Incorrect input. Try again");
                scanner.nextLine();
            }
        }
        return input;
    }
}
