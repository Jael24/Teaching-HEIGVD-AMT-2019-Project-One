package ch.heigvd.amt.projectone.integration;

import ch.heigvd.amt.projectone.model.Character;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterManager implements CharacterManagerLocal{
    @Resource(lookup = "jdbc/cinema")
    private DataSource dataSource;

    public List<Character> findAllCharacters(){
        List<Character> chars = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM chars");
            readResult(chars, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

    public void createCharacter(String charName, long idActor, long idMovie){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO chars(idActor, idMovie, charName) VALUES (" + idActor + ", "+ idMovie + ", "+ charName + ");");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCharacter(long idActor, long idMovie, String newName){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE chars SET charName = '" + newName + "' WHERE idActor =" + idActor + "AND idMovie =" + idMovie + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCharacter(long idActor, long idMovie){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM chars WHERE idActor =" + idActor + "AND idMovie =" + idMovie + ";");
            ps.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Character> findCharacter(String search){
        List<Character> chars = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM chars WHERE charName LIKE '" + search + "';");
            readResult(chars, ps);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

    private void readResult(List<Character> chars, PreparedStatement ps) throws SQLException {
        ResultSet result = ps.executeQuery();
        while (result.next()){
            long idMovie = result.getLong("idMovie");
            long idActor = result.getLong("idActor");
            String charName = result.getString("charName");
            chars.add(new Character(idActor, idMovie, charName));
        }
    }

}
