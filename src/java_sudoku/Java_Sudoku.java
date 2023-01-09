/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package java_sudoku;

import java.util.Scanner;

/**
 *
 * @author stefk
 */
public class Java_Sudoku {

    private static final int NUMBER_OF_ROWS = 9;
    private static final int NUMBER_OF_COLUMNS = 9;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int[][] board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

        System.out.println("Enter your current board (if a tile is empty enter 0 in it's place)");
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            for (int j = 0; j < NUMBER_OF_COLUMNS; j++) {
                board[i][j] = input.nextInt();
                if (board[i][j] < 0 || board[i][j] > 9) {
                    System.out.println("You have entered invalid information, please review it again!");
                    break;
                }
            }
        }
        /*EXAMPLE BOARD
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };*/
        if (solveBoard(board)) {
            System.out.println("Here is the solution:");
            printBoard(board);
        } else {
            System.out.println("This is an unsolvable board");
        }
    }

    private static boolean isThisNumberInTheRow(int[][] board, int number, int row) {
        for (int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }

        return false;
    }

    private static boolean isThisNumberInTheColumn(int[][] board, int number, int column) {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }

        return false;
    }

    private static boolean isThisNumberInTheBox(int[][] board, int number, int row, int column) {
        final int currentBoxRow = row - row % 3;
        final int currentBoxColumn = column - column % 3;

        for (int i = currentBoxRow; i < currentBoxRow + 3; i++) {
            for (int j = currentBoxColumn; j < currentBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isThisAValidPlacement(int[][] board, int number, int row, int column) {
        return !isThisNumberInTheRow(board, number, row)
                && !isThisNumberInTheColumn(board, number, column)
                && !isThisNumberInTheBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
                if (board[row][column] == 0) {
                    for (int currentAttempt = 1; currentAttempt <= NUMBER_OF_ROWS; currentAttempt++) {
                        if (isThisAValidPlacement(board, currentAttempt, row, column)) {
                            board[row][column] = currentAttempt;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < NUMBER_OF_ROWS; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < NUMBER_OF_COLUMNS; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println("");
        }
    }
}
