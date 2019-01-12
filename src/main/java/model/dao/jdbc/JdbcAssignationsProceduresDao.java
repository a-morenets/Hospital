package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.AssignationsProceduresDao;
import model.entities.AssignationsProcedures;
import model.entities.Procedure;
import org.apache.log4j.Logger;
import view.Errors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * JdbcAssignationsProceduresDao
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class JdbcAssignationsProceduresDao implements AssignationsProceduresDao {

    private static final Logger LOGGER = Logger.getLogger(JdbcAssignationsProceduresDao.class);

    /* SQL */
    private static final String SELECT_FROM_ASSIGNATIONS_PROCEDURES =
            "SELECT ap.id, diagnosis_history_id, procedure_id, num_days, name procedure_name\n" +
                    "FROM assignations_procedures ap JOIN procedures p\n" +
                    "ON ap.procedure_id = p.id\n" +
                    "WHERE ap.diagnosis_history_id = ?";
    private static final String INSERT_INTO_ASSIGNATIONS_PROCEDURES =
            "INSERT INTO assignations_procedures\n" +
                    "(diagnosis_history_id, procedure_id, num_days)\n" +
                    "VALUES(?, ?, ?)";

    /* Fields for assignations_procedures */
    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    private static final String NUM_DAYS = "num_days";

    /* Fields for procedures */
    private static final String PROCEDURE_ID = "procedure_id";
    private static final String PROCEDURE_NAME = "procedure_name";

    private Connection connection;

    JdbcAssignationsProceduresDao(Connection connection) {
        this.connection = connection;
    }

//    public void setConnection(Connection connection) {
//        this.connection = connection;
//    }

    public List<AssignationsProcedures> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationsProcedures> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_ASSIGNATIONS_PROCEDURES)) {
            query.setString(1, String.valueOf(diagnosisHistoryId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new AppException(Errors.SQL_ERROR, ex);
        }
        return result;
    }

    private AssignationsProcedures getEntityFromResultSet(ResultSet rs) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(rs.getInt(PROCEDURE_ID));
        procedure.setName(rs.getString(PROCEDURE_NAME));
        return new AssignationsProcedures.Builder()
                .setId(rs.getInt(ID))
                .setDiagnosisHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .setProcedure(procedure)
                .setNumDays(rs.getInt(NUM_DAYS))
                .build();
    }

    @Override
    public void create(AssignationsProcedures assignationsProcedures) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_ASSIGNATIONS_PROCEDURES, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(assignationsProcedures.getDiagnosisHistoryId()));
            query.setString(2, String.valueOf(assignationsProcedures.getProcedure().getId()));
            query.setString(3, String.valueOf(assignationsProcedures.getNumDays()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                assignationsProcedures.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
    }

    @Override
    public Optional<AssignationsProcedures> find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AssignationsProcedures> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(AssignationsProcedures assignationsProcedures) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
