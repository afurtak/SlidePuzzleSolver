
/**
 * Class represents single move of slide puzzle.
 *
 * Move means swap blank place with piece on UP\DOWN\RIGHT\LEFT from its.
 *
 * e.g.
 *      0 1 2      1 0 2
 *      3 4 5  ->  3 4 5
 *      6 7 8      6 7 8
 *
 *      is move RIGHT.
 *
 */
public class SlidePuzzleMove {

    public static final short UP = 1;
    public static final short DOWN = 2;
    public static final short RIGHT = 3;
    public static final short LEFT = 4;

    private short direction;

    public SlidePuzzleMove(short direction) {
        this.direction = direction;
    }

    public short getDirection() {
        return direction;
    }
    
}
