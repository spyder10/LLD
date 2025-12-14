package Entities;

import java.util.*;

public class Expense {
    double amount;
    Person spender;
    List<Person> spentFor;
    UUID id;

    public Expense(double amount, Person spender, List<Person> spentFor) {
        this.amount = amount;
        this.spender = spender;
        this.spentFor = spentFor;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Person getSpender() {
        return spender;
    }

    public void setSpender(Person spender) {
        this.spender = spender;
    }

    public List<Person> getSpentFor() {
        return spentFor;
    }

    public void setSpentFor(List<Person> spentFor) {
        this.spentFor = spentFor;
    }
}
