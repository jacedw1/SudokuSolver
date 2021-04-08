package me.zelevon.objects;

import me.zelevon.Main;

public class Grid {

    private final int[][] defaultBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private int[][] grid2dArr;

    //default constructor randomizes the board
    public Grid() {
        randomizeBoard();
    }

    //checks the given matrix to check if it is square. if so, sets the board to the given matrix. else, sets the board to default
    public Grid(int[][] grid2dArr){
        this.grid2dArr = grid2dArr.length == grid2dArr[0].length ? grid2dArr : defaultBoard;
    }

    //solves the board and returns true if solvable.
    public boolean solve(){
        int[][] copy = copyBoard();

        int[] next = findNextUnfilled();
        if(next[0] == -1){
            return true;
        }
        for(int i = 1; i  < 10; i++){
            if(isValid(i, next[0], next[1])){
                grid2dArr[next[0]][next[1]] = i;
                if(solve()){
                    return true;
                }
                grid2dArr[next[0]][next[1]] = 0;
            }
        }

        this.grid2dArr = copy;
        return false;
    }

    //uses solve() to determine if board is solvable. does not affect the state of the array
    public boolean isSolvable(){
        Grid copy = new Grid(copyBoard());

        if(copy.solve()){
            return true;
        }
        return false;
    }

    //checks if a number is valid in the given position
    public boolean isValid(int num, int row, int col) {
        //check for num being in range. return false if not in range
        if(num < 1 || num > 9) {
            return false;
        }

        //check for row being in range. return false if not in range
        if(row < 0 || row > grid2dArr.length){
            return false;
        }

        //check for col being in range. return false if not in range
        if(col < 0 || col > grid2dArr[row].length){
            return false;
        }

        //check each entry in the column, excluding the current position. return false if equal
        for(int i = 0; i < grid2dArr.length; i++){
            if(i != row){
                if(grid2dArr[i][col] == num){
                    return false;
                }
            }
        }

        //check each entry in the row, excluding the current position. return false if equal
        for(int i = 0; i < grid2dArr[row].length; i++){
            if(i != col){
                if(grid2dArr[row][i] == num){
                    return false;
                }
            }
        }

        //get box position relative to a 3x3 matrix.
        // leaves no remainder, so positions 0, 1, and 2 will be 0, positions 3, 4, and 5 will be 1, and positions 6, 7, and 8 will be 2
        int boxX = row / 3;
        int boxY = col / 3;

        //iterate through grid2dArr[boxX * 3][boxY * 3] to grid2dArr[boxX * 3 + 3][boxY * 3 + 3] to go through the 9 values in the box. return false if equal
        for(int i = boxX * 3; i < boxX * 3 + 3; i++){
            for(int j = boxY * 3; j < boxY * 3 + 3; j++){
                if(i != row || j != col) {
                    if (grid2dArr[i][j] == num) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    //finds the coords of the next unfilled board spot. returns {-1, -1} if no unfilled slots
    public int[] findNextUnfilled(){
        for(int i = 0; i < grid2dArr.length; i++){
            for(int j = 0; j < grid2dArr[i].length; j++){
                if(grid2dArr[i][j] == 0){
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {-1, -1};
    }

    //prints the board to console
    public void printBoard(){
        for(int i = 0; i < grid2dArr.length; i++) {
            if(i % 3 == 0 && i != 0){
                System.out.println("---------------------------------");
            }
            for (int j = 0; j < grid2dArr[i].length; j++) {
                if(j % 3 == 0 && j != 0){
                    System.out.print(" | ");
                }
                String print = grid2dArr[i][j] == 0 ? " -" : " " + grid2dArr[i][j];
                if(j == grid2dArr.length - 1)
                    System.out.println(print);
                else
                    System.out.print(print + " ");
            }
        }
    }

    //sets the board to a random, solvable Sudoku board
    public void randomizeBoard(){
        this.grid2dArr = defaultBoard;
    }

    //sets the board to the default board
    public void setDefaultBoard(){
        this.grid2dArr = defaultBoard;
    }

    //clones the board into a new matrix
    public int[][] copyBoard(){
        int[][] copy = new int[grid2dArr.length][];
        for(int i = 0; i < grid2dArr.length; i++){
            copy[i] = grid2dArr[i].clone();
        }
        return copy;
    }

    //basic getter
    public int[][] getGrid() {
        return grid2dArr;
    }

    //basic setter for a position
    public void setPosition(int num, int row, int col) {
        this.grid2dArr[row][col] = num;
    }
}
