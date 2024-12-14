package modern_java_in_action.ch13;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
