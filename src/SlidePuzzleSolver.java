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
            solve();
            isSolved = true;
            return solution;
        }
    }

    /**
     * Computes array of moves provide to solved slide puzzle.
     *
     * Uses A* algorithm to find solution.
     */
    private void solve() {
        HashSet<Long> visited = new HashSet<>();
        Queue<PermutationState> Q = new PriorityQueue<>(new Comparator<PermutationState>() {
            @Override
            public int compare(PermutationState left, PermutationState right) {
                return Integer.compare(left.getRating(), right.getRating());
            }
        });

        beginState.setPrevState(null);
        beginState.setDistanceFromRoot(0);

        Q.add(beginState);

        PermutationState currState;
        while ((currState = Q.poll()) != null) {

            visited.add(currState.getPermutation());
            if (currState.isSolved()) {
                //TODO find path to the solved state.
                System.out.println();
                while (currState != null) {
                    currState.print();
                    System.out.println();
                    currState = currState.getPrevState();
                }
                break;
            }

            ArrayList<Pair<PermutationState, SlidePuzzleMove>> nextStates = currState.getPossibleNextStates();

            for (Pair<PermutationState, SlidePuzzleMove> i : nextStates) {
                if (i.getKey().getPermutation() != currState.getPermutation() && !visited.contains(i.getKey().getPermutation())) {


                    i.getKey().setDistanceFromRoot(currState.getDistanceFromRoot() + 1);
                    i.getKey().setPrevState(currState);

                    Q.add(i.getKey());
                }
            }

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
