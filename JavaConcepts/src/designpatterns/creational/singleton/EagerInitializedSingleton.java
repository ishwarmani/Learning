package designpatterns.creational.singleton;

import java.util.HashMap;

public class EagerInitializedSingleton {

    /**
     * Private static variable of the same class that is the only instance of the class
     */
    private static final EagerInitializedSingleton SINGLETON_INSTANCE = new EagerInitializedSingleton();

    /**
     * Private constructor to restrict instantiation of the class from other classes.
     */
    private EagerInitializedSingleton(){ }

    /**
     * Public static method that returns the instance of the class,
     * this is the global access point for outer world to get the instance of the singleton class.
     * @return SINGLETON_INSTANCE
     */
    public static EagerInitializedSingleton getInstance(){
        return SINGLETON_INSTANCE;
    }
}
