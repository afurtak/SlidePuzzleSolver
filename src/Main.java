import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        int[] perm = {1, 5, 2, 3, 4, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Permutation permutation = new Permutation(9, 34634);

        PermutationState startState = new PermutationState(permutation.whichPermutation(), 3, 3);

        if (startState.isSolvable()) {
            SlidePuzzleSolver solver = new SlidePuzzleSolver(startState);
            solver.getSolution();
            System.out.println();
        }

        //solver.printSolution();

    }

}
