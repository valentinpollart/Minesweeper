package game;

import java.util.Random;
import java.util.Scanner;

public class MineField {
    public enum Difficulty {EASY,MEDIUM,HARD,CUSTOM}

    private Tile[][] field;
    private int length;
    private int height;
    private int mineCount;

    public MineField(){
        this(Difficulty.EASY);
    }

    public MineField(Difficulty chosenDifficulty) {
        switch (chosenDifficulty){
            case EASY:
                this.length = 10;
                this.height = 10;
                this.mineCount = 8;
                break;
            case MEDIUM:
                this.length = 20;
                this.height = 20;
                this.mineCount = 40;
                break;
            case HARD:
                this.length = 30;
                this.height = 30;
                this.mineCount = 108;
                break;
            case CUSTOM:
                Scanner sc = new Scanner(System.in);
                System.out.println("Saisissez la longueur du plateau :");
                this.length = sc.nextInt();
                System.out.println("Saisissez la hauteur du plateau :");
                this.height = sc.nextInt();
                System.out.println("Saisissez le nombre de mines sur le plateau :");
                this.mineCount = sc.nextInt();
                break;
        }
        this.field = new Tile[this.length][this.height];
        for(int i = 0; i < this.length; i++) {
            for(int j = 0; j < this.height; j++) {
                setTile(i, j, new Tile());
            }
        }
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getField() {
        return field;
    }

    public Tile getTile(int x, int y) {
        return field[x][y];
    }

    public void setTile(int x, int y, Tile tile){
        field[x][y] = tile;
    }

    public void placeMines() {
        Random rg = new Random();
        int remainingMineCount = mineCount;
        while (remainingMineCount > 0){
            int randLength = rg.nextInt(length);
            int randHeigh = rg.nextInt(height);
            if(getTile(randLength, randHeigh).getStatus() == Tile.Status.MINED){
                remainingMineCount--;
            }
            getTile(randLength, randHeigh).setStatus(Tile.Status.MINED);
        }
    }

    private int countNearbyMines(int xMine, int yMine) {
        int mineCount = 0;

        for(int x = Math.max(xMine - 1, 0); x < Math.min(xMine + 2, length); x++){
            for (int y = Math.max(yMine -1, 0); y < Math.min(yMine + 2, height); y++) {
                if (field[x][y].isMined()){
                    mineCount++;
                }
            }
        }
        return mineCount;
    }

    public void setUnminedTiles() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height; j++) {
                if (!field[i][j].isMined()){
                    field[i][j].setStatus(Tile.Status.EMPTY);
                    field[i][j].setNearbyMines(countNearbyMines(i, j));
                }
            }
        }
    }

    private StringBuilder displayMineField() {
        StringBuilder stringifiedMineField = new StringBuilder();
        for (int i = 0; i<this.length; i++){
            for (int j = 0; j < this.length; j++){
                if (this.field[i][j].isMined()) {
                    stringifiedMineField.append("X");
                } else {
                    stringifiedMineField.append(this.field[i][j].getNearbyMines());
                }
                stringifiedMineField.append(" ");
            }
            stringifiedMineField.append("\n");
        }
        return stringifiedMineField;
    }

    @Override
    public String toString() {
        return this.displayMineField().toString();
    }

    public boolean isGameFinished() {
        int remainingMines = 0;
        for (int i = 0; i<this.length; i++) {
            for (int j = 0; j < this.length; j++) {
                if (field[i][j].getStatus() != Tile.Status.EMPTY) {
                    remainingMines++;
                }
            }
        }
        return remainingMines == mineCount;
    }
}
