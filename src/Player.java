import java.io.File;

public class Player {
    private String name = "";
    private int gamesPlayed = 0;
    private int gamesWon = 0;
    private File playerInfo = null;


    public Player(String name) {
        this.name = name;
        playerInfo = new File("hangman"+ name +".txt");
    }
    public Player(String name, int gamesPlayed, int gamesWon){
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        playerInfo = new File("hangman"+ name +".txt");

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
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public File getPlayerInfo() {
        return playerInfo;
    }

    public void setPlayerInfo(File playerInfo) {
        this.playerInfo = playerInfo;
    }
}
