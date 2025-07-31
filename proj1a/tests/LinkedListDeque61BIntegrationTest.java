import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import java.util.*;

public class LinkedListDeque61BIntegrationTest {

    @Test
    public void randomizedIntegrationTest() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        LinkedList<Integer> ref = new LinkedList<>();
        StringBuilder trace = new StringBuilder();
        Random rnd = new Random(123456);  // fixed seed for reproducibility
        int ops = 10000;

        for (int i = 0; i < ops; i++) {
            int op = rnd.nextInt(8);
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
                        Integer e = ref.removeFirst();
                        Integer a = test.removeFirst();
                        trace.append("removeFirst()\n");
                        System.out.println(trace);
                        assertThat(a).isEqualTo(e);
                    }
                }
                case 3 -> {
                    if (!ref.isEmpty()) {
                        Integer e = ref.removeLast();
                        Integer a = test.removeLast();
                        trace.append("removeLast()\n");
                        System.out.println(trace);
                        assertThat(a).isEqualTo(e);
                    }
                }
                case 4 -> {
                    int size = ref.size();
                    if (size > 0) {
                        int idx = rnd.nextInt(size);
                        Integer e = ref.get(idx);
                        Integer a = test.get(idx);
                        trace.append("get(").append(idx).append(")\n");
                        System.out.println(trace);
                        assertThat(a).isEqualTo(e);
                    }
                }
                case 5 -> {
                    boolean e = ref.isEmpty();
                    boolean a = test.isEmpty();
                    trace.append("isEmpty()\n");
                    System.out.println(trace);
                    assertThat(a).isEqualTo(e);
                }
                case 6 -> {
                    int e = ref.size();
                    int a = test.size();
                    trace.append("size()\n");
                    System.out.println(trace);
                    assertThat(a).isEqualTo(e);
                }
                case 7 -> {
                    List<Integer> e = new ArrayList<>(ref);
                    List<Integer> a = test.toList();
                    trace.append("toList()\n");
                    System.out.println(trace);
                    assertThat(a).containsExactlyElementsIn(e).inOrder();
                }
            }
        }
    }
}
