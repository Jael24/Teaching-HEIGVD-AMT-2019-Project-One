package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Clip;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClipManager implements ClipManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    private DataSource dataSource;

    public List<Clip> findAllClips(){
        List<Clip> clips = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clips;");
            readResult(clips, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clips;
    }

    public void createClip(String title){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO clips(title) VALUES (" + title + ");");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClip(long idClip, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE clips SET title = '" + newName + "' WHERE idClip =" + idClip + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClip(long idClip){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clips WHERE idClip =" + idClip + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Clip> findClip(String search){
        List<Clip> clips = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM clips WHERE title LIKE '%" + search + "%';");
            readResult(clips, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clips;
    }

    private void readResult(List<Clip> clips, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            String title = result.getString("title");
            long idClip = result.getLong("idClip");
            clips.add(new Clip(idClip, title));
        }
    }
}
