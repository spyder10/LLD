package Entities;

public class Pair {
    double amount;
    Person person;

    public Pair(double amount, Person person) {
        this.amount = amount;
        this.person = person;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
