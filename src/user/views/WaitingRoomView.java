package user.views;

import game.MineField;
import game.Player;
import user.panels.PlayerPanel;

import java.util.HashMap;

import javax.swing.*;
import java.awt.*;


public class WaitingRoomView extends JPanel{
    private static WaitingRoomView instance;

    private HashMap<Player, MineField.Difficulty> playerList;

    public static WaitingRoomView getInstance() {
        if (instance == null) {
            instance = new WaitingRoomView();
        }
        return instance;
    }

    public void redraw(HashMap<Player, MineField.Difficulty> playerList) {
        this.playerList = playerList;
        playerList.forEach((K,V) -> add(new PlayerPanel(K,V),BorderLayout.CENTER));
    }

    public void addPlayer(Player player, MineField.Difficulty difficulty) {
        this.playerList.put(player, difficulty);
        redraw(playerList);
    }

    public void removePlayer(Player player) {
        this.playerList.remove(player);
        redraw(playerList);
    }
}
