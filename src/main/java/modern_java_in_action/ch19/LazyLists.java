package modern_java_in_action.ch19;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyLists {

    static void run1() {
        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    }

    static void run2() {
        LazyList<Integer> numbers = from(2);

        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();

        System.out.println(two + " " + three + " " + four);
    }

    static void run3() {
        LazyList<Integer> numbers = from(2);

        int two = primes(numbers).head();
        int three = primes(numbers).tail().head();
        int five = primes(numbers).tail().tail().head();

        System.out.println(two + " " + three + " " + five);
    }

    static void run4() {
        printAll(primes(from(2)));
    }

    static void run5() {
        printAll2(primes(from(2)));
    }

    public static void main(String[] args) {

//        run1();
//        run2();
//        run3();
//        run4();
        run5();

    }

    interface MyList<T> {
        T head();
        MyList<T> tail();
        default boolean isEmpty() {return true;}
        MyList<T> filter(Predicate<T> p);
    }

    static class Empty<T> implements MyList<T> {

        @Override
        public T head() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        @Override
        public MyList<T> filter(Predicate<T> p) {
            return null;
        }
    }

    static class MyLinkedList<T> implements MyList<T> {

        private final T head;
        private final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public MyList<T> filter(Predicate<T> p) {
            return null;
        }
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public MyList<T> tail() {
            return tail.get(); // 위의 head와 달리 tail에서는 Supplier로 게으른 동작을 만들었다.
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
        }
    }

    static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n+1));
    }

    static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(
                numbers.head(),
                () -> primes(
                        numbers.tail()
                                .filter(n -> n % numbers.head() != 0)
                )
        );
    }

    static <T> void printAll(MyList<T> list) {
        while (!list.isEmpty()) {
            System.out.println(list.head());
            list = list.tail();
        }
    }

    static <T> void printAll2(MyList<T> list) {
        if (list.isEmpty()) {
            return;
        }
        System.out.println(list.head());
        printAll2(list.tail());
    }
}
