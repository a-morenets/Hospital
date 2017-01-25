package model.dao.jdbc;

import model.dao.AssignationProcedureDao;
import model.entities.AssignationProcedure;
import model.entities.Procedure;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class JdbcAssignationProcedureDao implements AssignationProcedureDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcAssignationProcedureDao.class);

    /* SELECT */
    private static final String SELECT_FROM_ASSIGNATIONS_PROCEDURES =
            "SELECT ap.id, diagnosis_history_id, procedure_id, num_days, p.id id_procedure, name\n" +
                    "FROM assignations_procedures ap JOIN procedures p\n" +
                    "ON ap.procedure_id = p.id\n" +
                    "WHERE ap.diagnosis_history_id = ?";

    /* Fields for assignations_procedures */
    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    public static final String PROCEDURE_ID = "procedure_id";
    public static final String NUM_DAYS = "num_days";

    /* Fields for procedures */
    public static final String ID_PROCEDURE = "id_procedure";
    public static final String NAME = "name";

    private Connection connection;

    JdbcAssignationProcedureDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<AssignationProcedure> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationProcedure> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_ASSIGNATIONS_PROCEDURES)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getAssignationProcedureFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private AssignationProcedure getAssignationProcedureFromResultSet(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(resultSet.getInt(ID_PROCEDURE));
        procedure.setName(resultSet.getString(NAME));
        return new AssignationProcedure.Builder()
                .setId(resultSet.getInt(ID))
                .setDiagnosisHistoryId(resultSet.getInt(DIAGNOSIS_HISTORY_ID))
                .setProcedure(procedure)
                .setNumDays(resultSet.getInt(NUM_DAYS))
                .build();
    }

    @Override
    public AssignationProcedure find(int id) {
        return null;
    }

    @Override
    public List<AssignationProcedure> findAll() {
        return null;
    }

    @Override
    public void create(AssignationProcedure assignationProcedure) {

    }

    @Override
    public void update(AssignationProcedure assignationProcedure) {

    }

    @Override
    public void delete(int id) {

    }
}
