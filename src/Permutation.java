
/**
 * Representation of permutation natural some numbers.
 */
public class Permutation {

    private int[] numbers;

    /**
     * @param numbers is a permutation of some natural numbers.
     */
    public Permutation(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * @return which lexicographical permutation is holden perm.
     */
    long whichPermutation() {
        long result = 0;
        int[] permutation = new int[numbers.length];

        System.arraycopy(numbers, 0, permutation, 0, permutation.length);

        for (int i = 0; i < permutation.length; i++) {
            result += permutation[i] * Factorial.getFactorial((permutation.length) - i - 1);

            //updates relatives position;
            for (int j = i + 1; j < permutation.length; j++)
                if (permutation[j] > permutation[i])
                    permutation[j]--;
        }

        return result;
    }

    /**
     * Compute next permutation of holden numbers;
     *
     * Algorithm:
     *      1) find the longest not increasing sub sequence,
     *      2) set the pivot
     *      3) find the most right larger than pivot and swap it with pivot
     *      4) reverse previous found not increasing sub sequence
     *
     * @return false if next permutation does not exist. Otherwise true.
     */
    public boolean nextPermutation() {
        int i = numbers.length - 2;
        while (i >= 0 && numbers[i] >= numbers[i + 1])
            i--;

        if (i < 0)
            return false;

        int j = numbers.length - 1;
        while (numbers[j] <= numbers[i])
            j--;

        //swap
        int temp = numbers[j];
        numbers[j] = numbers[i];
        numbers[i] = temp;

        i++;
        j = numbers.length - 1;

        //reverse
        while (i < j) {
            temp = numbers[j];
            numbers[j] = numbers[i];
            numbers[i] = temp;

            j--;
            i++;
        }
        return true;
    }

    /**
     * Prints permutation in one line.
     */
    public void print() {
        for (int i : numbers)
            System.out.print(i);
        System.out.println();
    }

    public int[] getNumbers() {
        return numbers;
    }

    int getNumber(int n) {
        return numbers[n];
    }

}