package user.network.handlers;

import game.MineField;
import game.Tile;
import packets.TileReveal;
import user.Client;
import user.panels.FieldPanel;
import user.panels.ScorePanel;
import user.ui.TileButton;
import user.views.GameView;

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
        tile.setStatus(tile.isMined() ? Tile.Status.MINED : Tile.Status.EMPTY);
        MineField field = Client.getInstance().getField();
        TileButton tileButton = FieldPanel.getInstance().getButton(packet.getX(), packet.getY());
        tileButton.setTile(tile);
        tileButton.redraw();
        tileButton.setFocusable(false);
        if(tile.isMined()) {
            if(Client.getInstance().getPlayer().getId() == packet.getSweeper().getId()) {
                Client.getInstance().getPlayer().setHasLost(true);
            }
        } else {
            ScorePanel.getInstance().setPlayerScore(packet.getSweeper());
        }
    }
}
