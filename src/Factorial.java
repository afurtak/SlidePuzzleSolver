
/**
 *
 */
public class Factorial {

    /**
     * @param x integer
     * @return x! (x factorial)
     */
    static public long getFactorial(int x) {
        long result = 1;

        for (int i = 1; i <= x; i++)
            result *= i;

        return result;
    }

}
