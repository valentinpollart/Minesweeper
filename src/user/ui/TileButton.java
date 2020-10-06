package user.ui;

import game.Tile;

import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private final int positionX;
    private final int positionY;

    private Tile tile;

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public TileButton(int positionX, int positionY, Tile tile) {
        super();
        this.positionX = positionX;
        this.positionY = positionY;
        this.tile = tile;
        setIcon(AssetGetter.loadImage("/undiscovered.png"));
        setFocusable(true);
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

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
