
/**
 * Representation of single move of slide puzzle.
 */
public class SlidePuzzleMove {

    public static final short UP = 1;
    public static final short DOWN = 2;
    public static final short RIGHT = 3;
    public static final short LEFT = 4;

    private int x, y;
    private short direction;

    /**
     * @param x - x coordinate of moved piece piece of slide puzzle.
     * @param y - y coordinate of moved piece piece of slide puzzle.
     * @param direction - direction of move of piece of slide puzzle.
     */
    public SlidePuzzleMove(int x, int y, short direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * @return x coordinate of moved piece piece of slide puzzle.
     */
    public int getX() {
        return x;
    }

    /**
     * @return y coordinate of moved piece piece of slide puzzle.
     */
    public int getY() {
        return y;
    }

    /**
     * @return direction of move of piece of slide puzzle.
     */
    public short getDirection() {
        return direction;
    }
}
