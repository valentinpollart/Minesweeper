package packets;

import game.MineField;

public class ChosenGame extends Packet {

    private final int chosenGame;
    private final MineField.Difficulty difficulty;

    public ChosenGame(int chosenGame, MineField.Difficulty difficulty) {
        this.chosenGame = chosenGame;
        this.difficulty =difficulty;
    }

    public int getChosenGame() {
        return chosenGame;
    }

    public MineField.Difficulty getDifficulty() {
        return difficulty;
    }
}
