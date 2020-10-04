package packets;

import game.Player;

public class TileRequest extends Packet {
    private Player sweeper;
    private int x;
    private int y;

    public TileRequest(Player sweeper, int x, int y) {
        this.sweeper = sweeper;
        this.x = x;
        this.y = y;
    }

    public Player getSweeper() {
        return sweeper;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
