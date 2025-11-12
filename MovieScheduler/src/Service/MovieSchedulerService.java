package Service;

import Entities.Movie;
import Entities.Schedule;

import java.util.*;

public class MovieSchedulerService {
    private List<Schedule> movieSchedules;
    private int cinemaOpenTime;
    private int cinemaCloseTime;
    public MovieSchedulerService(List<Schedule> movieSchedules, int cinemaOpenTime, int cinemaCloseTime) {
        this.movieSchedules = movieSchedules;
        this.cinemaCloseTime = cinemaCloseTime;
        this.cinemaOpenTime = cinemaOpenTime;
    }


    public Schedule CanSchedule(Movie movie){
        if(movie.getDuration() <= movieSchedules.get(0).getStartTime() - cinemaOpenTime){
            return new Schedule(cinemaOpenTime, movie);
        }

        var lastMovie = movieSchedules.getLast();
        int lastMovieEndTime = lastMovie.getStartTime() + lastMovie.getMovie().getDuration();
        if(movie.getDuration() <= cinemaCloseTime - lastMovieEndTime){
            return new Schedule(lastMovieEndTime, movie);
        }

        for(int i=0; i < movieSchedules.size()-1;i++){
            int startTime = movieSchedules.get(i).getStartTime();
            int duration = movieSchedules.get(i).getMovie().getDuration();
            int endTime = startTime + duration;
            int startTimeOfNextMovie = movieSchedules.get(i+1).getStartTime();

            if(movie.getDuration() <= startTimeOfNextMovie-endTime){
                return new Schedule(endTime,movie);
            }
        }
        return null;
    }
}
