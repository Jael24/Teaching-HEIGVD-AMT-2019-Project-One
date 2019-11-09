package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Movie;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ClipManagerLocal {
    List<Movie> findAllClips();
    Movie createClip(String title);
    void updateClip(long idClip, String newName);
    void deleteClip(long idClip);
    List<Movie> findClip(String search);
    List<Movie> findClipsWhereActorHasPlayed(long actorId);
    Movie findClipById(long search);
    long findMaxId();
}
