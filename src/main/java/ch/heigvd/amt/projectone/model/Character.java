package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Character {
    private long idActor;
    private long idMovie;
    private String charName;

    public Character(long idActor, long idMovie, String charName){
            this.idActor = idActor;
            this.idMovie = idMovie;
            this.charName = charName;
    }

    public long getIdActor() {
        return idActor;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public String getName() {
        return charName;
    }
}
