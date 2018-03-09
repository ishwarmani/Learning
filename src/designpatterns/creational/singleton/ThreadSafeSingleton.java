package designpatterns.creational.singleton;

/**
 * Above implementation works fine and provides thread-safety but it reduces the performance
 * because of cost associated with the synchronized method, although we need it only for the
 * first few threads who might create the separate instances.
 * To avoid this extra overhead every time, double checked locking principle is used.
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance() {
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    /**
     * The synchronized block is used inside the if condition with
     * an additional check to ensure that only one instance of singleton class is created.
     * @return instance
     */
    public static ThreadSafeSingleton getInstanceDoubleLocking(){
        if(instance == null){
            synchronized (ThreadSafeSingleton.class){
                instance = new ThreadSafeSingleton();
            }
        }
        return instance;
    }
}
