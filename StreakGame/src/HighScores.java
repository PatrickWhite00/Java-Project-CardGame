public class HighScores
{
    private int score;
    private String playerName;

    public HighScores(int score, String playerName) {
        this.score = score;
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return playerName+" "+score;
    }

}
