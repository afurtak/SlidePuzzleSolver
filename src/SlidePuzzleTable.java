import javax.sound.midi.SysexMessage;

/**
 * Tabled permutation of slide puzzle.
 */
public class SlidePuzzleTable {


    private int [] table;
    private int width, height;

    /**
     * Fill table first lexicographically permutation of possibly states of slide puzzle.
     * Other words, creates representation of solved slide puzzle
     *
     * @param width is width of slide puzzle
     * @param height is height of slide puzzle
     */
    public SlidePuzzleTable(int width, int height) {
        this.width = width;
        this.height = height;
        table = new int[width * height];

        for (int i = 0; i < width * height; i++)
            table[i] = i;
    }

    /**
     * @param table
     * @param width
     * @param height
     */
    public SlidePuzzleTable(int[] table, int width, int height) {
        this.width = width;
        this.height = height;
        this.table = new int[width * height];

        System.arraycopy(table, 0, this.table, 0, width * height);
    }

    /**
     * Compute tabled representation of slide puzzle based on permutation
     * representation.
     *
     * @param permutation permutation representation of slide puzzle.
     */
    public SlidePuzzleTable(SlidePuzzlePermutation permutation) {

    }

    /**
     * Copy constructor for SlidePuzzleTable class.
     * @param slidePuzzleTable is source which will be copied.
     */
    public SlidePuzzleTable(SlidePuzzleTable slidePuzzleTable) {
        width = slidePuzzleTable.getWidth();
        height = slidePuzzleTable.getHeight();

        this.table = new int[width * height];
        System.arraycopy(slidePuzzleTable.getTable(), 0, this.table, 0, width * height);
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