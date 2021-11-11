/* Grupp High-Voltage
Stefan Johansson, Toros Kutlu, Jonathan Tesfazion och Emily Arnelid
 */
// Stor bokstav skapar problem när spelare skapas och laddas in.
// Ändra FULL_HEALTH till 10.
//Nu fungerar menyerna. Det var paranteser som sknades som Toros föreslog, plus att multiplayerendofgame inte
// var hanterad.
// case 4 ska radera spelare men startar istället spelet som sparades.
// Load bugg: spelare som skapats hittas inte i loadPlayer.
// Vi är och arbetar i loadGame metoden som är i menu classen. Vi måste göra kontruktorer för 2-3 spelare. Rad 416-435.
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
    }
}

