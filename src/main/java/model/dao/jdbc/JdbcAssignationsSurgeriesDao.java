package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.AssignationsSurgeriesDao;
import model.entities.AssignationsSurgeries;
import model.entities.Surgery;
import view.Errors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcAssignationsSurgeriesDao
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class JdbcAssignationsSurgeriesDao implements AssignationsSurgeriesDao {

    /* SQL */
    private static final String SELECT_FROM_ASSIGNATIONS_SURGERIES =
            "SELECT asur.id, diagnosis_history_id, surgery_id, name surgery_name\n" +
                    "FROM assignations_surgeries asur JOIN surgeries s\n" +
                    "ON asur.surgery_id = s.id\n" +
                    "WHERE asur.diagnosis_history_id = ?";
    private static final String INSERT_INTO_ASSIGNATIONS_SURGERIES =
            "INSERT INTO assignations_surgeries\n" +
                    "(diagnosis_history_id, surgery_id)\n" +
                    "VALUES(?, ?)";

    /* Fields for assignations_surgeries */
    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";

    /* Fields for surgeries */
    private static final String SURGERY_ID = "surgery_id";
    private static final String NASURGERY_NAMEE = "surgery_name";

    private Connection connection;

    JdbcAssignationsSurgeriesDao(Connection connection) {
        this.connection = connection;
    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public List<AssignationsSurgeries> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationsSurgeries> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_ASSIGNATIONS_SURGERIES)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    private AssignationsSurgeries getEntityFromResultSet(ResultSet rs) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setId(rs.getInt(SURGERY_ID));
        surgery.setName(rs.getString(NASURGERY_NAMEE));
        return new AssignationsSurgeries.Builder()
                .setId(rs.getInt(ID))
                .setDiagnosisHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .setSurgery(surgery)
                .build();
    }

    @Override
    public void create(AssignationsSurgeries assignationsSurgeries) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_ASSIGNATIONS_SURGERIES, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(assignationsSurgeries.getDiagnosisHistoryId()));
            query.setString(2, String.valueOf(assignationsSurgeries.getSurgery().getId()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                assignationsSurgeries.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
    }

    @Override
    public Optional<AssignationsSurgeries> find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AssignationsSurgeries> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(AssignationsSurgeries assignationsSurgeries) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
