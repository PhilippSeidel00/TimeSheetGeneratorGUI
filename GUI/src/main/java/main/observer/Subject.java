package main.observer;

/**
 * Subject in the Observer Pattern
 */
public interface Subject {

    /**
     * subscribe {@link Observer} to this subject
     * @param observer the observer that is to be subscribed
     */
    void subscribe(Observer observer);
}
