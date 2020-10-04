package user.views;

import game.Player;
import user.panels.ScorePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class ScoreboardView extends JPanel {
    private static ScoreboardView instance;
    private HashMap<Player,Integer> scoreboard;

    public static ScoreboardView getInstance() {
        return instance;
    }

    public ScoreboardView(HashMap<Player,Integer> scoreboard) {
        super(new BorderLayout());
        instance = this;
        this.scoreboard = scoreboard;
        redraw();
    }

    public void redraw(){
        removeAll();
        add(new ScorePanel(this.scoreboard), BorderLayout.CENTER);
        JButton gameListButton = new JButton("Retourner à la liste des jeux");
    }

    private void onButtonClick(ActionEvent actionEvent) {

    }
}
