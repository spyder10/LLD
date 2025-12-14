import Entities.Group;
import Entities.Person;
import Entities.Transaction;
import Service.ExpenseManager;

import java.util.*;

public class Main{
    public static void main(String[] args){
        Person p1 = new Person("Vibhor");
        Person p2 = new Person("Saumya");
        Person p3 = new Person("Rachit");
        Person p4 = new Person("Khushboo");

        Set<Person> persons = new HashSet<>(List.of(p1,p2,p3,p4));
        Group grp = new Group(persons);
        HashMap<UUID, Group> grpMap= new HashMap<>();
        grpMap.put(grp.getId(), grp);

        ExpenseManager exM = new ExpenseManager(grpMap);

        exM.AddExpense(grp.getId(), p1, List.of(p1,p2,p3,p4), 500);
        exM.AddExpense(grp.getId(), p2, List.of(p1,p2,p3,p4), 200);
        exM.AddExpense(grp.getId(), p3, List.of(p1,p2,p3,p4), 100);
        exM.AddExpense(grp.getId(), p4, List.of(p1,p2,p3,p4), 1000);


        List<Transaction> txns = exM.settle(grp.getId());
        for(Transaction t : txns){
            System.out.println("From:" + t.getFrom().getName() + " To:" + t.getTo().getName() + " Rupees: " + t.getAmount());
        }
    }
}