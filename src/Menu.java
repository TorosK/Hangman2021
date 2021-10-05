import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    private int choice = 0;

    public Menu() {

    }

    public Menu(int menuChoice) {

    }

    public void show() {
        System.out.println("Welcome! Please choose menu option:");
        System.out.println("1: Play");
        System.out.println("2: Load Player");
        System.out.println("3: Save Player");
        System.out.println("4: Quit");
        int menuChoice = getInt();
        switch (menuChoice) {
            case 1: {
                //play();
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
