package Quuens;

import shared.SolutionModel;
import shared.Utils;

public class QueensForwardChecking {

    private int numberOfInvocations;
    private int numberOfSolutions;
    private boolean printSolutions;

    public QueensForwardChecking(int boardSize, SolutionModel solutionModel, boolean printSolutions) {
        this.printSolutions = printSolutions;
        solutionModel.setBoardSize(boardSize);

        long start = System.currentTimeMillis();
        solve(Utils.emptyArray(boardSize), Utils.emptyArray(boardSize), 0);

        solutionModel.setTime(System.currentTimeMillis() - start);

        solutionModel.setNumberOfInvocations(this.numberOfInvocations);
        solutionModel.setNumberOfSolutions(this.numberOfSolutions);
    }

    private enum FixType {
        DECREASE(-1), INCREASE(1);

        int val;

        FixType(int val) {
            this.val = val;
        }
    }

    private boolean solve(int[][] board, int[][] threaten, int col) {
        if (col == board.length) {
            //printSolution(board);
            numberOfSolutions++;
            return true;
        }

        numberOfInvocations++;

        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            if (threaten[i][col] == 0) {
                board[i][col] = 1;
                fixThreatCounter(board, threaten, i, col, FixType.INCREASE);
                if (forwardCheck(board, threaten, col)) {
                    res = solve(board, threaten,col + 1);
                }
                fixThreatCounter(board, threaten, i, col, FixType.DECREASE);
                board[i][col] = 0;
            }
        }

        return res;
    }


    private void fixThreatCounter(int[][] board, int[][] threaten, int row, int col, FixType fixType) {
        for (int j = 1; j < board.length - col; j++) {
            threaten[row][col + j] += fixType.val; // horizontally
            if (row + j < board.length) {
                threaten[row + j][col + j] += fixType.val; //diagonal down
            }
            if (row - j >= 0) {
                threaten[row - j][col + j] += fixType.val; // diagonal up
            }
        }
    }

    private boolean forwardCheck(int[][] board, int[][] threaten, int col) {
        for (int i = col + 1; i < board.length; i++) {
            boolean canBePlaced = false;
            for (int row = 0; row < board.length && !canBePlaced; row++) {
                if (threaten[row][i] == 0) {
                    canBePlaced = true;
                }
            }
            if (!canBePlaced) return false;
        }
        return true;
    }
}
