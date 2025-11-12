package Service;

import Entities.Employee;
import Entities.Group;
import Entities.Node;

public class EmployeeManager {
    private Node root;

    public EmployeeManager(Node root) {
        this.root = root;
    }

    public Node GetLCA(Employee e1, Employee e2){
        return GetLCAHelper(root, e1, e2);
    }

    private Node GetLCAHelper(Node node, Employee e1, Employee e2){
        if(node instanceof Employee){
            if(node==e1 || node==e2){
                return node;
            }
            else{
                return null;
            }
        }

        Group currGroup = (Group)node;
        int cnt=0;
        Node plca=null;
        for(int i=0;i<currGroup.getEmployeeList().size();i++) {
            Node lca = GetLCAHelper(currGroup.getEmployeeList().get(i), e1, e2);
            if (lca == e1 || lca==e2) {
                plca = lca;
                cnt++;
                if(cnt==2){
                    return node;
                }
            }
            else if (lca != null) {
                return lca;
            }
        }
        return plca;
    }
}
