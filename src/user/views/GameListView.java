package user.views;

import game.MineField;
import game.Player;
import user.panels.GamePanel;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

public class GameListView extends JPanel{
    private static GameListView instance;
    private int gameId;

    public static GameListView getInstance() {
        if (instance == null) {
            instance = new GameListView();
        }
        return instance;
    }

    public void redraw(Vector<HashMap<Player, MineField.Difficulty>> gamesPlayers) {
        this.gameId = 0;
        gamesPlayers.forEach((game) -> {
            add(new GamePanel(this.gameId,game));
            this.gameId++;
        });
    }
}
