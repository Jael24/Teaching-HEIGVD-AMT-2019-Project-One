package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Character {
    private long idChar;
    private Actor actor;
    private Movie movie;
    private String charName;

    public Character(long idChar, Actor actor, Movie movie, String charName){
            this.idChar = idChar;
            this.actor = actor;
            this.movie = movie;
            this.charName = charName;
    }

    public long getIdChar() {
        return idChar;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getCharName() {
        return charName;
    }
}
