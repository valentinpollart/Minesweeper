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

public class GamePanel extends JPanel {
    private HashMap<Player, MineField.Difficulty> players;
    private int gameId;

    public GamePanel(int gameId, HashMap<Player, MineField.Difficulty> players) {
        super(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        this.players = players;
        this.gameId = gameId;
        setBorder(null);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(new JLabel(("Game " + gameId)), constraints);
        constraints.gridx++;
        add(new JLabel(("Players : " + players.size())), constraints);
        constraints.gridx++;
        JButton joinButton = new JButton("Join");
        add(joinButton,constraints);
        joinButton.addActionListener(this::onButtonClick);
    }

    private void onButtonClick(ActionEvent actionEvent) {
        MineField.Difficulty difficulty = MineField.Difficulty.values()[JOptionPane.showOptionDialog(
                null,
                "Sélectionnez la difficulté que vous désirez",
                "Difficulté",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                MineField.Difficulty.values(),
                MineField.Difficulty.values()[0]
        )];
        ClientSocket.getInstance().send(new ChosenGame(gameId, difficulty));
        Client.getInstance().getPlayer().setGameId(gameId);
    }
}
