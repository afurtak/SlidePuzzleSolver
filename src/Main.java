import javafx.util.Pair;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Permutation permutation = new Permutation(6, 123);

        PermutationState startState = new PermutationState(permutation.whichPermutation(), 2, 3);
        /*startState.print();

        ArrayList<Pair<PermutationState, SlidePuzzleMove>> t = startState.getPossibleNextStates();

        System.out.println();

        for (Pair<PermutationState, SlidePuzzleMove> i : t) {
            i.getKey().print();
            System.out.println();
        }



        System.out.println();

        System.out.println(Factorial.getFactorial(16));*/

        SlidePuzzleSolver solver = new SlidePuzzleSolver(startState);
        solver.getSolution();
        System.out.println();

        startState.print();

        solver.printSolution();

    }

}
