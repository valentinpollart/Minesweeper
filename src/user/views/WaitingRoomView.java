package user.views;

import game.MineField;
import game.Player;
import user.panels.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class WaitingRoomView extends JPanel{
    private static WaitingRoomView instance;

    private HashMap<Player, MineField.Difficulty> playerList = new HashMap<>();

    public static WaitingRoomView getInstance() {
        if (instance == null) {
            instance = new WaitingRoomView();
        }
        return instance;
    }

    public void setPlayerList(HashMap<Player, MineField.Difficulty> playerList) {
        this.playerList = playerList;
        redraw();
    }

    public void redraw() {
        removeAll();
        playerList.forEach((K,V) -> add(new PlayerPanel(K,V),BorderLayout.CENTER));
    }

    public void addPlayer(Player player, MineField.Difficulty difficulty) {
        this.playerList.put(player, difficulty);
        redraw();
    }

    public void removePlayer(Player player) {
        this.playerList.remove(player);
        redraw();
    }
}
