package designpatterns.creational.singleton;

/**
 * This implementation works fine incase of single threaded environment but when it comes to multithreaded systems,
 * it can cause issues if multiple threads are inside the if loop at the same time. It will destroy the singleton pattern and both threads
 * will get the different instances of singleton class
 */
public class LazyInitializationSingleton {

    private static LazyInitializationSingleton instance;

    private LazyInitializationSingleton(){}

    public static LazyInitializationSingleton getInstance(){
        if(instance == null){
            instance = new LazyInitializationSingleton();
        }
        return instance;
    }
}
