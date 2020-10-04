package user.panels;

import game.MineField;
import game.Player;
import user.Client;
import user.ui.TileButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ScorePanel extends JPanel {
    private static ScorePanel instance;
    private HashMap<Player, Integer> playersScores;

    public ScorePanel(HashMap<Player, Integer> playersScores){
        super(new GridBagLayout());
        instance = this;
        Player player = Client.getInstance().getPlayer();
        this.playersScores = playersScores;
        redraw();
    }

    public static ScorePanel getInstance() {
        return instance;
    }

    public void setPlayerScore(Player player) {
        playersScores.put(player, player.getScore());
        redraw();
    }

    public void redraw() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;

        playersScores.forEach(
            (player, score) -> {
                PlayerScore playerScore = new PlayerScore(player);
                add(playerScore, constraints);
                constraints.gridy += 20;
            }
        );

    }
}
