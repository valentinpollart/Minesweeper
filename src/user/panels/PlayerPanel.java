package user.panels;

import game.MineField;
import game.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private final JLabel playerLabel = new JLabel();
    private final JLabel difficultyLabel = new JLabel();
    private final JLabel readyLabel = new JLabel();

    public PlayerPanel(Player player, MineField.Difficulty difficulty) {
        super(new GridBagLayout());
        setPlayerLabel(player);
        setDifficultyLabel(difficulty);
        setReadyLabel(player.getReady());
        redraw();
    }

    public void redraw() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(playerLabel, constraints);

        constraints.gridx++;
        constraints.anchor = GridBagConstraints.CENTER;
        add(readyLabel, constraints);

        constraints.gridx++;
        constraints.anchor = GridBagConstraints.EAST;
        add(difficultyLabel, constraints);
    }

    public void setPlayerLabel(Player player) {
        this.playerLabel.setText(player.getName() + " : ");
    }

    public void setDifficultyLabel(MineField.Difficulty difficulty) {
        this.difficultyLabel.setText(difficulty.toString());
    }

    public void setReadyLabel(boolean ready) {
        readyLabel.setText(ready ? "Prêt" : "Pas prêt");
    }
}
