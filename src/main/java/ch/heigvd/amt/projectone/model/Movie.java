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
}
