/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttgame;

import java.util.Arrays;

public class TicTacToe {
    char[][] board; // TicTacToe board has 3 rows and 3 columns.
    char PLAYER_1 = 'X';
    char PLAYER_2 = 'O';
    char turn; // Whose turn is it?

    // keeping track of no. of iterations done
    static int minimaxCount = 0;
    static int alphaBetaCount = 0;

    /*
     * Initialize the 2D array. X always start's first.
     */
    public TicTacToe() {
        board = new char[3][3];
        turn = 'X';

        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    /*
     * Check 3 rows, 3 cols and 2 diagonals for a win
     * If there is a winner return who won : X or O
     * Otherwise return a blank (space) character.
     */
    public char getWinner() {
        // checking rows for winner
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2])) {
                // System.out.println("wnr = " + board[i][0]);
                return board[i][0];
            }
        }
        // checking cols for winner
        for (int i = 0; i < 3; i++) {
            if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i])) {
                return board[0][i];
            }
        }

        // checking first diagonal for winner
        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2])) {
            return board[0][0];
        }

        // checking first diagonal for winner
        if ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0])) {
            return board[2][0];
        }

        return ' ';
    }

    /*
     * Pretty print the TTT board.
     */
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%c  |  %c  |  %c\n", board[i][0], board[i][1], board[i][2]);
            if (i < 2)
                System.out.println("-----------");

        }
        System.out.printf("minimax count = %d\nalphaBeta Count = %d\n\n", minimaxCount, alphaBetaCount);

        minimaxCount = 0;
        alphaBetaCount = 0;

    }

    /*
     * Return the Player who has to put a mark.
     */
    public char whoseTurn() {
        return turn;
    }

    /*
     * Fill the board at [row,col] with X or O
     * depending on whose turn it is
     * then change turn from X to O or O to X.
     */
    public void putMark(int row, int col) {
        board[row][col] = turn;
        turn = turn == 'X' ? 'O' : 'X';
    }

    public void switchTurn() {
        turn = turn == 'X' ? 'O' : 'X';
    }

    /*
     * Return the mark at [row,col] in the board.
     */
    public char getMark(int row, int col) {
        return board[row][col];
    }

    public int countRemainingMoves() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    count++;
                }
            }
        }
        return count;
    }
    // minimax algorithm for player 'X'

    public void autoPlay() {
        int maxScore = -111;
        int row = 0;
        int col = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    putMark(i, j);
                    // int score = minimax(false);
                    int score = alphaBeta(false);

                    if (maxScore < score) {
                        maxScore = score;
                        row = i;
                        col = j;
                    }
                    board[i][j] = ' ';
                    switchTurn();
                }
            }
        }
        putMark(row, col);
    }

    public int minimax(boolean isMaximizing) {
        if (getWinner() != ' ') {
            return getWinner() == 'X' ? 1 : -1;
        } else if (getWinner() == ' ' && countRemainingMoves() == 0) {
            return 0;
        }

        if (isMaximizing) {
            int maxScore = -111;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        minimaxCount++;
                        putMark(i, j);
                        int score = minimax(false);

                        if (maxScore < score) {
                            maxScore = score;
                        }
                        board[i][j] = ' ';
                        switchTurn();
                    }
                }
            }
            return maxScore;
        }

        else {
            int minScore = 111;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        minimaxCount++;
                        putMark(i, j);
                        int score = minimax(true);

                        if (minScore > score) {
                            minScore = score;
                        }
                        board[i][j] = ' ';
                        switchTurn();
                    }
                }
            }
            return minScore;
        }
    }

    public int alphaBeta(boolean isMaximizing) {
        if (getWinner() != ' ') {
            return getWinner() == 'X' ? 1 : -1;
        } else if (getWinner() == ' ' && countRemainingMoves() == 0) {
            return 0;
        }

        if (isMaximizing) {
            int maxScore = -111;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        putMark(i, j);
                        alphaBetaCount++;
                        int score = alphaBeta(false);

                        if (maxScore < score) {
                            maxScore = score;
                        }
                        board[i][j] = ' ';
                        switchTurn();

                        if (maxScore == 1) {
                            break;
                        }
                    }
                }
            }
            return maxScore;
        }

        else {
            int minScore = 111;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        putMark(i, j);
                        alphaBetaCount++;
                        int score = alphaBeta(true);

                        if (minScore > score) {
                            minScore = score;
                        }
                        board[i][j] = ' ';
                        switchTurn();

                        if (minScore == -1) {
                            break;
                        }
                    }
                }
            }
            return minScore;
        }
    }
}