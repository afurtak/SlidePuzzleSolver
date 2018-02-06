
/**
 * Class services solutions for slide puzzle.
 */
public class SlidePuzzleSolver {

    private PermutationState beginState;
    private PermutationState endState;

    private SlidePuzzleMove[] solution;

    private boolean isSolved;

    /**
     * @param permutationState start state of slide puzzle as permutation representation of state.
     */
    public SlidePuzzleSolver(PermutationState permutationState) {
        beginState = new PermutationState(permutationState);
        endState = new PermutationState(0, beginState.getX(), beginState.getY());

        isSolved = false;
    }

    /**
     * @param tableState start state of slide puzzle as tabled representation of state.
     */
    public SlidePuzzleSolver(TableState tableState) {
        beginState = new PermutationState(tableState);
        endState = new PermutationState(0, beginState.getX(), beginState.getY());

        isSolved = false;
    }

    /**
     * @return array of moves provide to solved slide puzzle.
     */
    public SlidePuzzleMove[] getSolution() {
        if (isSolved)
            return solution;
        else {
            solve();
            isSolved = true;
            return solution;
        }
    }

    /**
     * Computes array of moves provide to solved slide puzzle.
     */
    private void solve() {

    }
}
