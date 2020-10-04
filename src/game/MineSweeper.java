package game;

import java.util.Scanner;

public class MineSweeper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Valentin's game.MineSweeper ! Please input the difficulty of the game (EASY/MEDIUM/HARD/CUSTOM.");
        MineField minefield = new MineField(MineField.Difficulty.valueOf(sc.next()));
        minefield.placeMines();
        System.out.println("Here's the requested grid :");
        System.out.println(minefield.toString());
    }
}
