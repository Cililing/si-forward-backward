package LatinSquare;

import shared.SolutionModel;
import shared.SolutionPair;
import shared.Utils;

import java.util.ArrayList;

public class LatinSquare {

    public static void main(String... args) {
        ArrayList<SolutionPair> solutions = new ArrayList<>();
        for(int i = 1; i <= 6; i++) {
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
        SolutionModel solutionModelFc = new SolutionModel();

        Runnable btRunnable = () -> {
            LatinSquareBacktracking backtracking = new LatinSquareBacktracking(rows, solutionModelBt, printSolutions);
        }        ;
        Runnable fcRunnable = () -> {
            LatinSquareForwardChecking fc = new LatinSquareForwardChecking(rows, solutionModelFc, printSolutions);
        };

        Thread btThread = new Thread(btRunnable);
        Thread ftThread = new Thread(fcRunnable);

        btThread.run();
        ftThread.run();

        try {
            System.out.println("Waiting for FT");
            ftThread.join();
            System.out.println("Waiting for BT");
            btThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new SolutionPair(solutionModelBt, solutionModelFc);
    }
}
