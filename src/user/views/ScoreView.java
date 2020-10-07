package user.views;

import game.Player;
import packets.PlayerLogin;
import user.network.ClientSocket;
import user.panels.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class ScoreView extends JPanel {
    private static ScoreView instance;

    public static ScoreView getInstance() {
        if (instance == null) {
            instance = new ScoreView();
        }
        return instance;
    }

    public ScoreView() {
        super(new GridBagLayout());
    }

    public void setScoreboard(HashMap<Player, Integer> scoreboard) {
        redraw();
    }

    public void redraw(){
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;

        add(ScorePanel.getInstance(), constraints);

        JButton gameListButton = new JButton("Retourner à la liste des jeux");
        constraints.gridx = GridBagConstraints.RELATIVE;
        add(gameListButton, constraints);
        gameListButton.addActionListener(this::onButtonClick);
    }

    private void onButtonClick(ActionEvent actionEvent) {
        ClientSocket.getInstance().send(new PlayerLogin(null));
    }
}
