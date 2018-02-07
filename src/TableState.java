
/**
 * Tabled permutation of slide puzzle.
 */
public class TableState {


    private int [] table;
    private int width, height;

    /**
     * Fill table first lexicographically permutation of possibly states of slide puzzle.
     * Other words, creates representation of solved slide puzzle
     *
     * @param width is width of slide puzzle
     * @param height is height of slide puzzle
     */
    public TableState(int width, int height) {
        this.width = width;
        this.height = height;
        table = new int[width * height];

        for (int i = 0; i < width * height; i++)
            table[i] = i;
    }

    /**
     * @param table is a array, contains permutation of slide puzzle
     * @param width is width of slide puzzle
     * @param height is height of slide puzzle
     */
    public TableState(int[] table, int width, int height) {
        this.width = width;
        this.height = height;
        this.table = new int[width * height];

        System.arraycopy(table, 0, this.table, 0, width * height);
    }

    /**
     * Compute tabled representation of slide puzzle based on permutation
     * representation.
     *
     * @param permutationState permutation representation of slide puzzle.
     */
    public TableState(PermutationState permutationState) {

        this.width = permutationState.getX();
        this.height = permutationState.getY();

        Permutation permutation = new Permutation(width * height, permutationState.getPermutation());
        table = permutation.getNumbers();
    }

    /**
     * Copy constructor for SlidePuzzleTable class.
     * @param tableState is source which will be copied.
     */
    public TableState(TableState tableState) {
        width = tableState.getWidth();
        height = tableState.getHeight();

        this.table = new int[width * height];
        System.arraycopy(tableState.getTable(), 0, this.table, 0, width * height);
    }

    /**
     * @param move - SlidePuzzleMove object represents move which is checked.
     * @return true if given move is possible, otherwise false.
     */
    public boolean isMovePossible(SlidePuzzleMove move) {

        return true;
    }

    /**
     *
     * @param move
     */
    public void makeMove(SlidePuzzleMove move) throws Exception {

    }

    /**
     *
     * @return
     */
    public SlidePuzzleMove[] getPossibleMove() {
        return new SlidePuzzleMove[1];
    }

    /**
     *
     * @return
     */
    public TableState getStateAfterMove() {
        return this;
    }

    /**
     * Prints state of slide puzzle.
     */
    public void print() throws Exception {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(getElement(x, y) + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param x x coordinate of table
     * @param y y coordinate of table
     * @return (x, y) element of table;
     * @throws Exception if x or y are not property coordinates;
     */
    public int getElement(int x, int y) throws Exception {
        if (x > width || y > height)
            throw new Exception("Not property coordinates! X or Y is too big.");

        return table[x * width + y];
    }

    public int[] getTable() {
        return table;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setTable(int[] table) {
        this.table = table;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int y) {
        this.height = y;
    }
}
