package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.DiagnosisDao;
import model.entities.Diagnosis;
import view.Errors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcDiagnosisDao
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisDao implements DiagnosisDao {

    /* SQL */
    private static final String SELECT_FROM_DIAGNOSIS_BY_ID = "SELECT * FROM diagnosis WHERE id = ?";
    private static final String SELECT_FROM_DIAGNOSIS = "SELECT * FROM diagnosis";

    /* Fields */
    private static final String ID = "id";
    private static final String NAME = "name";

    private Connection connection;

    JdbcDiagnosisDao(Connection connection) {
        this.connection = connection;
    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    @Override
    public Optional<Diagnosis> find(int id) {
        Optional<Diagnosis> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Diagnosis diagnosis = getEntityFromResultSet(resultSet);
                result = Optional.of(diagnosis);
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    @Override
    public List<Diagnosis> findAll() {
        List<Diagnosis> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DIAGNOSIS)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    private Diagnosis getEntityFromResultSet(ResultSet rs) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(rs.getInt(ID));
        diagnosis.setName(rs.getString(NAME));
        return diagnosis;
    }

    @Override
    public void create(Diagnosis diagnosis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Diagnosis diagnosis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
