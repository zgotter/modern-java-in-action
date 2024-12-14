package modern_java_in_action.ch13;

public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
