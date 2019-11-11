package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Character {
    private long idChar;
    private Actor actor;
    private Movie movie;
    private String charName;

    /**
     * Constructor of the character
     * @param idChar the id of the character
     * @param actor the actor that has played the character
     * @param movie the movie in which the character appears
     * @param charName the name of the character in the movie
     */
    public Character(long idChar, Actor actor, Movie movie, String charName){
            this.idChar = idChar;
            this.actor = actor;
            this.movie = movie;
            this.charName = charName;
    }

    /**
     * Getter of the character ID
     * @return the character ID
     */
    public long getIdChar() {
        return idChar;
    }

    /**
     * Getter of the actor
     * @return the actor that plays the character
     */
    public Actor getActor() {
        return actor;
    }

    /**
     * Getter of the movie
     * @return the movie in which the character appears
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Getter of the character name
     * @return the character name
     */
    public String getCharName() {
        return charName;
    }
}
