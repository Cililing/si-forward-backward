package Quuens;

import shared.SolutionModel;
import shared.SolutionPair;
import shared.Utils;

import java.util.ArrayList;

public class Queens {

    public static void main(String... args) {
        ArrayList<SolutionPair> solutions = new ArrayList<>();
        for(int i = 4; i <= 10; i++) {
            solutions.add(compareAlg(i, false));
            System.out.println(String.format("Checking for N = %d", i));
        }

        Utils.proccessSolution(solutions);
    }

    /**
     * @return pair: back, forward
     */
    public static SolutionPair compareAlg(int rows, boolean printSolutions) {
        SolutionModel solutionModelBt = new SolutionModel();
        QueensBacktracking backtracking = new QueensBacktracking(rows, solutionModelBt, printSolutions);

        SolutionModel solutionModelFc = new SolutionModel();
        QueensForwardChecking fc = new QueensForwardChecking(rows, solutionModelFc, printSolutions);

        return new SolutionPair(solutionModelBt, solutionModelFc);
    }
}
