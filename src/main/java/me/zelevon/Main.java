package me.zelevon;

import me.zelevon.objects.Grid;

public class Main {

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 4, 0, 9, 0},
                {8, 0, 2, 9, 7, 0, 0, 0, 0},
                {9, 0, 1, 2, 0, 0, 3, 0, 0},
                {0, 0, 0, 0, 4, 9, 1, 5, 7},
                {0, 1, 3, 0, 5, 0, 9, 2, 0},
                {5, 7, 9, 1, 2, 0, 0, 0, 0},
                {0, 0, 7, 0, 0, 2, 6, 0, 3},
                {0, 0, 0, 0, 3, 8, 2, 0, 5},
                {0, 2, 0, 5, 0, 0, 0, 0, 0}
        };
        Grid grid = new Grid(board);

        grid.printBoard();
    }
}
