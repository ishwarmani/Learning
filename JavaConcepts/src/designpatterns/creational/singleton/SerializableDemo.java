package designpatterns.creational.singleton;

import java.io.*;

public class SerializableDemo{
    public static void main(String[] args) {
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstanceDoubleLocking();
        singleton.setValue(10);
        try{
            FileOutputStream fileOut = new FileOutputStream("out.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOut);
            outputStream.writeObject(singleton);
            outputStream.close();
            fileOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        singleton.setValue(20);

        ThreadSafeSingleton singleton2 = null;
        try{
            FileInputStream fileIn = new FileInputStream("out.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileIn);
            singleton2 = (ThreadSafeSingleton)inputStream.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException ce){
            ce.printStackTrace();
        }
        if(singleton == singleton2){
            System.out.println("Two objects are same.");
        }else{
            System.out.println("Two objects are not same.");
        }

        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
        System.out.println(singleton.hashCode());
        System.out.println(singleton2.hashCode());

    }
}
