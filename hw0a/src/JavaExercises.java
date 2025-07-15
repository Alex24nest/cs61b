/**
 * Skeleton file for HW0A.
 * Exercises sourced from Practice-It by the University of Washington.
 * Original problems available at: https://practiceit.cs.washington.edu/
 *
 * @author Erik Kizior
 */
public class JavaExercises {

    /**
     * Prints a right-aligned triangle of stars ('*') with 5 lines.
     * The first row contains 1 star, the second 2 stars, and so on.
     */
    public static void starTriangle() {
        // TODO: Fill in this function
        for  (int i = 0; i < 5; i++) {
            String line = "";
            for (int j = 0; j < 5 - i - 1; j++) {
                line += " ";
            }
            for  (int k = 0; k < i+1; k++) {
                line += "*";
            }
            System.out.println(line);
        }
    }

    /**
     * Prints each character of a given string followed by its reverse index.
     * Example: printIndexed("hello") -> h4e3l2l1o0
     */
    public static void printIndexed(String s) {
        // TODO: Fill in this function
        String output = "";
        for (int i = 0; i < s.length(); i++) {
            output += s.charAt(i);
            output += String.valueOf(s.length() - 1 - i);
        }
        System.out.println(output);
    }

    /**
     * Returns a new string where each character of the given string is repeated twice.
     * Example: stutter("hello") -> "hheelllloo"
     */
    public static String stutter(String s) {
        // TODO: Fill in this function
        String output = "";
        for (int i = 0; i < s.length(); i++) {
            output += s.charAt(i);
            output += s.charAt(i);
        }
        return output;
    }

    /**
     * Determines the quadrant of a Cartesian coordinate (x, y).
     * Returns:
     *   1 for the first quadrant (x > 0, y > 0),
     *   2 for the second quadrant (x < 0, y > 0),
     *   3 for the third quadrant (x < 0, y < 0),
     *   4 for the fourth quadrant (x > 0, y < 0),
     *   0 if the point lies on an axis.
     */
    public static int quadrant(int x, int y) {
        // TODO: Fill in this function
        if (x == 0 || y == 0) {
            return 0;
        } else if (y > 0 && x > 0) {
            return 1;
        } else if (y > 0 && x < 0) {
            return 2;
        } else if (y < 0 && x < 0) {
            return 3;
        } else {
            return 4;
        }
    }

    public static void main(String[] args) {
        starTriangle();
        printIndexed("hello");
        System.out.println(stutter("hello"));
        System.out.println(quadrant(3, 4));  // Output: 1
        System.out.println(quadrant(-3, 4)); // Output: 2
        System.out.println(quadrant(-3, -4));// Output: 3
        System.out.println(quadrant(3, -4)); // Output: 4
        System.out.println(quadrant(0, 5));  // Output: 0
    }
}
