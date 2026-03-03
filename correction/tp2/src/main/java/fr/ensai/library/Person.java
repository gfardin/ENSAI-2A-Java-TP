package fr.ensai.library;

public class Person {

    // Attributes
    public String name;
    public int age;

    // Constructor
    public Person(String name) {
        this.name = name;
    }

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Methods
    public void birthday() {
        this.age += 1;
    }

    public String toString() {
        return this.getClass() + " " + this.name;
    }
}
