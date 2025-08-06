package deque;

import static com.google.common.truth.Truth.assertThat;

import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.Test;
import java.util.*;

public class ArrayDeque61BEnhancementsTest {

    @Test
    public void randomizedIntegrationTest() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        LinkedList<Integer> ref = new LinkedList<>();
        StringBuilder trace = new StringBuilder();
        Random rnd = new Random(314159); // fixed seed
        int ops = 15000;

        for (int i = 0; i < ops; i++) {
            int op = rnd.nextInt(10); // now 10 types of operations
            switch (op) {
                case 0 -> {
                    int v = rnd.nextInt(1000);
                    test.addFirst(v);
                    ref.addFirst(v);
                    trace.append("addFirst(").append(v).append(")\n");
                }
                case 1 -> {
                    int v = rnd.nextInt(1000);
                    test.addLast(v);
                    ref.addLast(v);
                    trace.append("addLast(").append(v).append(")\n");
                }
                case 2 -> {
                    if (!ref.isEmpty()) {
                        Integer expected = ref.removeFirst();
                        Integer actual = test.removeFirst();
                        trace.append("removeFirst()\n");
                        System.out.println(trace);
                        assertThat(actual).isEqualTo(expected);
                    }
                }
                case 3 -> {
                    if (!ref.isEmpty()) {
                        Integer expected = ref.removeLast();
                        Integer actual = test.removeLast();
                        trace.append("removeLast()\n");
                        System.out.println(trace);
                        assertThat(actual).isEqualTo(expected);
                    }
                }
                case 4 -> {
                    if (!ref.isEmpty()) {
                        int index = rnd.nextInt(ref.size());
                        Integer expected = ref.get(index);
                        Integer actual = test.get(index);
                        trace.append("get(").append(index).append(")\n");
                        System.out.println(trace);
                        assertThat(actual).isEqualTo(expected);
                    }
                }
                case 5 -> {
                    boolean expected = ref.isEmpty();
                    boolean actual = test.isEmpty();
                    trace.append("isEmpty()\n");
                    System.out.println(trace);
                    assertThat(actual).isEqualTo(expected);
                }
                case 6 -> {
                    int expected = ref.size();
                    int actual = test.size();
                    trace.append("size()\n");
                    System.out.println(trace);
                    assertThat(actual).isEqualTo(expected);
                }
                case 7 -> {
                    List<Integer> expected = new ArrayList<>(ref);
                    List<Integer> actual = test.toList();
                    trace.append("toList()\n");
                    System.out.println(trace);
                    assertThat(actual).containsExactlyElementsIn(expected).inOrder();
                }
                case 8 -> {
                    // Mix addFirst and removeLast
                    int v = rnd.nextInt(1000);
                    test.addFirst(v);
                    ref.addFirst(v);
                    trace.append("addFirst(").append(v).append(")\n");
                    if (!ref.isEmpty()) {
                        Integer expected = ref.removeLast();
                        Integer actual = test.removeLast();
                        trace.append("removeLast()\n");
                        System.out.println(trace);
                        assertThat(actual).isEqualTo(expected);
                    }
                }
                case 9 -> {
                    // Mix addLast and removeFirst
                    int v = rnd.nextInt(1000);
                    test.addLast(v);
                    ref.addLast(v);
                    trace.append("addLast(").append(v).append(")\n");
                    if (!ref.isEmpty()) {
                        Integer expected = ref.removeFirst();
                        Integer actual = test.removeFirst();
                        trace.append("removeFirst()\n");
                        System.out.println(trace);
                        assertThat(actual).isEqualTo(expected);
                    }
                }
            }
        }
    }
}
