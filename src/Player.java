
public class Player {
    private String name = "";
    private int gamesPlayed = 0;
    private int gamesWon = 0;

    /**
     * Skapar en spelare med ett namn, gamesplayed och gameswon är satta till 0 by default;
     * @param name
     */
    public Player(String name) {
        this.name = name;
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
}
