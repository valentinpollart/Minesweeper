package user.ui;

import game.MineField;
import packets.PlayerLeft;
import user.Client;
import user.network.ClientSocket;
import user.panels.FieldPanel;
import user.panels.LoginPanel;
import user.panels.ScorePanel;
import user.views.GameListView;
import user.views.GameView;

import javax.swing.*;

public class GameMenu extends JMenuBar {
    private static GameMenu instance;

    public static GameMenu getInstance() {
        if (instance == null) {
            instance = new GameMenu();
        }
        return instance;
    }

    public GameMenu() {
        JMenu menu = new JMenu("Jeu");

        JMenuItem loginMenu = new JMenuItem("Rejoindre un salon en ligne");
        menu.add(loginMenu);

        JMenuItem singleGameMenu = new JMenuItem("Nouvelle partie");
        menu.add(singleGameMenu);

        JMenuItem gameListMenu = new JMenuItem("Rejoindre la liste des parties en ligne");
        menu.add(gameListMenu);

        JMenuItem logoutMenu = new JMenuItem("Quitter le salon en ligne");
        menu.add(logoutMenu);

        add(menu);

        Client client = Client.getInstance();

        loginMenu.addActionListener(actionEvent -> {
            client.setContentPane(LoginPanel.getInstance());
            client.revalidate();
        });

        singleGameMenu.addActionListener(actionEvent -> {
            client.newGame();
            client.getPlayer().reset();
            FieldPanel fieldPanel = FieldPanel.getInstance();
            fieldPanel.redraw();
            ScorePanel.getInstance().redraw();
            client.setContentPane(GameView.getInstance());
        });

        gameListMenu.addActionListener(actionEvent -> {
            client.setContentPane(GameListView.getInstance());
            client.revalidate();
        });

        logoutMenu.addActionListener(actionEvent -> {
            ClientSocket.getInstance().send(new PlayerLeft(client.getPlayer()));
        });
    }


}
