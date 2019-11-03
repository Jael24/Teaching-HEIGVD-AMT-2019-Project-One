package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Clip;

import java.util.List;

public interface ClipManagerLocal {
    List<Clip> findAllClips();
    void createClip(String title);
    void updateClip(long idClip, String newName);
    void deleteClip(long idClip);
    List<Clip> findClip(String search);
}
