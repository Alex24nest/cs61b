import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     void  toListTest() {
         Deque61B<String> all = new ArrayDeque61B<>();

         all.addFirst("b");
         all.addFirst("a");
         all.addLast("c");

         assertThat(all.toList()).containsExactly("a", "b", "c").inOrder();
     }

     @Test
     void getTest() {
         Deque61B<Integer> all = new ArrayDeque61B<>();

         all.addFirst(2);
         all.addFirst(1);
         all.addFirst(0);
         all.addLast(3);
         all.addLast(4);
         all.addLast(5);

         int i = all.get(-6);

         assertThat(i).isEqualTo(0);
     }

    @Test
    void  removeFirstTest() {
        Deque61B<String> all = new ArrayDeque61B<>();

        all.addFirst("b");
        all.addFirst("a");
        all.addLast("c"); // ["a", "b", "c"]

        String i = all.removeFirst(); // ["b", "c"]

        assertThat(all.toList()).containsExactly("b", "c").inOrder();
        assertThat(i).isEqualTo("a");
    }

    @Test
    void  removeLastTest() {
        Deque61B<String> all = new ArrayDeque61B<>();

        all.addFirst("b");
        all.addFirst("a");
        all.addLast("c"); // ["a", "b", "c"]

        String i = all.removeLast(); // ["a", "b"]

        assertThat(all.toList()).containsExactly("a", "b").inOrder();
        assertThat(i).isEqualTo("c");
        assertThat(all.size()).isEqualTo(2);
     }

     @Test
     void resizeUpTest() {
         Deque61B<String> all = new ArrayDeque61B<>();

         all.addFirst("b");
         all.addFirst("a");
         all.addLast("c");
         all.addLast("d");
         all.addLast("e");
         all.addLast("f");
         all.addLast("g");
         all.addLast("i");

         all.addLast("h");
         all.addFirst("r");

         assertThat(all.toList()).containsExactly("r", "a", "b", "c", "d", "e", "f", "g", "i", "h").inOrder();
         assertThat(all.size()).isEqualTo(10);
     }

    @Test
    void resizeDownTest() {
        Deque61B<String> all = new ArrayDeque61B<>();

        for (int i = 0; i < 200; i++) {
            all.addFirst("a");
        }

        all.addLast("c");
        all.addLast("d");

        for (int i = 0; i < 194; i++) {
                all.removeFirst();
        }

        all.removeLast();

        assertThat(all.size()).isEqualTo(7);
    }

    @Test
    void IterableTest() {
        Deque61B<String> all = new ArrayDeque61B<>();

        all.addLast("c");
        all.addLast("d");
        all.addFirst("a");
        all.addFirst("a");
        all.addFirst("a");
        all.addFirst("a");
        all.addLast("c");
        all.addLast("c");
        all.addLast("c");


        assertThat(all).containsExactly("a", "a", "a", "a", "c", "d", "c", "c", "c");
    }

    @Test
    public void testIterationMatchesExpected() {
        Deque61B<String> list = new ArrayDeque61B<>();
        list.addFirst("a");
        list.addLast("b");
        list.addLast("c");

        // Convert Iterable to List
        Deque61B<String> actual = new ArrayDeque61B<>();
        for (String item : list) {
            actual.addLast(item);
        }

        // Expected result
        List<String> expected = List.of("a", "b", "c");

        // Truth assertion
        assertThat(actual).containsExactlyElementsIn(expected).inOrder();
    }

}
