package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Actor {
    private String fullname;
    private long pID;

    public Actor(long pID, String fullname) {
        this.fullname = fullname;
        this.pID = pID;
    }
}
