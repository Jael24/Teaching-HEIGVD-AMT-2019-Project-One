package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Clip {
    private long idClip;
    private String title;

    public Clip(long idClip, String title) {
        this.idClip = idClip;
        this.title = title;
    }
}
