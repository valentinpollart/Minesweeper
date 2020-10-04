package game;

public class Player {
    private Integer id;
    private int score;
    private String name;
    private Integer gameId;
    private boolean ready = false;
    private boolean hasLost = false;

    public Player(String name) {
        this.id = null;
        this.score = 0;
        this.name = name;
        this.gameId = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public String getName() {
        return name;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public boolean getReady() {
        return this.ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean getHasLost() {
        return this.hasLost;
    }

    public void setHasLost(boolean hasLost) {
        this.hasLost = hasLost;
    }
}
