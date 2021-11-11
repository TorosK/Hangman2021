import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Player {
    private String name = "";
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    final private int FULL_HEALTH = 3;
    private int lives = FULL_HEALTH;
    private boolean playerGameOver = false;


    /**
     * Skapar en spelare med ett namn, gamesplayed och gameswon är satta till 0 by default;
     * @param name
     */
    public Player(String name) {
        this.name = name;
        savePlayer(this);
    }

    /**
     * Den här kontruktorn skapar en spelare när man har hämtat informationen från textfil.
     * @param name
     * @param gamesPlayed
     * @param gamesWon
     */
    public Player(String name, int gamesPlayed, int gamesWon){
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        savePlayer(this);
    }

    public Player(String name, String gamesPlayed, String gamesWon, String lives, String isGameOver){
        this.name = name;
        this.gamesPlayed = Integer.parseInt(gamesPlayed);
        this.gamesWon = Integer.parseInt(gamesWon);
        this.lives = Integer.parseInt(lives);
        if (isGameOver.equals("false")){
            this.playerGameOver = false;
        } else {
            this.playerGameOver = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed += gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon += gamesWon;
    }

    public int getLives() {
        return lives;
    }

    public void setLives() {
        this.lives--;
    }

    public void setResetLives() {
        this.lives = FULL_HEALTH;
    }

    public boolean isPlayerGameOver() {
        return playerGameOver;
    }

    public void setPlayerGameOver(boolean playerGameOver) {
        this.playerGameOver = playerGameOver;
    }

    public void savePlayer (Player player){
        try {
            PrintWriter out = new PrintWriter("HangmanPlayerFile" + player.getName() + ".txt");
            out.println(player.getName());
            out.println(player.gamesPlayed);
            out.println(player.gamesWon);
            out.close();
        } catch (FileNotFoundException exception) {
        }
    }
}
