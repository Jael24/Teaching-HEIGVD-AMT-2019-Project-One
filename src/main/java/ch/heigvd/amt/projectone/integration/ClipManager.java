package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Movie;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to represent clip's DAOs
 * @author Guillaume Vetter & Jael Dubey
 */
@Stateless
public class ClipManager implements ClipManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    DataSource dataSource;

    /**
     * Method used to find the top 10 clips of the db
     * @return the first 10 movies of the db
     */
    @Override
    public List<Movie> findAllClips(){
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movie LIMIT 10;");
            readResult(movies, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Method used to retrieve a list of Movie in which a given Actor has played
     * @param actorId the given actor
     * @param start the starting index
     * @param length the length of the final result
     * @return a list of Movie starting at index "start" of length "length"
     */
    @Override
    public List<Movie> findClipsWhereActorHasPlayed(long actorId, int start, long length) {
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `movie` INNER JOIN `character` ON `movie`.idMovie = `character`.idMovie WHERE `character`.idActor = " + actorId +" LIMIT " + start + "," + length + ";");
            readResult(movies, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Method used to create a Movie, given its title
     * @param title the title of our movie
     * @return the Movie newly created
     */
    @Override
    public Movie createClip(String title){
        long newId = 0;
        try {
            newId = findMaxId()+1;
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO movie(idMovie, title) VALUES (?, ?);");
            ps.setLong(1, newId);
            ps.setString(2, title);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Movie(newId, title);
    }

    /**
     * Method used to update the name of a given clip ID.
     * @param idClip the id of the clip we want ot update the name.
     * @param newName the new name of the movie.
     */
    @Override
    public void updateClip(long idClip, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE movie SET title = ? WHERE idMovie =" + idClip + ";");
            ps.setString(1, newName);
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methos used to delete a clip given its ID
     * @param idClip the ID of the clip we want to delete.
     */
    @Override
    public void deleteClip(long idClip){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM movie WHERE idMovie =" + idClip + ";");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to find a clip using a string.
     * @param search the string
     * @return a List of movie that contains the string in its name.
     */
    @Override
    public List<Movie> findClip(String search){
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movie WHERE title LIKE '%" + search + "%';");
            readResult(movies, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * Method used to find a clip using an ID
     * @param search the ID of the clip we want to find
     * @return the movie that has the given ID, null otherwise.
     */
    @Override
    public Movie findClipById(long search){
        Movie movie = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movie WHERE idMovie = " + search + ";");
            ResultSet result = ps.executeQuery();
            result.next();
            movie = new Movie(result.getLong("idMovie"), result.getString("title"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    /**
     * Method used to find the maximum ID of the movie table
     * @return the maximum ID of the movie table.
     */
    @Override
    public long findMaxId(){
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT MAX(idMovie) AS maxId FROM movie;");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("maxId");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * Method used to find whether or not the given actor has played in the given movie.
     * @param idActor the given actor ID
     * @param idMovie the give movie ID
     * @return
     */
    @Override
    public boolean containsMovieId(long idActor, long idMovie) {
        List<Movie> movieList = findClipsWhereActorHasPlayed(idActor, 0, countAllClips());
        for (Movie movie : movieList) {
            if (movie.getIdMovie() == idMovie) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to count the number of movie for a given Actor
     * @param idActor the given actor
     * @return the number of movie played
     */
    @Override
    public long countClips(long idActor) {
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS countClips FROM movie INNER JOIN `character` ON `movie`.idMovie = `character`.idMovie WHERE `character`.idActor = " + idActor + ";");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("countClips");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * Method used to count every movie
     * @return the total number of movies
     */
    @Override
    public long countAllClips() {
        long returnValue = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS countClips FROM movie");
            ResultSet result = ps.executeQuery();
            result.next();
            returnValue = result.getLong("countClips");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    /**
     * Method used to display the first 100'000 Movie of the table.
     * @return a list of every retrieved movie.
     */
    @Override
    public List<Movie> findLotsClips() {
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movie LIMIT 100000;");
            readResult(movies, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private void readResult(List<Movie> movies, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            String title = result.getString("title");
            long idClip = result.getLong("idMovie");
            movies.add(new Movie(idClip, title));
        }
    }
}
