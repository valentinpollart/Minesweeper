package user.panels;

import game.Player;

import javax.swing.*;
import java.awt.*;

class PlayerScore extends JPanel {
    private JLabel nameLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();

    private PlayerScore(){
        redraw();
    }

    PlayerScore(Player player){
        this();
        setNameLabel(player);
        setScoreLabel(player);
    }

    public void setNameLabel(Player player) {
        this.nameLabel.setText(player.getName());
    }

    public void setScoreLabel(Player player) {
        this.scoreLabel.setText(Integer.toString(player.getScore()));
    }

    void redraw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        add(nameLabel, constraints);

        constraints.gridy++;
        add(scoreLabel, constraints);
    }
}
