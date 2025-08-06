import deque.Deque61B;
import deque.LinkedListDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;


public class LinkedListDeque61BTest {

    @Test
    public void testIterationMatchesExpected() {
        Deque61B<String> list = new LinkedListDeque61B<>();
        list.addFirst("a");
        list.addLast("b");
        list.addLast("c");

        Deque61B<String> a = new LinkedListDeque61B<>();
        for (String i : list) {
            a.addLast(i);
        }

        assertThat(a.toList()).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    public void testEqualDeque61B() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

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
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }


}
