package ch.heigvd.amt.projectone.model;

public class Movie {
    private long idMovie;
    private String movieName;

    public long getIdMovie() {
        return idMovie;
    }

    public String getMovieName() {
        return movieName;
    }

    public Movie(long idMovie, String movieName){
        this.idMovie = idMovie;
        this.movieName = movieName;
    }
}
