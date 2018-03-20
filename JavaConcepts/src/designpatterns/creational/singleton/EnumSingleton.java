package designpatterns.creational.singleton;
/*
 Since enums are inherently serializable, we don't need to implement it with a serializable interface.
 The reflection problem is also not there. Therefore, it is 100% guaranteed that only one instance of
 the singleton is present within a JVM.
 One thing to remember here is, when serializing an enum, field variables are not getting serialized.
 For example, if we serialize and deserialize the EnumSingleton class, we will lose the value of the
 int value field.
 */
public enum EnumSingleton {

    INSTANCE;
    int value;

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public static void main(String[] args) {
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        System.out.println(EnumSingleton.INSTANCE.getClass());
        System.out.println(EnumSingleton.INSTANCE.hashCode());
        enumSingleton.setValue(10);
        System.out.println(enumSingleton.getValue());
        System.out.println(enumSingleton.INSTANCE.value);
    }
}
