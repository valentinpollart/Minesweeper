package game;

import java.io.Serializable;

public class Tile implements Serializable {
    public enum Status {UNDISCOVERED, EMPTY, MINED}

    private Status status;
    private int nearbyMines;
    private int sweeperId;

    Tile() {
        this(Status.EMPTY);
    }

    private Tile(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getNearbyMines() {
        return nearbyMines;
    }

    void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }

    public int getSweeperId() {
        return sweeperId;
    }

    public void setSweeperId(int sweeperId) {
        this.sweeperId = sweeperId;
    }

    public boolean isMined() {
        return getStatus() == Status.MINED;
    }
}
