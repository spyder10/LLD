package Entities;

public class Employee extends Node {
    private String Name;

    public Employee(String name, String id) {
        super(id);
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
