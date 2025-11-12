import Entities.Movie;
import Entities.Schedule;
import Service.MovieSchedulerService;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        Movie starWars = new Movie("StarWars", 2, "m1", 200);
        Movie gravity = new Movie("Gravity", 3, "m2", 300);
        Movie prestige = new Movie("prestige", 4, "m3", 400);
        var movieScheduleList = new ArrayList<Schedule>(
                Arrays.asList(
                        new Schedule(9, starWars),
                        new Schedule(12, gravity),
                        new Schedule(17, prestige),
                        new Schedule(21, gravity)
                ));

        var movieSchedulerService = new MovieSchedulerService(movieScheduleList,8,24);
        Movie newMovie = new Movie("partner", 2, "m4",600);
        var res = movieSchedulerService.CanSchedule(newMovie);
        if(res==null){
            System.out.println("No schedule available");
        }
        else{
            System.out.println("StartTime: "+ res.getStartTime());
        }

    }
}
