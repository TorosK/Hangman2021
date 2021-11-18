import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Player {
    private String name = "";

    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private int totalPoints = 0;
    private int maxPoints = 0;
    private double averagePoints = 0;

    private int currentGamePoints = 0;

    final private int FULL_HEALTH = 10;
    private int lives = FULL_HEALTH;
    private boolean playerGameOver = false;

    private boolean currentGameWinner = false;


    /**
     * Skapar en spelare med ett namn, gamesplayed och gameswon är satta till 0 by default;
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        savePlayer(this);
    }

    /**
     * Den här kontruktorn skapar en spelare när man har hämtat informationen från textfil.
     *
     * @param name
     * @param gamesPlayed
     * @param gamesWon
     */
    public Player(String name, int gamesPlayed, int gamesWon, int totalPoints, int maxPoints, String averagePoints) {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.totalPoints = totalPoints;
        this.maxPoints = maxPoints;
        this.averagePoints = Double.parseDouble(averagePoints);
        savePlayer(this);
    }

    //LOAD game
    public Player(String name, String gamesPlayed, String gamesWon, String lives, String isGameOver, String currentGamePoints) {
        this.name = name;
        this.gamesPlayed = Integer.parseInt(gamesPlayed);
        this.gamesWon = Integer.parseInt(gamesWon);
        this.lives = Integer.parseInt(lives);
        this.currentGamePoints = Integer.parseInt(currentGamePoints);
        if (isGameOver.equals("false")) {
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

    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public double getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(double averagePoints) {
        this.averagePoints = averagePoints;
    }

    public int getCurrentGamePoints() {
        return currentGamePoints;
    }

    public void setCurrentGamePoints() {
        this.currentGamePoints = currentGamePoints + 1;
    }

    public void resetCurrentGamePoints() {
        this.currentGamePoints = 0;
    }

    public void updatePlayerData() {
        gamesPlayed++;
        if (this.currentGameWinner) {
            gamesWon++;
        }
        if (currentGamePoints > maxPoints) {
            maxPoints = currentGamePoints;
        }
        totalPoints += currentGamePoints;
        averagePoints = (double) totalPoints / (double) gamesPlayed;
        averagePoints = Math.floor(averagePoints * 100) / 100;
    }

    public boolean isPlayerGameOver() {
        return playerGameOver;
    }

    public void setPlayerGameOver(boolean playerGameOver) {
        this.playerGameOver = playerGameOver;
    }

    public void resetCurrentGameWinner() {
        currentGameWinner = false;
    }

    public void setCurrentGameWinner() {
        this.currentGameWinner = true;
    }

    public boolean getCurrentGameWinner() {
        return this.currentGameWinner;
    }

    public void savePlayer(Player player) {
        try {
            PrintWriter out = new PrintWriter("HangmanPlayerFile" + player.getName() + ".txt");
            out.println(player.getName());
            out.println(player.gamesPlayed);
            out.println(player.gamesWon);
            out.println(player.totalPoints);
            out.println(player.maxPoints);
            out.println(player.averagePoints);
            out.close();
        } catch (FileNotFoundException exception) {
        }
    }
}
