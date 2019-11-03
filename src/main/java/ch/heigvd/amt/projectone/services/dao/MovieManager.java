package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Actor;
import ch.heigvd.amt.projectone.model.Movie;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    @Resource(lookup = "jdbc/sakila")
    private DataSource dataSource;

    public List<Movie> findAllMovies(){
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movies;");
            readResult(movies, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public void createMovie(String movieName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO movies(movieName) VALUES (" + movieName + ");");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(long idMovie, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE movies SET FullName = '" + newName + "' WHERE idMovie =" + idMovie + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(long idMovie){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM movies WHERE idMovie =" + idMovie + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> findMovie(String search){
        List<Movie> movies = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM movies WHERE movieName LIKE '" + search + "';");
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
            String movieName = result.getString("movieName");
            long idMovie = result.getLong("idMovie");
            movies.add(new Movie(idMovie, movieName));
        }
    }
}
