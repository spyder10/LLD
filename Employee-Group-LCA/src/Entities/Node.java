package Entities;

import java.util.UUID;

public class Node {
    private final String Id;

    public Node(String id) {
        this.Id = id;
    }

    public String getId() {
        return Id;
    }
}
