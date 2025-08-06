import deque.Maximizer61B;
import deque.ArrayDeque61B;

import org.junit.jupiter.api.*;

import java.util.Comparator;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class Maximizer61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        ArrayDeque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("");
        ad.addFirst("2");
        ad.addFirst("fury road");
        assertThat(Maximizer61B.max(ad, new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test
    public void max_default() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(5);
        deque.addLast(3);
        deque.addLast(2);
        deque.addLast(4);

        Integer result = Maximizer61B.max(deque);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void max_different_comp() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("kiwi");
        deque.addLast("grapefruit");

        Comparator<String> byLength = Comparator.comparingInt(String::length);
        String result = Maximizer61B.max(deque, byLength);

        assertThat(result).isEqualTo("grapefruit");
    }

    @Test
    public void max_empty() {
        ArrayDeque61B<String> empty = new ArrayDeque61B<>();

        String result = Maximizer61B.max(empty);

        assertThat(result).isNull();
    }

    @Test
    public void max_nonempty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("zebra");
        deque.addLast("cat");
        deque.addLast("monkey");
        deque.addLast("ant");

        String result = Maximizer61B.max(deque);

        assertThat(result).isEqualTo("zebra");
    }

}
