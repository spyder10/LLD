package Service;

import Entities.*;
import java.util.*;

public class ExpenseManager {
    HashMap<UUID, Group> groups;

    public ExpenseManager(HashMap<UUID, Group> groups) {
        this.groups = groups;
    }

    public void AddExpense(UUID groupId, Person spender, List<Person> spentFor, double amount){
        Expense newEx = new Expense(amount, spender, spentFor);
        Group g = groups.computeIfAbsent(groupId, (id) -> {
           HashSet<Person> newGroup = new HashSet<>(spentFor);
           newGroup.add(spender);
           return new Group(newGroup);
        });

        g.getExpenses().add(newEx);

        HashMap<Person, Double> exMap = g.getPersonsNetOwe();
        exMap.put(spender, exMap.get(spender) - amount);

        for(Person p : spentFor){
            exMap.put(p, exMap.get(p) + (double)amount/spentFor.size());
        }
    }

    public List<Transaction> settle(UUID groupId){
        Group g = groups.get(groupId);
        HashMap<Person, Double> exMap = g.getPersonsNetOwe();

        List<Transaction> txns = new ArrayList<>();
        SortedSet<Pair> exSet = new TreeSet<>(Comparator.comparingDouble(Pair :: getAmount).
                thenComparing(p -> p.getPerson().getId()));

        for(Map.Entry<Person, Double> e : exMap.entrySet()){
            Pair p = new Pair(e.getValue(), e.getKey());
            exSet.add(p);
        }

        while(exSet.size()>1){
            Pair firstPair = exSet.getFirst();
            Pair lastPair = exSet.getLast();

            double firstAmount = firstPair.getAmount();
            double lastAmount = lastPair.getAmount();

            exSet.remove(firstPair);
            exSet.remove(lastPair);

            if(Math.abs(firstAmount) > Math.abs(lastAmount)){
                txns.add(new Transaction(Math.abs(lastAmount), lastPair.getPerson(), firstPair.getPerson()));
                exSet.add(new Pair(firstAmount + lastAmount, firstPair.getPerson()));
            }
            else if (Math.abs(firstAmount) < Math.abs(lastAmount)){
                txns.add(new Transaction(Math.abs(firstAmount), lastPair.getPerson(), firstPair.getPerson()));
                exSet.add(new Pair(firstAmount+lastAmount, lastPair.getPerson()));
            }
            else{
                txns.add(new Transaction(lastAmount, lastPair.getPerson(), firstPair.getPerson()));
            }
        }
        return txns;
    }
}
