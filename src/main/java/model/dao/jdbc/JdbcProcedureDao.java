package model.dao.jdbc;

import model.dao.ProcedureDao;
import model.entities.Procedure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcProcedureDao implements ProcedureDao {

    /* SELECT */
    private static final String SELECT_FROM_PROCEDURES = "SELECT * FROM procedures";

    /* Fields */
    private static final String ID = "id";
    public static final String NAME = "name";

    private Connection connection;

    JdbcProcedureDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Procedure find(int id) {
        return null;
    }

    @Override
    public List<Procedure> findAll() {
        List<Procedure> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PROCEDURES)) {

            while (resultSet.next()) {
                result.add(getProcedureFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Procedure getProcedureFromResultSet(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(resultSet.getInt(ID));
        procedure.setName(resultSet.getString(NAME));
        return procedure;
    }

    @Override
    public void create(Procedure procedure) {

    }

    @Override
    public void update(Procedure procedure) {

    }

    @Override
    public void delete(int id) {

    }
}
