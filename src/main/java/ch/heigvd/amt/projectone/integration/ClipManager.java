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

    public void createClip(String title){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO movie(title) VALUES (" + title + ");");
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClip(long idClip, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE movie SET title = '" + newName + "' WHERE idMovie =" + idClip + ";");
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
            ps.executeQuery();
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

    private void readResult(List<Movie> movies, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            String title = result.getString("title");
            long idClip = result.getLong("idMovie");
            movies.add(new Movie(idClip, title));
        }
    }
}
