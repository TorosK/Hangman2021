import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private int choice = 0;


    public Menu() {
    String[] startmeny = {"Welcome! Please choose menu option: ", "1: Play", "2: Load Player", "3: Save Player", "4: Quit"};
    show(startmeny);
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
                case 1: {
                    //new Game();
                    break;
                }
                case 2: {
                    //loadPlayer();
                    break;
                }
                case 3: {
                    //savePlayer();
                    break;
                }
                case 4: {
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
}
