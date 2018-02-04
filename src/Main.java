

public class Main {

    public static void main(String[] args) {

        int[] perm = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Permutation permutation = new Permutation(perm);

        SlidePuzzleTable slidePuzzleTable = new SlidePuzzleTable(permutation.getNumbers(), 4, 4);
        SlidePuzzlePermutation slidePuzzlePermutation = new SlidePuzzlePermutation(slidePuzzleTable);

        boolean isCorrect = true;

        if (slidePuzzlePermutation.getPermutation() != 0) {
            isCorrect = false;
        }

        for (int i = 1; permutation.nextPermutation() && isCorrect; i++) {
            slidePuzzleTable.setTable(permutation.getNumbers());

            slidePuzzlePermutation = new SlidePuzzlePermutation(slidePuzzleTable);
            if (slidePuzzlePermutation.getPermutation() != i) {
                isCorrect = false;
            }
        }

        if (!isCorrect)
            System.out.println("something's gone wrong :(");
        else
            System.out.println("OK!");

    }

}
