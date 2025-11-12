package Entities;

public class Movie {
    private String Name;
    private int duration;
    private String id;
    private int revenue;

    public Movie(String name, int duration, String id, int revenue) {
        Name = name;
        this.duration = duration;
        this.id = id;
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public String getName() {
        return Name;
    }

    public int getDuration() {
        return duration;
    }

    public String getId() {
        return id;
    }
}
