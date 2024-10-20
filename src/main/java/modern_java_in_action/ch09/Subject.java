package modern_java_in_action.ch09;

public interface Subject {
    void registerObserver (Observer o);
    void notifyObservers (String tweet);
}
