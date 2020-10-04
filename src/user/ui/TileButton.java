package user.ui;

import game.Tile;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private final int x;
    private final int y;

    private Tile tile;

    public Tile getTile() {
        return tile;
    }

    public TileButton(int x, int y, Tile tile) {
        super();
        this.x = x;
        this.y = y;
        this.tile = tile;
        setIcon(AssetGetter.loadImage("/undiscovered.png"));
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setMargin(new Insets(0, 0, 0, 0));


    }

    public void redraw() {
        setIcon(AssetGetter.loadImage(getImageName()));
        setFocusable(false);
    }

    private String getImageName() {
        switch(tile.getStatus()){
            case MINED:
                return "mined.png";
            case EMPTY:
                return tile.getNearbyMines() + ".png";
            case UNDISCOVERED:
                return "undiscovered.png";
            default:
                throw new IllegalStateException("Asset loader cannot load image for  status" + tile.getStatus());
        }
    }
}
