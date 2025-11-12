package Entities;

public class Schedule {
    private int StartTime;
    private Movie movie;

    public Schedule(int startTime, Movie movie) {
        StartTime = startTime;
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getStartTime() {
        return StartTime;
    }
}
