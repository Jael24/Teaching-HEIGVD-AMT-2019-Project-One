package ch.heigvd.amt.projectone.model;

import lombok.Getter;

@Getter
public class Clip {
    private long clipId;
    private String title;

    public Clip(long clipId, String title) {
        this.clipId = clipId;
        this.title = title;
    }
}
