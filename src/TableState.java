import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Tabled permutation of slide puzzle.
 */
public class TableState {


    private int [] table;
    private int width, height;

    private int zeroX, zeroY;

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

        zeroX = 0;
        zeroY = 0;
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

        for (int i = 0; i < table.length; i++) {
            if (table[i] == 0) {
                zeroX = i % width;
                zeroY = i / width;
            }
        }
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

        for (int i = 0; i < table.length; i++) {
            if (table[i] == 0) {
                zeroX = i % width;
                zeroY = i / width;
            }
        }
    }

    /**
     * Copy constructor for SlidePuzzleTable class.
     *
     * @param tableState is source which will be copied.
     */
    public TableState(TableState tableState) {
        width = tableState.getWidth();
        height = tableState.getHeight();

        this.table = new int[width * height];
        System.arraycopy(tableState.getTable(), 0, this.table, 0, width * height);

        zeroY = tableState.getZeroY();
        zeroX = tableState.getZeroX();
    }

    /**
     * Method that checks if given move is possible to perform.
     *
     * @param move - SlidePuzzleMove object represents move which is checked.
     * @return true if given move is possible, otherwise false.
     */
    public boolean isMovePossible(SlidePuzzleMove move) {
        if (move.getDirection() == SlidePuzzleMove.UP) {
            return zeroY != 0;
        }
        else if (move.getDirection() == SlidePuzzleMove.DOWN) {
            return zeroY != height - 1;
        }
        else if (move.getDirection() == SlidePuzzleMove.RIGHT) {
            return zeroX != width - 1;
        }
        else {
            return zeroX != 0;
        }
    }

    /**
     * Method that make given move.
     *
     * @param move SlidePuzzleMove object which is to perform.
     */
    public void makeMove(SlidePuzzleMove move) {
        int x, y;
        if (move.getDirection() == SlidePuzzleMove.UP) {
            x = zeroX;
            y = zeroY - 1;
        }
        else if (move.getDirection() == SlidePuzzleMove.DOWN) {
            x = zeroX;
            y = zeroY + 1;
        }
        else if (move.getDirection() == SlidePuzzleMove.RIGHT) {
            x = zeroX + 1;
            y = zeroY;
        }
        else {
            x = zeroX - 1;
            y = zeroY;
        }

        try {
            int value = getElement(x, y);
            setElement(zeroX, zeroY, value);
            setElement(x, y, 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        zeroX = x;
        zeroY = y;
    }

    /**
     * Returns an array contains all possible states such that, exist move provides to that state.
     *
     * @return array of possible current state's next states.
     */
    public ArrayList<Pair<TableState, SlidePuzzleMove>> getPossibleNextStates() {
        short[] directions = {SlidePuzzleMove.UP, SlidePuzzleMove.DOWN, SlidePuzzleMove.RIGHT, SlidePuzzleMove.LEFT};

        ArrayList<Pair<TableState, SlidePuzzleMove>> result = new ArrayList<>();

        for (short direction : directions) {
            if (isMovePossible(new SlidePuzzleMove(direction))) {
                try {
                    result.add(new Pair<>(getStateAfterMove(new SlidePuzzleMove(direction)), new SlidePuzzleMove(direction)));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * @return new TableState object represents state of slide puzzle after given move.
     * @throws Exception if given move is not possible to do.
     */
    public TableState getStateAfterMove(SlidePuzzleMove move) throws Exception {
        TableState tableState = new TableState(this);

        tableState.makeMove(move);

        return tableState;
    }

    /**
     * Prints state of slide puzzle.
     */
    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try {
                    System.out.print(getElement(x, y) + " ");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    /**
     * Sets value on given coordinates.
     *
     * @param x - x coordinate of piece of slide puzzle.
     * @param y - y coordinate of piece of slide puzzle.
     * @param value new value of piece on given coordinate
     * @throws Exception when given coordinates are not property.
     */
    private void setElement(int x, int y, int value) throws Exception {
        if (x >= width || y >= height)
            throw new Exception("Not property coordinates! X or Y is too big.");
        if (x < 0 || y < 0)
            throw new Exception("Not property coordinates! X or Y is negative.");

        table[y * width + x] = value;
    }

    /**
     * @param x x coordinate of table
     * @param y y coordinate of table
     * @return (x, y) element of table;
     * @throws Exception if x or y are not property coordinates;
     */
    public int getElement(int x, int y) throws Exception {
        if (x >= width || y >= height)
            throw new Exception("Not property coordinates! X or Y is too big.");
        if (x < 0 || y < 0)
            throw new Exception("Not property coordinates! X or Y is negative.");

        return table[y * width + x];
    }

    /**
     * Heuristic method that returns approximately distance to solved state in moves.
     * The value is sum of distance each piece of slide puzzle from its correct place.
     *
     * @return Integer approximate number of moves required to solve slide puzzle
     */
    public int rateState() {
        int result = 0;

        for (int i = 0; i < table.length; i++) {
            int x1 = i % width, y1 = i / width;
            int x2 = table[i] % width, y2 = table[i] / width;

            result += (Math.abs(x1 - x2) + Math.abs(y1 - y2));
        }
        return result;
    }

    /**
     * Counts number of inversions of current permutation of puzzle.
     * if size of puzzle is odd then,
     *      number of inversions must be odd.
     * otherwise,
     *      it depense on parity of row number in which is zero.
     *
     * @return true if slide puzzle is solvable, otherwise false.
     */
    public boolean isSolvable() {

        int numberInversion = new Permutation(table).getNumberInversion();

        numberInversion -= zeroX + zeroY * width;

        if (width % 2 == 1) {
            return numberInversion % 2 == 0;
        }
        else {
            if (zeroY % 2 == 0)
                return numberInversion % 2 == 0;
            else
                return numberInversion % 2 == 1;
        }

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

    public int getZeroX() {
        return zeroX;
    }

    public int getZeroY() {
        return zeroY;
    }
}
