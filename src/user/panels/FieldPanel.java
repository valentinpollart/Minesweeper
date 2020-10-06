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

    private void setButton(TileButton button) {
        buttonField[button.getPositionX()][button.getPositionY()] = button;
    }

    public TileButton getButton(int x, int y) {
        return buttonField[x][y];
    }

    public void redraw() {
        removeAll();
        mineField = Client.getInstance().getField();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        for (int x = 0; x < mineField.getLength(); x++) {
            constraints.gridx = x;
            for (int y = 0; y < mineField.getHeight(); y++) {
                TileButton button = new TileButton(x, y, mineField.getField()[x][y]);
                setButton(button);
                constraints.gridy = y;

                add(button, constraints);

                button.addActionListener(this::onTileClick);
            }
        }

        Client.getInstance().setSize(mineField.getLength() * AssetGetter.TILE_SIZE_PX + 150, mineField.getHeight() * AssetGetter.TILE_SIZE_PX + 100);
    }

    private void onTileClick(ActionEvent actionEvent) {
        Player player = Client.getInstance().getPlayer();
        if (!player.getHasLost()) {
            TileButton tileButton = (TileButton) actionEvent.getSource();
            Tile tile = tileButton.getTile();
            if (player.getGameId() != null) {
                TileRequest tileRequest = new TileRequest(player, tileButton.getPositionX(), tileButton.getPositionY());
                ClientSocket.getInstance().send(tileRequest);
            } else {
                tileButton.redraw();
                if (tile.getStatus() != Tile.Status.MINED) {
                    player.incrementScore();
                    ScorePanel.getInstance().setPlayerScore(player);
                    ScorePanel.getInstance().revalidate();
                } else {
                    player.setHasLost(true);

                }
            }
        }
    }
}
