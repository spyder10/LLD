package Entities;

import java.util.*;

public class Group {
    HashMap<Person, Double> personsNetOwe;
    List<Expense> expenses;
    UUID id;

    public Group(Set<Person> persons) {
        personsNetOwe = new HashMap<>();
        expenses = new ArrayList<>();
        for(Person p : persons){
            personsNetOwe.put(p, 0.0);
        }
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public HashMap<Person, Double> getPersonsNetOwe() {
        return personsNetOwe;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
