package packets;

import game.MineField;

public class ChosenGame extends Packet {

    private int chosenGame;

    public ChosenGame(int chosenGame) {
        this.chosenGame = chosenGame;
    }

    public int getChosenGame() {
        return chosenGame;
    }
}
