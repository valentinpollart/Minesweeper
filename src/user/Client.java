package user;

import game.MineField;
import game.Player;
import user.panels.LoginPanel;
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
        Client frame = new Client();
    }

    private Client() {
        configure();
        Client.instance = this;
        this.field = new MineField() ;
        this.player = new Player(JOptionPane.showInputDialog(null, "Input your game name"));
        field.placeMines();
        field.setUnminedTiles();
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

    public static Client getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
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
}
