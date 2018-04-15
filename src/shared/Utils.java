package shared;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static int[][] emptyArray(int x) {
        int[][] res = new int[x][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                res[i][j] = 0;
            }
        }
        return res;
    }

    public static void printSolution(int[][] board) {
        Arrays.stream(board).forEach(
                row -> System.out.println(Arrays.toString(row))
        );
        System.out.println();
    }

    public static void proccessSolution(ArrayList<SolutionPair> solutions) {
        List<Integer> indexes = solutions.stream().map(item -> item.getBacktracking().getBoardSize()).collect(Collectors.toList());

        List<Integer> backNumberOfSolutions = solutions.stream().map(item -> item.getBacktracking().getNumberOfSolutions()).collect(Collectors.toList());
        List<Integer> backNumberOfInvocations = solutions.stream().map(item -> item.getBacktracking().getNumberOfInvocations()).collect(Collectors.toList());
        List<Long> backTime = solutions.stream().map(item -> item.getBacktracking().getTime()).collect(Collectors.toList());

        List<Integer> forwardNumberOfSolutions = solutions.stream().map(item -> item.getForwardChecking().getNumberOfSolutions()).collect(Collectors.toList());
        List<Integer> forwardNumberOfInvocations = solutions.stream().map(item -> item.getForwardChecking().getNumberOfInvocations()).collect(Collectors.toList());
        List<Long> forwardTime = solutions.stream().map(item -> item.getForwardChecking().getTime()).collect(Collectors.toList());


        XYChart tChart = QuickChart.getChart("Time", "N", "Time", "backTime", indexes, backTime);
        tChart.addSeries("forwardTime", indexes, forwardTime);

        XYChart iChart = QuickChart.getChart("Invocations", "N", "Inv", "backInv", indexes, backNumberOfInvocations);
        iChart.addSeries("forwardInv", indexes, forwardNumberOfInvocations);

        XYChart sChart = QuickChart.getChart("Solutions", "N", "Inv", "backSol", indexes, backNumberOfSolutions);
        sChart.addSeries("forwardSol", indexes, forwardNumberOfSolutions);

        new SwingWrapper(sChart).displayChart();
        new SwingWrapper(iChart).displayChart();
        new SwingWrapper(tChart).displayChart();

        solutions.forEach(item -> {
            System.out.println(String.format("Result for N = %d", item.getBacktracking().getBoardSize()));
            System.out.println("Backtrc: " +
                    "\t T = " + item.getBacktracking().getTime() +
                    "\t S = " + item.getBacktracking().getNumberOfSolutions() +
                    "\t I = " + item.getBacktracking().getNumberOfInvocations());
            System.out.println("Forward:" +
                    "\t T = " + item.getForwardChecking().getTime() +
                    "\t S = " + item.getForwardChecking().getNumberOfSolutions()  +
                    "\t I = " + item.getForwardChecking().getNumberOfInvocations());
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        });
    }
}
