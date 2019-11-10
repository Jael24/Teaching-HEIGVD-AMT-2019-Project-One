package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Movie {
    private long idMovie;
    private String title;

    public Movie(long idMovie, String title) {
        this.idMovie = idMovie;
        this.title = title;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public String getTitle() {
        return title;
    }
}
