package designpatterns.creational.singleton;

import java.lang.reflect.Constructor;

/**
 * Reflection can be used to destroy all the implemented singleton
 * implementation approaches. Let’s see this with an example class.
 */
public class ReflectionSingletonTest {
    public static void main(String[] args) {
        EagerInitializedSingleton eagerInitializedSingleton = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor: constructors) {
                //this code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(instanceTwo.hashCode());
        System.out.println(eagerInitializedSingleton.hashCode());
    }
}
