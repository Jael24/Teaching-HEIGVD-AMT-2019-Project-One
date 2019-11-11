package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Movie {
    private long idMovie;
    private String title;

    /**
     * Constructor of a movie
     * @param idMovie the id of the movie
     * @param title the title of the movie
     */
    public Movie(long idMovie, String title) {
        this.idMovie = idMovie;
        this.title = title;
    }

    /**
     * Getter of the id of the movie
     * @return the id of the movie
     */
    public long getIdMovie() {
        return idMovie;
    }

    /**
     * Getter of the title
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }
}
