import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import java.util.*;

public class LinkedListDequeIntegrationTest {

    @Test
    public void randomizedIntegrationTest() {
        Deque61B<Integer> testDeque = new LinkedListDeque61B<>();
        LinkedList<Integer> refDeque = new LinkedList<>();
        StringBuilder trace = new StringBuilder();

        Random random = new Random();
        int numOps = 10000;

        for (int i = 0; i < numOps; i++) {
            int op = random.nextInt(8);

            switch (op) {
                case 0 -> {
                    int val = random.nextInt(1000);
                    testDeque.addFirst(val);
                    refDeque.addFirst(val);
                    trace.append("addFirst(").append(val).append(")\n");
                }
                case 1 -> {
                    int val = random.nextInt(1000);
                    testDeque.addLast(val);
                    refDeque.addLast(val);
                    trace.append("addLast(").append(val).append(")\n");
                }
                case 2 -> {
                    if (!refDeque.isEmpty()) {
                        Integer expected = refDeque.removeFirst();
                        Integer actual = testDeque.removeFirst();
                        trace.append("removeFirst()\n");
                        assertThat(actual).named(trace.toString()).isEqualTo(expected);
                    }
                }
                case 3 -> {
                    if (!refDeque.isEmpty()) {
                        Integer expected = refDeque.removeLast();
                        Integer actual = testDeque.removeLast();
                        trace.append("removeLast()\n");
                        assertThat(actual).named(trace.toString()).isEqualTo(expected);
                    }
                }
                case 4 -> {
                    int size = refDeque.size();
                    if (size > 0) {
                        int index = random.nextInt(size);
                        Integer expected = refDeque.get(index);
                        Integer actual = testDeque.get(index);
                        trace.append("get(").append(index).append(")\n");
                        assertThat(actual).named(trace.toString()).isEqualTo(expected);
                    }
                }
                case 5 -> {
                    boolean expected = refDeque.isEmpty();
                    boolean actual = testDeque.isEmpty();
                    trace.append("isEmpty()\n");
                    assertThat(actual).named(trace.toString()).isEqualTo(expected);
                }
                case 6 -> {
                    int expected = refDeque.size();
                    int actual = testDeque.size();
                    trace.append("size()\n");
                    assertThat(actual).named(trace.toString()).isEqualTo(expected);
                }
                case 7 -> {
                    List<Integer> expected = new ArrayList<>(refDeque);
                    List<Integer> actual = testDeque.toList();
                    trace.append("toList()\n");
                    assertThat(actual).named(trace.toString())
                            .containsExactlyElementsIn(expected).inOrder();
                }
            }
        }
    }
}
