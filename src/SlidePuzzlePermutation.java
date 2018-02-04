
/**
 * Permutation representation of slide puzzle.
 */
public class SlidePuzzlePermutation {

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
    public SlidePuzzlePermutation(long permutation, int x, int y) {
        this.permutation = permutation;
        this.x = x;
        this.y = y;
    }

    /**
     * Compute permutation based on given tabled representation of slide puzzle.
     *
     * @param slidePuzzleTable tabled representation of slide puzzle.
     */
    public SlidePuzzlePermutation(SlidePuzzleTable slidePuzzleTable) {
        permutation = 0;
        x = slidePuzzleTable.getWidth();
        y = slidePuzzleTable.getHeight();

        Permutation perm = new Permutation(slidePuzzleTable.getTable());
        permutation = perm.whichPermutation();
    }

    /**
     * Copy constructor for SlidePuzzlePermutation class.
     *
     * @param slidePuzzlePermutation is source which will be copied.
     */
    public SlidePuzzlePermutation(SlidePuzzlePermutation slidePuzzlePermutation) {
        x = slidePuzzlePermutation.getX();
        y = slidePuzzlePermutation.getY();
        permutation = slidePuzzlePermutation.permutation;
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
