import Entities.*;
import Enums.*;
import Service.*;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Queue<Food> foodItems = new LinkedList<>(
                List.of(
                        new Food(new Coordinate(0, 1), 2),
                        new Food(new Coordinate(0, 2), 5),
                        new Food(new Coordinate(0, 3), 4)
                )
        );
        Board board = new Board(5, 5, new Snake(), foodItems);
        Scanner scanner = new Scanner(System.in);
        GameService game = new GameService(board);
        while (!game.IsGameOver()) {
            System.out.println("Enter direction as R,L,U,D");
            char dir = scanner.next().charAt(0);
            Direction chosenDir = DirectionFactory.getDirection(dir);
            if(chosenDir == Direction.UNKNOWN){
                System.out.println("Invalid Input");
                continue;
            }
            game.move(chosenDir);
            game.printCurrGameState();
        }
        System.out.println("Game over!, your score is " + game.getScore());
    }
}