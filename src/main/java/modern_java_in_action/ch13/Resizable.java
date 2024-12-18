package modern_java_in_action.ch13;

public interface Resizable extends Drawable {
    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);
    void setAbsoluteSize(int width, int height);
//    void setRelativeSize(int wFactor, int hFactor);
    default void setRelativeSize(int wFactor, int hFactor) {
        setAbsoluteSize(getWidth()/wFactor, getHeight()/hFactor);
    }
}
