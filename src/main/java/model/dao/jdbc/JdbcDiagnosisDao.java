package model.dao.jdbc;

import model.dao.DiagnosisDao;
import model.entities.Diagnosis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisDao implements DiagnosisDao {

    /* SELECT */
    private static final String SELECT_FROM_DIAGNOSIS_BY_ID = "SELECT * FROM diagnosis WHERE id = ?";

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

    private Diagnosis getDiagnosisFromResultSet(ResultSet resultSet) throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(resultSet.getInt(ID));
        diagnosis.setName(resultSet.getString(NAME));
        return diagnosis;
    }

    @Override
    public List<Diagnosis> findAll() {
        return null;
    }

    @Override
    public void create(Diagnosis diagnosis) {

    }

    @Override
    public void update(Diagnosis diagnosis) {

    }

    @Override
    public void delete(int id) {

    }
}
