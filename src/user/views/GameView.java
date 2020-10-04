package user.views;

import user.panels.FieldPanel;
import user.panels.ScorePanel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private static GameView instance;

    private GameView() {
        super(new BorderLayout());
        redraw();
    }

    public static synchronized GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
        }
        return instance;
    }

    public void redraw() {
        removeAll();
        add(FieldPanel.getInstance(), BorderLayout.CENTER);
        add(ScorePanel.getInstance(), BorderLayout.EAST);
    }
}
