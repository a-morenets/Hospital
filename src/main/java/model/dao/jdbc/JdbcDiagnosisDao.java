package model.dao.jdbc;

import model.dao.DiagnosisDao;
import model.entities.Diagnosis;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Diagnosis find(int id) {
        Diagnosis diagnosis = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                diagnosis = getDiagnosisFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> findAll() {
        List<Diagnosis> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DIAGNOSIS)) {

            while (resultSet.next()) {
                result.add(getDiagnosisFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Diagnosis getDiagnosisFromResultSet(ResultSet resultSet) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(resultSet.getInt(ID));
        diagnosis.setName(resultSet.getString(NAME));
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
