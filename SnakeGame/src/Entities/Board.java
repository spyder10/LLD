package Entities;

import java.util.*;

public class Board {
    int rows;
    int cols;
    Snake snake;
    Queue<Food> food;

    public Board(int rows, int cols, Snake snake, Queue<Food> food) {
        this.rows = rows;
        this.cols = cols;
        this.snake = snake;
        this.food = food;
    }

    public Snake getSnake() {
        return snake;
    }

    public boolean isBoardTerminating(int newHeadRow, int newHeadCol){
        return rows == newHeadRow || cols == newHeadCol || newHeadRow==-1 || newHeadCol==-1;
    }

    public boolean isSnakeCord(int newHeadRow, int newHeadCol){
        return snake.get_cords().contains(new Coordinate(newHeadRow, newHeadCol));
    }

    public Queue<Food> getFoods() {
        return food;
    }
    public void pollFood(){
        food.poll();
    }
}
