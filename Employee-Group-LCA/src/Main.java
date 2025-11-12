import Entities.Employee;
import Entities.Group;
import Entities.Node;
import Service.EmployeeManager;

import java.util.*;

/*
    g2
   g1  e3
 e1 e2
 */
public class Main {

    public static void main(String[] args){

        Employee e1 = new Employee("Alice","e1");
        Employee e2 = new Employee("Bob","e2");
        Employee e3 = new Employee("OtherName","e3");
        Group g1 = new Group(new ArrayList<Node>(Arrays.asList(e1,e2)),"g1");
        Group g2 = new Group(new ArrayList<Node>(Arrays.asList(g1,e3)),"g2");

        EmployeeManager mgr = new EmployeeManager(g2);
        Node lca = mgr.GetLCA(e2,e1);
        if(lca==null) {
            System.out.print("Either or both employees not present any group");
        }
        else{
            System.out.println("LCA is " + lca.getId());
        }
    }
}