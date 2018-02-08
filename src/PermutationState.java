
/**
 * Permutation representation of slide puzzle.
 */
public class PermutationState {

    public static final short UP = 1;
    public static final short DOWN = 2;
    public static final short RIGHT = 3;
    public static final short LEFT = 4;

    private long permutation;
    private int x, y;

    /**
     * @param permutation is permutation
     * @param x is width of slide puzzle
     * @param y is height of slide puzzle
     */
    public PermutationState(long permutation, int x, int y) {
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
    }

    /**
     * Returns an array contains all possible states such that, exist move provides to that state.
     *
     * @return array of possible current state's next states.
     */
    public PermutationState[] getPossibleNextStates() {
        TableState tableState = new TableState(this);
        TableState[] nextTableStates = tableState.getPossibleNextStates();

        PermutationState[] nextPermutationStates = new PermutationState[nextTableStates.length];

        for (int i = 0; i < nextPermutationStates.length;i++) {
            nextPermutationStates[i] = new PermutationState(nextTableStates[i]);
        }

        return nextPermutationStates;
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
}
