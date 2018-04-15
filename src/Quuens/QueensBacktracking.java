package Quuens;

import shared.SolutionModel;
import shared.Utils;

import static shared.Utils.printSolution;

class QueensBacktracking {

    private int numberOfInvocations;
    private int numberOfSolutions;

    private boolean printSolutions;

    public QueensBacktracking(int boardSize, SolutionModel solutionModel, boolean printSolutions) {
        this.printSolutions = printSolutions;

        solutionModel.setBoardSize(boardSize);

        long start = System.currentTimeMillis();
        solve(Utils.emptyArray(boardSize), 0);
        solutionModel.setTime(System.currentTimeMillis() - start);

        solutionModel.setNumberOfInvocations(this.numberOfInvocations);
        solutionModel.setNumberOfSolutions(this.numberOfSolutions);
    }

    private void solve(int[][] board, int col) {
        if (col == board.length) {
            if (printSolutions) {
                printSolution(board);
            }
            numberOfSolutions++;
            return;
        }

        numberOfInvocations++;

        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                solve(board, col + 1);
                board[i][col] = 0;
            }
        }
    }

    private boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        // Left side
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Upper diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) {
                return false;
            }

        // Lower diagonal
        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1) {
                return false;
            }

        return true;
    }

}