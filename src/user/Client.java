package user;

import game.MineField;
import game.Player;
import user.panels.LoginPanel;
import user.ui.GameMenu;
import user.views.GameListView;
import user.views.GameView;
import user.views.ScoreView;
import user.views.WaitingRoomView;

import javax.swing.*;

public class Client extends JFrame {
    private static Client instance;
    private MineField field;
    private Player player;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Client frame = new Client();
    }

    private Client() {
        super("MineSweeper");
        configure();
        Client.instance = this;
        this.player = new Player(JOptionPane.showInputDialog(null, "Input your game name"));
        newGame();
        setJMenuBar(GameMenu.getInstance());
        setContentPane(GameView.getInstance());
        setVisible(true);

    }

    private void configure() {
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MineField getField() {
        return this.field;
    }

    public void setField(MineField field) {
        this.field = field;
    }

    public static synchronized Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void setView(JPanel view) {
        if (view instanceof GameListView) {
            GameListView.getInstance().redraw();
        } else if (view instanceof GameView) {
            GameView.getInstance().redraw();
        } else if (view instanceof WaitingRoomView) {
            WaitingRoomView.getInstance().redraw();
        } else if (view instanceof ScoreView) {
            ScoreView.getInstance().redraw();
        } else if (view instanceof LoginPanel) {
            LoginPanel.getInstance().redraw();
        }
        setContentPane(view);
        revalidate();
    }

    public void newGame(MineField.Difficulty difficulty) {
        field = new MineField(difficulty) ;
        field.placeMines();
        field.setUnminedTiles();
    }

    public void newGame() {
        newGame(MineField.Difficulty.EASY);
    }
}
