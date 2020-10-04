package packets;

import game.Player;
import game.Tile;

public class TileReveal extends Packet {
    private Player sweeper;
    private int x;
    private int y;
    private Tile tile;

   public TileReveal(Player sweeper, int x, int y, Tile tile) {
       this.sweeper = sweeper;
       this.x = x;
       this.y = y;
       this.tile = tile;
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

    public Tile getTile() {
        return tile;
    }
}
