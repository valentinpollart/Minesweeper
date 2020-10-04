package user.network.handlers;

import game.MineField;
import game.Tile;
import packets.TileReveal;
import user.Client;
import user.panels.FieldPanel;
import user.panels.ScorePanel;

public class TileRevealHandler {
    private static TileRevealHandler instance;

    public static TileRevealHandler getInstance() {
        if (instance == null) {
            instance = new TileRevealHandler();
        }
        return instance;
    }

    public void handle(TileReveal packet) {
        Tile tile = packet.getTile();
        MineField field = Client.getInstance().getField();
        field.setTile(packet.getX(), packet.getX(), packet.getTile());
        FieldPanel.getInstance().redraw();
        ScorePanel.getInstance().setPlayerScore(packet.getSweeper());
    }
}
