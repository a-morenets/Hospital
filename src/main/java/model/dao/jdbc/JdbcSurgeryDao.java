package model.dao.jdbc;

import model.dao.SurgeryDao;
import model.entities.Surgery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcSurgeryDao implements SurgeryDao {

    /* SELECT */
    private static final String SELECT_FROM_SURGERIES = "SELECT * FROM surgeries";

    /* Fields */
    private static final String ID = "id";
    public static final String NAME = "name";

    private Connection connection;

    JdbcSurgeryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Surgery find(int id) {
        return null;
    }

    @Override
    public List<Surgery> findAll() {
        List<Surgery> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_SURGERIES)) {

            while (resultSet.next()) {
                result.add(getSurgeryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Surgery getSurgeryFromResultSet(ResultSet resultSet) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setId(resultSet.getInt(ID));
        surgery.setName(resultSet.getString(NAME));
        return surgery;
    }

    @Override
    public void create(Surgery surgery) {

    }

    @Override
    public void update(Surgery surgery) {

    }

    @Override
    public void delete(int id) {

    }
}
