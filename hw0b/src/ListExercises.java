import javax.annotation.Untainted;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;

        for (Integer e : L) {
            sum += e;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> even = new ArrayList<>();

        for (Integer e : L) {
            if (e % 2 == 0) {
                even.add(e);
            }
        }
        return even;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> comm = new ArrayList<>();

        for (Integer e : L1) {
            if (L2.contains(e)) {
                comm.add(e);
            }
        }
        return comm;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count = 0;

        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (ch == c) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> lst = List.of("hello", "world", "welcome");

        System.out.println(ListExercises.countOccurrencesOfC(lst, 'o'));
        System.out.println(ListExercises.countOccurrencesOfC(lst, 'a'));
        System.out.println(ListExercises.countOccurrencesOfC(lst, 'l'));
    }
}
