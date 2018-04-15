package shared;

public class SolutionPair {
    private SolutionModel backtracking;
    private SolutionModel forwardChecking;

    public SolutionPair(SolutionModel backtracking, SolutionModel forwardChecking) {
        this.backtracking = backtracking;
        this.forwardChecking = forwardChecking;
    }

    @Override
    public String toString() {
        return "SolutionPair{" +
                "backtracking=" + backtracking +
                ", forwardChecking=" + forwardChecking +
                '}';
    }

    public SolutionModel getBacktracking() {
        return backtracking;
    }

    public void setBacktracking(SolutionModel backtracking) {
        this.backtracking = backtracking;
    }

    public SolutionModel getForwardChecking() {
        return forwardChecking;
    }

    public void setForwardChecking(SolutionModel forwardChecking) {
        this.forwardChecking = forwardChecking;
    }
}
