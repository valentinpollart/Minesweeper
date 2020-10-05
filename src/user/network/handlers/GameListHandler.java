package user.network.handlers;

import packets.GameList;
import user.Client;
import user.views.GameListView;

public class GameListHandler {
    private static GameListHandler instance;

    public static GameListHandler getInstance() {
        if (instance == null) {
            instance = new GameListHandler();
        }
        return instance;
    }

    public void handle(GameList packet) {
        GameListView.getInstance().setGamesPlayers(packet.getGameList());
        Client.getInstance().setContentPane(GameListView.getInstance());
        Client.getInstance().revalidate();
    }
}
