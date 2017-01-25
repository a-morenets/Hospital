package model.dao.jdbc;

import model.dao.AssignationSurgeryDao;
import model.entities.AssignationSurgery;
import model.entities.Surgery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class JdbcAssignationSurgeryDao implements AssignationSurgeryDao {

    /* SELECT */
    private static final String SELECT_FROM_ASSIGNATIONS_SURGERIES =
            "SELECT asur.id, diagnosis_history_id, surgery_id, s.id id_surgery, name\n" +
                    "FROM assignations_surgeries asur JOIN surgeries s\n" +
                    "ON asur.surgery_id = s.id\n" +
                    "WHERE asur.diagnosis_history_id = ?";

    /* Fields for assignations_surgeries */
    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    public static final String PROCEDURE_ID = "surgery_id";

    /* Fields for surgeries */
    public static final String ID_SURGERY = "id_surgery";
    public static final String NAME = "name";

    private Connection connection;

    JdbcAssignationSurgeryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<AssignationSurgery> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationSurgery> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_ASSIGNATIONS_SURGERIES)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getAssignationSurgeryFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private AssignationSurgery getAssignationSurgeryFromResultSet(ResultSet resultSet) throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setId(resultSet.getInt(ID_SURGERY));
        surgery.setName(resultSet.getString(NAME));
        return new AssignationSurgery.Builder()
                .setId(resultSet.getInt(ID))
                .setDiagnosisHistoryId(resultSet.getInt(DIAGNOSIS_HISTORY_ID))
                .setSurgery(surgery)
                .build();
    }

    @Override
    public AssignationSurgery find(int id) {
        return null;
    }

    @Override
    public List<AssignationSurgery> findAll() {
        return null;
    }

    @Override
    public void create(AssignationSurgery assignationSurgery) {

    }

    @Override
    public void update(AssignationSurgery assignationSurgery) {

    }

    @Override
    public void delete(int id) {

    }
}
