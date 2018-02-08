import javafx.scene.control.Tab;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class services solutions for slide puzzle.
 */
public class SlidePuzzleSolver {

    private PermutationState beginState;

    private ArrayList<SlidePuzzleMove> solution;

    private HashSet<Long> visited;

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
            visited = new HashSet<>();
            solve(new PermutationState(beginState), new SlidePuzzleMove(SlidePuzzleMove.LEFT));
            isSolved = true;
            return solution;
        }
    }

    /**
     * Computes array of moves provide to solved slide puzzle.
     *
     * Uses DFS algorithm to search states graph looking for solution.
     */
    private void solve(PermutationState currState, SlidePuzzleMove move) {
        visited.add(currState.getPermutation());

        if (currState.isSolved())
            isSolved = true;

        if (!isSolved) {
            ArrayList<Pair<PermutationState, SlidePuzzleMove>> nextStates = currState.getPossibleNextStates();

            for (Pair<PermutationState, SlidePuzzleMove> i : nextStates) {

                if (!visited.contains(i.getKey().getPermutation()))
                    solve(i.getKey(), i.getValue());

                if (isSolved)
                    break;
            }
        }

        if (isSolved) {
            solution.add(move);
        }
    }

    public void printSolution() {
        TableState state = new TableState(beginState);
        for (int i = solution.size() - 2; i >= 0; i--) {

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
