import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    // Add tests
    @Test
    public void add_first_from_empty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("Apple");  // ["Apple"]

        List<String> i = lld1.toList();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(i).containsExactly("Apple").inOrder();
    }

    @Test
    public void add_last_from_empty() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("Apple");  // ["Apple"]

        List<String> i = lld1.toList();
        assertThat(lld1.size()).isEqualTo(1);
        assertThat(i).containsExactly("Apple").inOrder();
    }

    @Test
    public void add_first_nonempty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addLast("b");
        lld.addLast("c"); // ["b", "c"]

        lld.addFirst("a"); // ["a", "b", "c"]

        assertThat(lld.size()).isEqualTo(3);
        assertThat(lld.get(0)).isEqualTo("a");
        assertThat(lld.get(1)).isEqualTo("b");
        assertThat(lld.get(2)).isEqualTo("c");
        assertThat(lld.toList()).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    public void add_last_nonempty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("b");
        lld.addFirst("a"); // ["a", "b"]

        lld.addLast("c"); // ["a", "b", "c"]

        assertThat(lld.size()).isEqualTo(3);
        assertThat(lld.get(0)).isEqualTo("a");
        assertThat(lld.get(1)).isEqualTo("b");
        assertThat(lld.get(2)).isEqualTo("c");
        assertThat(lld.toList()).containsExactly("a", "b", "c").inOrder();
    }

    // Add after remove tests
    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("c");
        lld.addFirst("b");
        lld.addFirst("a"); // ["a", "b", "c"]

        lld.removeFirst();
        lld.removeFirst();
        lld.removeFirst(); // []

        lld.addFirst("Apple"); // ["Apple"]

        assertThat(lld.size()).isEqualTo(1);
        assertThat(lld.get(0)).isEqualTo("Apple");
        assertThat(lld.toList()).containsExactly("Apple").inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("c");
        lld.addFirst("b");
        lld.addFirst("a"); // ["a", "b", "c"]

        lld.removeLast();
        lld.removeLast();
        lld.removeLast(); // []

        lld.addLast("Apple"); // ["Apple"]

        assertThat(lld.size()).isEqualTo(1);
        assertThat(lld.get(0)).isEqualTo("Apple");
        assertThat(lld.toList()).containsExactly("Apple").inOrder();
    }

    // Remove tests
    @Test
    public void remove_first() {
         Deque61B<String> lld = new LinkedListDeque61B<>();

         lld.addFirst("Apple");
         lld.addFirst("Pear");
         lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

         lld.removeFirst(); // ["Pear", "Apple"]

        assertThat(lld.size()).isEqualTo(2);
        assertThat(lld.toList()).containsExactly("Pear", "Apple").inOrder();
    }

    @Test
    public void remove_last() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        lld.removeLast(); // ["Banana", "Pear"]

        assertThat(lld.size()).isEqualTo(2);
        assertThat(lld.toList()).containsExactly("Banana", "Pear").inOrder();
    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]
        lld.removeLast();
        lld.removeLast(); // ["Banana"]

        lld.removeFirst(); // []

        assertThat(lld.toList()).containsExactly().inOrder();
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]
        lld.removeLast();
        lld.removeLast(); // ["Banana"]

        lld.removeLast(); // []

        assertThat(lld.toList()).containsExactly().inOrder();
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]
        lld.removeLast();

        lld.removeFirst(); // ["Pear"]

        assertThat(lld.toList()).containsExactly("Pear").inOrder();
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]
        lld.removeLast();

        lld.removeLast(); // ["Banana"]

        assertThat(lld.toList()).containsExactly("Banana").inOrder();
    }

    // Get tests
    @Test
    public void get_valid() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.get(1);

        assertThat(i).isEqualTo("Pear");
    }

    @Test
    public void get_oob_large() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.get(3);

        assertThat(i).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.get(-1);

        assertThat(i).isEqualTo("Apple");
    }

    @Test
    public void get_recursive_valid() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.getRecursive(1);

        assertThat(i).isEqualTo("Pear");
    }

    @Test
    public void get_recursive_oob_large() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.getRecursive(3);

        assertThat(i).isEqualTo(null);
    }

    @Test
    public void get_recursive_oob_neg() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        String i = lld.getRecursive(-1);

        assertThat(i).isEqualTo("Apple");
    }

    // Size tests
    @Test
    public void size() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        assertThat(lld.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple");
        lld.addFirst("Pear");
        lld.addFirst("Banana"); // ["Banana", "Pear", "Apple"]

        lld.removeLast();
        lld.removeLast();
        lld.removeLast(); // []

        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    public void size_after_remove_from_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.removeLast(); // size = 0;

        assertThat(lld.size()).isEqualTo(0);
    }

    // IsEmpty tests
    @Test
    public void is_empty_true() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple"); // ["Apple"]
        lld.removeLast(); // []

        assertThat(lld.isEmpty()).isEqualTo(true);
    }

    @Test
    public void is_empty_false() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple"); // ["Apple"]
        lld.removeLast(); // []

        lld.addFirst("Banana"); // ["Banana"]

        assertThat(lld.isEmpty()).isEqualTo(false);
    }

    // ToList tests
    @Test
    public void to_list_empty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple"); // ["Apple"]
        lld.removeLast(); // []

        assertThat(lld.toList()).containsExactly().inOrder();
    }

    @Test
    public void to_list_nonempty() {
        Deque61B<String> lld = new LinkedListDeque61B<>();

        lld.addFirst("Apple"); // ["Apple"]
        lld.removeLast(); // []

        lld.addFirst("Banana"); // ["Banana"]

        assertThat(lld.toList()).containsExactly("Banana").inOrder();
    }
}