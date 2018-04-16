### Factory Design Pattern ###
1. Factory design pattern is used when we have a super class with multiple sub-classes and based on input, we need to return one of the sub-class. 
2. This pattern take out the responsibility of instantiation of a class from client program to the factory class.
3. This pattern is also known as **Factory Method Design Pattern**.
4. Super class in factory design pattern can be an interface, abstract class or a normal java class.

#### Note ####
1. We can keep Factory class Singleton or we can keep the method that returns the subclass as static.

#### Factory Design Pattern Advantages ####
1. Factory design pattern provides approach to code for interface rather than implementation.
2. Factory pattern removes the instantiation of actual implementation classes from client code. Factory pattern makes our code more robust, less coupled and easy to extend. For example, we can easily change PC class implementation because client program is unaware of this.
3. Factory pattern provides abstraction between implementation and client classes through inheritance.