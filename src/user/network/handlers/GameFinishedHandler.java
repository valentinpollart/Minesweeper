package user.network.handlers;

import packets.GameFinished;
import user.views.ScoreView;

public class GameFinishedHandler {
    private static GameFinishedHandler instance;

    public static GameFinishedHandler getInstance() {
        if (instance == null) {
            instance = new GameFinishedHandler();
        }
        return instance;
    }

    public void handle(GameFinished packet) {
        ScoreView.getInstance().setScoreboard(packet.getScoreboard());
        ScoreView.getInstance().redraw();
    }
}
