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
 * JdbcSurgeryDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcSurgeryDao implements SurgeryDao {

    /* SQL */
    private static final String SELECT_FROM_SURGERIES = "SELECT * FROM surgeries";

    /* Fields */
    private static final String ID = "id";
    private static final String NAME = "name";

    private Connection connection;

    JdbcSurgeryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Surgery find(int id) {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Surgery surgery) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
