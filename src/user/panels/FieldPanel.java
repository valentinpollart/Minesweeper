package user.panels;

import game.MineField;
import game.Player;
import game.Tile;
import packets.TileRequest;
import user.Client;
import user.network.ClientSocket;
import user.ui.AssetGetter;
import user.ui.TileButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FieldPanel extends JPanel {
    private static FieldPanel instance;
    private TileButton[][] buttonField;
    private MineField mineField;

    private FieldPanel() {
        super(new GridBagLayout());
        this.mineField = Client.getInstance().getField();
        buttonField = new TileButton[mineField.getLength()][mineField.getHeight()];
        redraw();
    }

    public static FieldPanel getInstance() {
        if (instance == null) {
            instance = new FieldPanel();
        }

        return instance;
    }

    private void setButton(int x, int y, TileButton button) {
        buttonField[x][y] = button;
    }

    public void redraw() {
        removeAll();
        MineField mineField = Client.getInstance().getField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        for (int x = 0; x < mineField.getLength(); x++) {
            constraints.gridx = x;
            for (int y = 0; y < mineField.getHeight(); y++) {
                TileButton button = new TileButton(x, y, mineField.getField()[x][y]);
                setButton(x, y, button);
                constraints.gridy = y;

                add(button, constraints);

                button.addActionListener(this::onTileClick);
            }
        }

        Client.getInstance().setSize(mineField.getLength() * AssetGetter.TILE_SIZE_PX + 150, mineField.getHeight() * AssetGetter.TILE_SIZE_PX + 30);
    }

    private void onTileClick(ActionEvent actionEvent) {
        Player player = Client.getInstance().getPlayer();
        if (player.getHasLost()) {
            TileButton tileButton = (TileButton) actionEvent.getSource();
            Tile tile = tileButton.getTile();
            if (player.getGameId() != null) {
                ClientSocket.getInstance().send(new TileRequest(player, tileButton.getX(), tileButton.getY()));
            } else {
                tileButton.redraw();
            }
            if (tile.getStatus() != Tile.Status.MINED) {
                player.incrementScore();
                ScorePanel.getInstance().setPlayerScore(player);
            } else {
                player.setHasLost(true);
            }
        }
    }
}
