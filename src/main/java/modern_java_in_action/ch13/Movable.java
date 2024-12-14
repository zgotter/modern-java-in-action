package modern_java_in_action.ch13;

public interface Movable {
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

    default void moveHorizontally(int distance) {
        setX(getX()+distance);
    }

    default void moveVertically(int distance) {
        setY(getY()+distance);
    }
}
