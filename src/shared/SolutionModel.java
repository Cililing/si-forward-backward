package shared;

import java.util.Objects;

public class SolutionModel {
    private int boardSize;
    private long time;
    private int numberOfInvocations;
    private int numberOfSolutions;

    public SolutionModel() {
    }

    public SolutionModel(int boardSize, long time, int numberOfInvocations, int numberOfSolutions) {
        this.boardSize = boardSize;
        this.time = time;
        this.numberOfInvocations = numberOfInvocations;
        this.numberOfSolutions = numberOfSolutions;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getNumberOfInvocations() {
        return numberOfInvocations;
    }

    public void setNumberOfInvocations(int numberOfInvocations) {
        this.numberOfInvocations = numberOfInvocations;
    }

    public int getNumberOfSolutions() {
        return numberOfSolutions;
    }

    public void setNumberOfSolutions(int numberOfSolutions) {
        this.numberOfSolutions = numberOfSolutions;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    @Override
    public String toString() {
        return "SolutionModel{" +
                "boardSize=" + boardSize +
                ", time=" + time +
                ", numberOfInvocations=" + numberOfInvocations +
                ", numberOfSolutions=" + numberOfSolutions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolutionModel)) return false;
        SolutionModel that = (SolutionModel) o;
        return boardSize == that.boardSize &&
                time == that.time &&
                numberOfInvocations == that.numberOfInvocations &&
                numberOfSolutions == that.numberOfSolutions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardSize, time, numberOfInvocations, numberOfSolutions);
    }
}
