package LatinSquare;

import shared.SolutionModel;
import shared.Utils;

class LatinSquareBacktracking {

    private int numberOfInvocations;
    private int numberOfSolutions;
    private boolean printSolutions;

    public LatinSquareBacktracking(int boardSize, SolutionModel solutionModel, boolean printSolutions) {
        this.printSolutions = printSolutions;
        solutionModel.setBoardSize(boardSize);

        long start = System.currentTimeMillis();
        solve(Utils.emptyArray(boardSize), 0, 0);
        solutionModel.setTime(System.currentTimeMillis() - start);

        solutionModel.setNumberOfInvocations(this.numberOfInvocations);
        solutionModel.setNumberOfSolutions(this.numberOfSolutions);
    }

    private void solve(int[][] matrix, int column, int row) {
        if (column == matrix.length) {
            return;
        }

        numberOfInvocations++;

        for (int i = 1; i < matrix.length + 1; i++) {
            if (isNumberPossible(matrix, column, row, i)) {
                matrix[column][row] = i;

                if (row < matrix.length - 1) {
                    solve(matrix, column, row + 1);
                } else if (column < matrix.length - 1) {
                    solve(matrix, column + 1, 0);
                }

                if (row == matrix.length - 1 && column == matrix.length - 1) {
                    numberOfSolutions++;
                    if (printSolutions) {
                        Utils.printSolution(matrix);
                    }
                }

                matrix[column][row] = 0;
            }
        }
    }

    private boolean isNumberPossible(int[][] matrix, int column, int position, int number) {
        for (int i = 0; i < position + 1; i++) {
            if (matrix[column][i] == number) {
                return false;
            }
        }
        for (int i = 0; i < column + 1; i++) {
            if (matrix[i][position] == number) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SolutionModel solutionModel = new SolutionModel();
        new LatinSquareBacktracking(6, solutionModel, false);
        System.out.println(solutionModel);
    }
}