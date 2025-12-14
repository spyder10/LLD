package Entities;

import java.util.UUID;

public class Food {
    Coordinate foodCoord;
    int numPts;
    UUID id;

    public Food(Coordinate foodCoord, int numPts) {
        this.foodCoord = foodCoord;
        id = UUID.randomUUID();
        this.numPts = numPts;
    }

    public int getNumPts() {
        return numPts;
    }

    public void setNumPts(int numPts) {
        this.numPts = numPts;
    }

    public int getRow() {
        return foodCoord.getRow();
    }

    public void setRow(int row) {
        this.foodCoord.setRow(row);
    }

    public int getCol() {
        return foodCoord.getCol();
    }

    public void setCol(int col) {
        this.foodCoord.setCol(col);
    }
}
