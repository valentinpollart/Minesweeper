package user.views;

import game.Player;
import user.panels.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class ScoreView extends JPanel {
    private static ScoreView instance;
    private HashMap<Player,Integer> scoreboard;

    public static ScoreView getInstance() {
        if (instance == null) {
            instance = new ScoreView();
        }
        return instance;
    }

    public ScoreView() {
        super(new BorderLayout());
    }

    public void setScoreboard(HashMap<Player, Integer> scoreboard) {
        this.scoreboard = scoreboard;
        redraw();
    }

    public void redraw(){
        removeAll();
        add(ScorePanel.getInstance(), BorderLayout.CENTER);
        JButton gameListButton = new JButton("Retourner à la liste des jeux");
    }

    private void onButtonClick(ActionEvent actionEvent) {

    }
}
