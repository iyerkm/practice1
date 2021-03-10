package main.java.ds.design;

import java.util.Arrays;
import static main.java.common.Util.print;

public class Sudoku {
    private static boolean[][] row;
    private static boolean[][] col;
    private static boolean[][] box;
    private static boolean solved=false;

    public static void main(String[] args){
        char[][] board = new char[][]
            {{'5','3','.','.','7','.','.','.','.'},
             {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}};

        char[][] board1 = new char[][]
                        {{'.','2','.','.','.','.','.','4','6'},
                        {'.','1','.','.','2','8','.','.','.'},
                        {'.','5','6','.','.','.','.','7','1'},
                        {'4','.','1','.','.','2','7','.','.'},
                        {'.','.','.','4','.','7','.','.','.'},
                        {'.','.','7','3','.','.','5','.','4'},
                        {'1','9','.','.','.','.','3','5','.'},
                        {'.','.','.','5','1','.','.','2','.'},
                        {'3','4','.','.','.','.','.','1','.'}};
        print("Input:");
        print(Arrays.deepToString(board));
        solveSudoku(board);
        print("Result:");
        print(Arrays.deepToString(board));
    }

    public static void solveSudoku(char[][] board){
        row = new boolean[9][9];
        col = new boolean[9][9];
        box = new boolean[9][9];
        solved=false;

        fillRestrictedNumbers(board,board.length);
        solve(board,0);
    }

    private static void solve(char[][] board, int n) {
        if(n==81){
            solved = true;
            return;
        }
        int r = n/9;
        int c = n%9;
        int boxIndex = boxIndex(r,c);

        if(board[r][c]=='.'){
            for(int num=0;num<9;num++){
                if(!row[r][num] && !col[c][num] && !box[boxIndex][num]){
                    board[r][c]= (char) (num+'1');
                    row[r][num] = col[c][num] = box[boxIndex][num] = true;
                    solve(board,n+1);
                    if(solved){
                        return;
                    }
                    board[r][c]='.';
                    row[r][num] = col[c][num] = box[boxIndex][num] = false;
                }
            }
        } else {
            solve(board,n+1);
        }
    }

    private static void fillRestrictedNumbers(char[][] board, int length) {
        for(int r=0;r<length;r++){
            for(int c=0;c<length;c++){
                if(board[r][c]!='.'){
                    int currentNumber = board[r][c] - '1';
                    row[r][currentNumber]=true;
                    col[c][currentNumber]=true;
                    int boxIndex = boxIndex(r,c);
                    box[boxIndex][currentNumber] = true;
                }
            }
        }
//        print("rows:");
//        print(Arrays.deepToString(row));
//        print("cols:");
//        print(Arrays.deepToString(col));
//        print("box:");
//        print(Arrays.deepToString(box));
    }
    private static int boxIndex(int r, int c) {
        int rowOfIndex = (r/3) * 3;
        int colOfIndex = (c/3) % 3;
        return rowOfIndex + colOfIndex;
    }
}
