package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Character {
    private String name;
    private long actorId;
    private long clipId;

    public Character(String name, long actorId, long clipId) {
        this.name = name;
        this.actorId = actorId;
        this.clipId = clipId;
    }
}
