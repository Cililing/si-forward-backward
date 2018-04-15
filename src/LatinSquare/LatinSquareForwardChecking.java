package LatinSquare;

import shared.SolutionModel;
import shared.Utils;

import java.util.ArrayList;

public class LatinSquareForwardChecking {
    private int numberOfInvocations;
    private int numberOfSolutions;
    private boolean printSolutions;

    public LatinSquareForwardChecking(int boardSize, SolutionModel solutionModel, boolean printSolutions) {
        this.printSolutions = printSolutions;

        solutionModel.setBoardSize(boardSize);

        long start = System.currentTimeMillis();
        int[][] matrix = Utils.emptyArray(boardSize);
        solve(matrix, getPossibilitiesArray(matrix, 0, 0), 0, 0);

        solutionModel.setTime(System.currentTimeMillis() - start);

        solutionModel.setNumberOfInvocations(this.numberOfInvocations);
        solutionModel.setNumberOfSolutions(this.numberOfSolutions);
    }

    private void solve(int[][] matrix, ArrayList<Integer> possibilities, int column, int row) {
        if (column == matrix.length) {
            return;
        }

        numberOfInvocations++;

        possibilities.forEach(integer -> {
            matrix[column][row] = integer;

            ArrayList<Integer> newPossibilities = new ArrayList<>();
            if (row < matrix.length - 1) {
                newPossibilities = getPossibilitiesArray(matrix, column, row + 1);
            } else if (column < matrix.length - 1) {
                newPossibilities = getPossibilitiesArray(matrix, column + 1, 0);
            }

            if (!newPossibilities.isEmpty()) {
                if (row < matrix.length - 1) {
                    solve(matrix, newPossibilities, column,row + 1);
                } else if (column < matrix.length - 1) {
                    solve(matrix, newPossibilities, column + 1, 0);
                }
            }

            if (row == matrix.length - 1 && column == matrix.length - 1) {
                numberOfSolutions++;

                if (printSolutions) {
                    Utils.printSolution(matrix);
                }
            }
        });

        matrix[column][row] = 0;

    }

    private ArrayList<Integer> getPossibilitiesArray(int[][] matrix, int column, int row) {
        ArrayList<Integer> threatenList = new ArrayList<>(matrix.length);
        for (int i = 1; i < matrix.length + 1; i++) {
            if (isNumberPossible(matrix, column, row, i)) {
                threatenList.add(i);
            }
        }
        return threatenList;
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
        new LatinSquareForwardChecking(4, solutionModel, false);
        System.out.println(solutionModel);
    }
}
