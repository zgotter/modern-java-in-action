package modern_java_in_action.ch13;

public interface Rotatable {
    void setRotationAngle(int angleInDegrees);
    int getRotationAngle();
    default void rotateBy(int angleInDegrees) {
        setRotationAngle((getRotationAngle()+angleInDegrees) % 360);
    }
}
