package Service;

import Entities.Board;
import Entities.Coordinate;
import Entities.Food;
import Enums.Direction;

public class GameService {

    private Board board;
    int score;
    boolean isGameOver;

    public GameService(Board board) {
        this.board = board;
        this.score=0;
        isGameOver = false;
    }

    public void move(Direction direction){
        Coordinate snakeHead = board.getSnake().getSnakeHead();
        int currHeadRow = snakeHead.getRow();
        int currHeadCol = snakeHead.getCol();

        int newHeadRow = currHeadRow + direction.getRowDiff();
        int newHeadCol = currHeadCol + direction.getColDiff();

        if(board.isBoardTerminating(newHeadRow, newHeadCol) || board.isSnakeCord(newHeadRow, newHeadCol)){
            isGameOver = true;
            return;
        }

        board.getSnake().addNewHead(newHeadRow, newHeadCol);
        if (!board.getFoods().isEmpty()) {
            Food currFood = board.getFoods().peek();
            if (newHeadRow == currFood.getRow() && newHeadCol == currFood.getCol()) {
                board.pollFood();
                score += currFood.getNumPts();
                return;
            }
        }
        board.getSnake().removeTail();
    }

    public boolean IsGameOver(){
        return isGameOver;
    }

    public int getScore(){
        return score;
    }

    public void printCurrGameState(){
        System.out.println("SnakeLen: " + board.getSnake().getLength());
        System.out.println("Your score:"+ getScore());
    }
}
