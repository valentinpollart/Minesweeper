package user.network.handlers;

import packets.GameReady;
import user.Client;
import user.panels.ScorePanel;
import user.views.GameView;

public class GameReadyHandler {
    private static GameReadyHandler instance;

    public static GameReadyHandler getInstance() {
        if (instance == null) {
            instance = new GameReadyHandler();
        }
        return instance;
    }

    public void handle(GameReady packet) {
        Client.getInstance().newGame(packet.getDifficulty());
        ScorePanel.getInstance().setPlayersScores(packet.getPlayerList());
        Client.getInstance().setContentPane(GameView.getInstance());
        Client.getInstance().revalidate();

    }
}
