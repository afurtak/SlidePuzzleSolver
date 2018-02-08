import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Class services solutions for slide puzzle.
 */
public class SlidePuzzleSolver {

    private PermutationState beginState;

    private ArrayList<SlidePuzzleMove> solution;

    private boolean isSolved;

    /**
     * @param permutationState start state of slide puzzle as permutation representation of state.
     */
    public SlidePuzzleSolver(PermutationState permutationState) {
        beginState = new PermutationState(permutationState);

        solution = new ArrayList<>();
        isSolved = false;
    }

    /**
     * @param tableState start state of slide puzzle as tabled representation of state.
     */
    public SlidePuzzleSolver(TableState tableState) {
        beginState = new PermutationState(tableState);

        solution = new ArrayList<>();
        isSolved = false;
    }

    /**
     * @return array of moves provide to solved slide puzzle.
     */
    public ArrayList<SlidePuzzleMove> getSolution() {
        if (isSolved)
            return solution;
        else {
            solve(beginState, new Pair<>(beginState, new SlidePuzzleMove(SlidePuzzleMove.LEFT)));
            isSolved = true;
            solution.remove(solution.size() - 1);
            return solution;
        }
    }

    /**
     * Computes array of moves provide to solved slide puzzle.
     *
     * Uses DFS algorithm to search states graph looking for solution.
     */
    private void solve(PermutationState currState, Pair<PermutationState, SlidePuzzleMove> prevState) {

        if (!currState.isSolved()) {
            ArrayList<Pair<PermutationState, SlidePuzzleMove>> nextStates = currState.getPossibleNextStates();

            for (Pair<PermutationState, SlidePuzzleMove> i : nextStates) {

                if (!prevState.getKey().equals(i.getKey()))
                    solve(i.getKey(), new Pair<>(currState, i.getValue()));

            }
        }
        else {
            isSolved = true;
        }

        if (isSolved) {
            solution.add(prevState.getValue());
        }
    }

}
