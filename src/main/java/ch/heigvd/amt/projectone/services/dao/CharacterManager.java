package ch.heigvd.amt.projectone.services.dao;

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
            ResultSet result = ps.executeQuery();
            while (result.next()){
                long idMovie = result.getLong("idMovie");
                long idActor = result.getLong("idActor");
                String charName = result.getString("charName");
                chars.add(new Character(idActor, idMovie, charName));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

}
