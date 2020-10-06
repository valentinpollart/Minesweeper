package user.views;

import game.MineField;
import game.Player;
import packets.PlayerCreateGame;
import server.ServerSocket;
import user.Client;
import user.network.ClientSocket;
import user.panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Vector;

public class GameListView extends JPanel {
    private static GameListView instance;
    private HashMap<Integer, HashMap<Player, MineField.Difficulty>> gamesPlayers;
    private int gameId;

    public static GameListView getInstance() {
        if (instance == null) {
            instance = new GameListView();
        }
        return instance;
    }

    private GameListView() {
        super(new GridBagLayout());
    }

    public void setGamesPlayers(HashMap<Integer, HashMap<Player, MineField.Difficulty>> gamesPlayers) {
        this.gamesPlayers = gamesPlayers;
        redraw();
    }

    public void redraw() {
        removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        JButton newGameButton = new JButton("Créer une nouvelle partie");
        add(newGameButton, constraints);

        newGameButton.addActionListener(actionEvent -> {
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
            ClientSocket.getInstance().send(new PlayerCreateGame(Client.getInstance().getPlayer(), difficulty));
            WaitingRoomView.getInstance().addPlayer(Client.getInstance().getPlayer(), difficulty);
            Client.getInstance().setContentPane(WaitingRoomView.getInstance());
            Client.getInstance().revalidate();
        });

        gamesPlayers.forEach((gameId,game) -> {
            constraints.gridy--;
            add(new GamePanel(gameId,game), constraints);
        });

    }
}
