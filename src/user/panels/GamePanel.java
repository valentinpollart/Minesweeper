package user.panels;

import game.MineField;
import game.Player;
import packets.ChosenGame;
import user.Client;
import user.network.ClientSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class GamePanel extends JButton {
    private HashMap<Player, MineField.Difficulty> players;
    private int gameId;

    public GamePanel(int gameId, HashMap<Player, MineField.Difficulty> players) {
        super();
        this.players = players;
        this.gameId = gameId;
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setMargin(new Insets(0, 0, 0, 0));
        add(new JLabel(("Game" + gameId)), BorderLayout.WEST);
        add(new JLabel(("Players" + players.size())), BorderLayout.CENTER);
        JButton joinButton = new JButton("Join");
        add(joinButton,BorderLayout.EAST);
        joinButton.addActionListener(this::onButtonClick);
    }

    private void onButtonClick(ActionEvent actionEvent) {
        ClientSocket.getInstance().send(new ChosenGame(gameId));
        Client.getInstance().getPlayer().setGameId(gameId);
    }
}
