package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.ProcedureDao;
import model.entities.Procedure;
import view.Errors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcProcedureDao implements ProcedureDao {

    /* SQL */
    private static final String SELECT_FROM_PROCEDURES = "SELECT * FROM procedures";

    /* Fields */
    private static final String ID = "id";
    private static final String NAME = "name";

    private Connection connection;

    JdbcProcedureDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Procedure> find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Procedure> findAll() {
        List<Procedure> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PROCEDURES)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    private Procedure getEntityFromResultSet(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(rs.getInt(ID));
        procedure.setName(rs.getString(NAME));
        return procedure;
    }

    @Override
    public void create(Procedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Procedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
