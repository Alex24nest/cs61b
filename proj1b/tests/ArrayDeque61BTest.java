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
    public void testIterationMatchesExpectedArrayDeque() {
        Deque61B<String> list = new ArrayDeque61B<>();
        list.addFirst("a");
        list.addLast("b");
        list.addLast("c");

        Deque61B<String> actual = new ArrayDeque61B<>();
        for (String item : list) {
            actual.addLast(item);
        }

        List<String> expected = List.of("a", "b", "c");
        assertThat(actual).containsExactlyElementsIn(expected).inOrder();
    }

    @Test
    public void testEqualDeque61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");


        assertThat(lld1).isEqualTo(lld2);
    }


    @Test
    public void testToSrtingDeque61B() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }

    @Test
    public  void testRemoveFirst() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("middle");
        lld1.addFirst("back");
        lld1.addFirst("front");

        String r = lld1.removeFirst();

        assertThat(r).isEqualTo("front");
    }

    @Test
    public  void testRemoveFirst2() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        String r = lld1.removeFirst();

        assertThat(r).isEqualTo("front");
    }

    @Test
    public  void testRemoveLast() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        String r = lld1.removeLast();

        assertThat(r).isEqualTo("back");
    }

    @Test
    public  void testRemoveLast2() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("front");
        lld1.addFirst("middle");
        lld1.addFirst("back");

        String r = lld1.removeLast();

        assertThat(r).isEqualTo("front");
    }

    @Test
    public void testRemoveLastWhenArrayFullFromAddFirstOnly() {
        Deque61B<Integer> d = new ArrayDeque61B<>();

        for (int i = 1; i <= 8; i++) {
            d.addFirst(i);  // will be [8, 7, 6, 5, 4, 3, 2, 1]
        }

        Integer removed = d.removeLast();
        assertThat(removed).isEqualTo(1);

        List<Integer> expected = List.of(8, 7, 6, 5, 4, 3, 2);
        List<Integer> actual = d.toList();
        assertThat(actual).containsExactlyElementsIn(expected).inOrder();
    }

    @Test
    public void add_first_from_empty() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addFirst(10);

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.get(0)).isEqualTo(10);
    }

    @Test
    public void add_last_from_empty() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(10);

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.get(0)).isEqualTo(10);
    }

    @Test
    public void add_first_nonempty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("b");
        deque.addFirst("a");

        assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.get(0)).isEqualTo("a");
        assertThat(deque.get(1)).isEqualTo("b");
    }

    @Test
    public void add_last_nonempty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addFirst("a");
        deque.addLast("b");

        assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.get(0)).isEqualTo("a");
        assertThat(deque.get(1)).isEqualTo("b");
    }

    @Test
    public void add_first_trigger_resize() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        int initialCapacity = 8;

        for (int i = 0; i < initialCapacity; i++) {
            deque.addFirst(i);
        }

        deque.addFirst(100);

        assertThat(deque.size()).isEqualTo(initialCapacity + 1);
        assertThat(deque.get(0)).isEqualTo(100);
    }

    @Test
    public void add_last_trigger_resize() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        int initialCapacity = 8;

        for (int i = 0; i < initialCapacity; i++) {
            deque.addLast(i);
        }
        deque.addLast(100);

        assertThat(deque.size()).isEqualTo(initialCapacity + 1);
        assertThat(deque.get(initialCapacity)).isEqualTo(100);
    }

    @Test
    public void add_first_after_remove_to_empty() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();


        for (int i = 0; i < 5; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 5; i++) {
            deque.removeLast();
        }
        deque.addFirst(100);

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(100);
    }

    @Test
    public void add_last_after_remove_to_empty() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();


        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 5; i++) {
            deque.removeFirst();
        }
        deque.addLast(100);

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.toList()).containsExactly(100);
    }

    @Test
    public void remove_first() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(10);

        int removed = deque.removeFirst();

        assertThat(removed).isEqualTo(10);
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void remove_last() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(20);

        int removed = deque.removeLast();

        assertThat(removed).isEqualTo(20);
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void remove_first_to_empty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");

        deque.removeFirst();
        String last = deque.removeFirst(); // "b"

        assertThat(last).isEqualTo("b");
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void remove_last_to_empty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");

        deque.removeLast();
        String last = deque.removeLast(); // "a"

        assertThat(last).isEqualTo("a");
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void remove_first_to_one() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        deque.removeFirst();
        deque.removeFirst();

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.get(0)).isEqualTo("c");
    }

    @Test
    public void remove_last_to_one() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();

        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        deque.removeLast();
        deque.removeLast();

        assertThat(deque.size()).isEqualTo(1);
        assertThat(deque.get(0)).isEqualTo("a");
    }

    @Test
    public void remove_first_trigger_resize() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        int fill = 32;

        for (int i = 0; i < fill; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 25; i++) {
            deque.removeFirst();
        }

        deque.removeFirst();

        assertThat(deque.size()).isEqualTo(6);
        assertThat(deque.get(0)).isEqualTo(26);
    }

    @Test
    public void remove_last_trigger_resize() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        int fill = 32;

        for (int i = 0; i < fill; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 25; i++) {
            deque.removeLast();
        }

        deque.removeLast();

        assertThat(deque.size()).isEqualTo(6);
        assertThat(deque.get(0)).isEqualTo(31);
        }

    @Test
    public void get_valid() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        assertThat(deque.get(0)).isEqualTo("a");
        assertThat(deque.get(1)).isEqualTo("b");
        assertThat(deque.get(2)).isEqualTo("c");
    }

    @Test
    public void get_oob_large() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);

        assertThat(deque.get(2)).isNull(); // index 2 is out-of-bounds (only 0 and 1 are valid)
        assertThat(deque.get(100)).isNull(); // very large index
    }

    @Test
    public void get_oob_neg() {
        ArrayDeque61B<Double> deque = new ArrayDeque61B<>();
        deque.addLast(3.14);
        deque.addLast(2.71);

        assertThat(deque.get(-1)).isEqualTo(2.71); // negative index is invalid
    }

    @Test
    public void size() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);

        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(30);

        assertThat(deque.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");

        deque.removeFirst(); // removes "a"
        deque.removeLast();  // removes "b"

        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void size_after_remove_from_empty() {
        ArrayDeque61B<Double> deque = new ArrayDeque61B<>();

        deque.removeFirst(); // should return null but not crash
        deque.removeLast();  // should return null but not crash

        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void is_empty_true() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void is_empty_false() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addFirst("hello");
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void to_list_empty() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        List<Integer> actual = deque.toList();

        assertThat(actual).isEmpty();
    }

    @Test
    public void to_list_nonempty() {
        ArrayDeque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");

        List<String> actual = deque.toList();
        List<String> expected = List.of("a", "b", "c");

        assertThat(actual).containsExactlyElementsIn(expected).inOrder();
    }

    @Test
    public void resize_up_and_resize_down() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        int initialFill = 32;
        for (int i = 0; i < initialFill; i++) {
            deque.addLast(i);
        }

        for (int i = 0; i < 26; i++) {
            deque.removeFirst();
        }

        deque.removeFirst();

        assertThat(deque.size()).isEqualTo(5); // [27, 28, 29, 30, 31]

        List<Integer> expected = List.of(27, 28, 29, 30, 31);
        List<Integer> actual = deque.toList();

        assertThat(actual).containsExactlyElementsIn(expected).inOrder();
    }


}
