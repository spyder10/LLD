package Entities;
import java.util.*;

public class Group extends Node{
    private final List<Node> employeeList;

    public Group(List<Node> employeeList, String id) {
        super(id);
        this.employeeList = employeeList;
    }

    public List<Node> getEmployeeList() {
        return employeeList;
    }
}
