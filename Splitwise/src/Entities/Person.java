package Entities;

import java.util.*;

public class Person {
    String Name;
    UUID id;

    public Person(String name) {
        Name = name;
        id = UUID.randomUUID();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public UUID getId() {
        return id;
    }
}
