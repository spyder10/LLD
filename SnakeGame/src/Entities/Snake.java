package Entities;

import java.util.*;

public class Snake {
    LinkedList<Coordinate> _snake;
    HashSet<Coordinate> _cords;
    int length;

    public Snake() {
        _snake = new LinkedList<>();
        _cords = new HashSet<>();
        length=1;
        _snake.add(new Coordinate(0,0));
        _cords.add(new Coordinate(0,0));
    }

    public HashSet<Coordinate> get_cords() {
        return _cords;
    }

    public void incrementSnakeLength() {
        length++;
    };

    public void decrementSnakeLength(){
        length--;
    }

    public int getLength(){
        return length;
    }
    public Coordinate getSnakeHead() {
        return _snake.getLast();
    }

    public Coordinate GetSnakeTail(){
        return _snake.getFirst();
    }

    public void addNewHead(int newHeadRow, int newHeadCol){
        _snake.addLast(new Coordinate(newHeadRow, newHeadCol));
        _cords.add(new Coordinate(newHeadRow, newHeadCol));
        incrementSnakeLength();
    }

    public void removeTail(){
        _cords.remove(_snake.getFirst());
        _snake.removeFirst();
        decrementSnakeLength();
    }
}
