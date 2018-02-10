import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Permutation representation of slide puzzle.
 */
public class PermutationState {

    private long permutation;
    private int x, y;

    private int distanceFromRoot;
    private int rating;

    /**
     * @param permutation is permutation
     * @param x is width of slide puzzle
     * @param y is height of slide puzzle
     */
    public PermutationState(long permutation, int x, int y) {
        rating = -1;
        this.permutation = permutation;
        this.x = x;
        this.y = y;
    }

    /**
     * Compute permutation based on given tabled representation of slide puzzle.
     *
     * @param tableState tabled representation of slide puzzle.
     */
    public PermutationState(TableState tableState) {
        permutation = 0;
        x = tableState.getWidth();
        y = tableState.getHeight();

        rating = -1;
        Permutation perm = new Permutation(tableState.getTable());
        permutation = perm.whichPermutation();
    }

    /**
     * Copy constructor for SlidePuzzlePermutation class.
     *
     * @param permutationState is source which will be copied.
     */
    public PermutationState(PermutationState permutationState) {
        x = permutationState.getX();
        y = permutationState.getY();
        permutation = permutationState.permutation;
        rating = -1;
    }

    /**
     * Returns an array contains all possible states such that, exist move provides to that state.
     *
     * @return array of possible current state's next states.
     */
    public ArrayList<Pair<PermutationState, SlidePuzzleMove>> getPossibleNextStates() {

        TableState tableState = new TableState(this);
        ArrayList<Pair<TableState, SlidePuzzleMove>> nextTableSates = tableState.getPossibleNextStates();
        ArrayList<Pair<PermutationState, SlidePuzzleMove>> result = new ArrayList<>();

        for (Pair<TableState, SlidePuzzleMove> i : nextTableSates) {
            result.add(new Pair<>(new PermutationState(i.getKey()), i.getValue()));
        }

        return result;
    }

    /**
     * Method that returns approximately distance to solved state in moves.
     * The value is computed by TableState class.
     *
     * @return Integer approximate number of moves required to solve slide puzzle
     */
    public int rateState() {
        return new TableState(this).rateState();
    }

    /**
     * Prints state of slide puzzle.
     */
    public void print() {
        new TableState(this).print();
    }

    public boolean isSolvable() {
        return new TableState(this).isSolvable();
    }

    public boolean isSolved() {
        return permutation == 0;
    }

    public long getPermutation() {
        return permutation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistanceFromRoot() {
        return distanceFromRoot;
    }

    public void setDistanceFromRoot(int distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public int getRating() {
        if (rating == -1)
            rating = rateState() + getDistanceFromRoot();
        return rating;
    }
}
