package Entities;

public class Bucket {
    private int currSize;
    private final int maxCapacity;
    private long LastRefilledTime;

    public Bucket(long lastRefilledTime) {
        this.currSize = 3;
        this.maxCapacity = 3;
        LastRefilledTime = lastRefilledTime;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public long getLastRefilledTime() {
        return LastRefilledTime;
    }

    public void setLastRefilledTime(long lastRefilledTime) {
        LastRefilledTime = lastRefilledTime;
    }

    public int getCurrSize() {
        return currSize;
    }

    public void setCurrSize(int currSize) {
        this.currSize = currSize;
    }
}
