package user.views;

import game.MineField;
import game.Player;
import packets.PlayerReady;
import user.Client;
import user.network.ClientSocket;
import user.panels.PlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class WaitingRoomView extends JPanel{
    private static WaitingRoomView instance;

    private HashMap<Player, MineField.Difficulty> playerList = new HashMap<>();

    public WaitingRoomView() {
        super(new GridBagLayout());
    }

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
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        playerList.forEach((K,V) -> {
            add(new PlayerPanel(K, V), constraints);
            constraints.gridy++;
        });
        JButton readyButton = new JButton("Prêt");
        add(readyButton,constraints);
        readyButton.addActionListener(actionEvent -> {
            Player player = Client.getInstance().getPlayer();
            setReady(player.getId());
            ClientSocket.getInstance().send(new PlayerReady(player));
        });
    }

    public void addPlayer(Player player, MineField.Difficulty difficulty) {
        playerList.put(player, difficulty);
        redraw();
    }

    public void setReady(Integer playerId) {
        playerList.forEach((player, difficulty) -> {
            if (player.getId() == playerId) {
                player.setReady(true);
            }
        });
        redraw();
        Client.getInstance().revalidate();
    }

    public void removePlayer(Player player) {
        playerList.remove(player);
        redraw();
    }
}
