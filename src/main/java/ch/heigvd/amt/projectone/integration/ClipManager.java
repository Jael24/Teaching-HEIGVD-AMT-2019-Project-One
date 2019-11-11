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

@Stateless
public class ClipManager implements ClipManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    DataSource dataSource;

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
