import javafx.scene.control.Tab;
import javafx.util.Pair;

import java.util.*;
import java.util.AbstractQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class services solutions for slide puzzle.
 */
public class SlidePuzzleSolver {

    private PermutationState beginState;

    private boolean isSolved;

    private ArrayList<SlidePuzzleMove> solution;
    private HashSet<Long> path;

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
     *
     * @throws Exception when given puzzle is not solvable.
     */
    public ArrayList<SlidePuzzleMove> getSolution() throws Exception {
        if (isSolved)
            return solution;
        else {
            solve();
            return solution;
        }
    }

    /**
     * Computes array of moves provide to solved slide puzzle.
     * Uses IDA* algorithm to find solution.
     *
     * @throws Exception when given puzzle is not solvable.
     */
    private void solve() throws Exception {

        if (beginState.isSolvable()) {
            int threshold = 1;

            while (!isSolved) {
                System.out.println(threshold);
                solution.clear();
                path = new HashSet<>();
                threshold = search(beginState, 0, threshold);
            }

            path.clear();
        }
        else {
            throw new Exception("This puzzle is not solvable");
        }
    }

    /**
     * Limited depth first search.
     *
     * @param state - searching begin state - PermutationState's class object
     * @param distanceFromRoot - current distance form begin state.
     * @param threshold - limit to searching.
     *
     * @return minimum of all ‘f’(rating of state, approximately computed cost
     *         to reach solved puzzle visiting current state),
     *         greater than threshold encountered.
     */
    private int search(PermutationState state, int distanceFromRoot, int threshold) {
        path.add(state.getPermutation());

        if (state.isSolved()) {
            state.print();
            isSolved = true;
            path.remove(state.getPermutation());
            return distanceFromRoot;
        }

        state.setDistanceFromRoot(distanceFromRoot);
        int f = state.getRating();
        if (f > threshold) {
            path.remove(state.getPermutation());
            return f;
        }

        ArrayList<Pair<PermutationState, SlidePuzzleMove>> successors = state.getPossibleNextStates();

        int min = 1000000000;

        for (Pair<PermutationState, SlidePuzzleMove> succ : successors) {
            if (!path.contains(succ.getKey().getPermutation())) {
                solution.add(succ.getValue());

                int temp = search(succ.getKey(), state.getDistanceFromRoot() + 1, threshold);

                if (isSolved) {
                    path.remove(state.getPermutation());
                    return min;
                }
                if (temp < min)
                    min = temp;

                solution.remove(solution.size() - 1);
            }
        }

        path.remove(state.getPermutation());
        return min;
    }

    /**
     * Method that prints solution:
     *      beginState ==(move of "zero" tile)==> next state ===...==> ==(move of "zero" tile")==> solved puzzle
     *
     *      STATE
     *
     *      LEFT/RIGHT/TOP/DOWN
     *
     *      STATE
     *
     *      LEFT/RIGHT/TOP/DOWN
     *
     *      STATE
     *         .
     *         .
     *         .
     *
     *      and go on.
     */
    public void printSolution() {
        TableState state = new TableState(beginState);
        for (int i = 0; i < solution.size(); i++) {

            state.print();
            state.makeMove(solution.get(i));
            
            System.out.println();

            if (solution.get(i).getDirection() == SlidePuzzleMove.UP)
                System.out.println("UP");

            if (solution.get(i).getDirection() == SlidePuzzleMove.DOWN)
                System.out.println("DOWN");

            if (solution.get(i).getDirection() == SlidePuzzleMove.LEFT)
                System.out.println("LEFT");

            if (solution.get(i).getDirection() == SlidePuzzleMove.RIGHT)
                System.out.println("RIGHT");

            System.out.println();
        }

        state.print();
    }

}
