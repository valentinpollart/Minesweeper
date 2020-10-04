package user.panels;

import game.MineField;
import game.Player;

import javax.swing.*;

public class PlayerPanel extends JPanel {
    private Player player;
    private MineField.Difficulty difficulty;

    public PlayerPanel(Player player, MineField.Difficulty difficulty) {
        this.player = player;
        this.difficulty = difficulty;
        redraw();
    }

    public void redraw() {
        removeAll();
    }
}
