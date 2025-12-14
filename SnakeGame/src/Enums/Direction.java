package Enums;

public enum Direction {
    LEFT(0,-1),RIGHT (0,1), UP (-1,0), DOWN(1,0), UNKNOWN(-1,-1);

    private final int rowDiff;
    private final int colDiff;

    Direction(int rowDiff, int colDiff) {
        this.rowDiff = rowDiff;
        this.colDiff = colDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }

    public int getColDiff() {
        return colDiff;
    }
}
