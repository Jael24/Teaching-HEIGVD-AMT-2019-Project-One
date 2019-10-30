package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Actor {
    private String fullname;
    private long actorId;   

    public Actor(long actorId, String fullname) {
        this.fullname = fullname;
        this.actorId = actorId;
    }
}
