package user.panels;

import game.Player;

import javax.swing.*;
import java.awt.*;

class PlayerScore extends JPanel {
    private final JLabel nameLabel = new JLabel();
    private final JLabel scoreLabel = new JLabel();

    PlayerScore(Player player){
        super(new GridBagLayout());
        setNameLabel(player);
        setScoreLabel(player);
        redraw();
    }

    public void setNameLabel(Player player) {
        this.nameLabel.setText(player.getName());
    }

    public void setScoreLabel(Player player) {
        this.scoreLabel.setText(Integer.toString(player.getScore()));
    }

    void redraw() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(nameLabel, constraints);

        constraints.gridx++;
        constraints.anchor = GridBagConstraints.EAST;
        add(scoreLabel, constraints);
    }
}
