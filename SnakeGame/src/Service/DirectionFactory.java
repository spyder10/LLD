package Service;

import Entities.Coordinate;
import Enums.Direction;

public class DirectionFactory {
    public static Direction getDirection(char dir){
        return switch (dir) {
            case ('R') -> Direction.RIGHT;
            case ('U') -> Direction.UP;
            case ('L') -> Direction.LEFT;
            case ('D') -> Direction.DOWN;
            default -> Direction.UNKNOWN;
        };
    }
}
