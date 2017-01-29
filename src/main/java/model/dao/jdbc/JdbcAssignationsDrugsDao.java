package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.AssignationsDrugsDao;
import model.entities.AssignationsDrugs;
import model.entities.Drug;
import view.Errors;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcAssignationsDrugsDao
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class JdbcAssignationsDrugsDao implements AssignationsDrugsDao {

    /* SQL */
    private static final String SELECT_FROM_ASSIGNATIONS_DRUGS =
            "SELECT ad.id, diagnosis_history_id, drug_id, num_units, num_times, num_days, name drug_name\n" +
                    "FROM assignations_drugs ad JOIN drugs d\n" +
                    "ON ad.drug_id = d.id\n" +
                    "WHERE ad.diagnosis_history_id = ?";
    private static final String INSERT_INTO_ASSIGNATIONS_DRUGS =
            "INSERT INTO assignations_drugs\n" +
                    "(diagnosis_history_id, drug_id, num_units, num_times, num_days)\n" +
                    "VALUES(?, ?, ?, ?, ?)";

    /* Fields for assignations_drugs */
    private static final String ID = "id";
    private static final String DIAGNOSIS_HISTORY_ID = "diagnosis_history_id";
    private static final String NUM_UNITS = "num_units";
    private static final String NUM_TIMES = "num_times";
    private static final String NUM_DAYS = "num_days";

    /* Fields for drugs */
    private static final String DRUG_ID = "drug_id";
    private static final String DRUG_NAME = "drug_name";

    private Connection connection;

    JdbcAssignationsDrugsDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<AssignationsDrugs> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationsDrugs> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_ASSIGNATIONS_DRUGS)) {
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

    private AssignationsDrugs getEntityFromResultSet(ResultSet rs) throws SQLException {
        Drug drug = new Drug();
        drug.setId(rs.getInt(DRUG_ID));
        drug.setName(rs.getString(DRUG_NAME));
        return new AssignationsDrugs.Builder()
                .setId(rs.getInt(ID))
                .setDiagnosisHistoryId(rs.getInt(DIAGNOSIS_HISTORY_ID))
                .setDrug(drug)
                .setNumUnits(rs.getInt(NUM_UNITS))
                .setNumTimes(rs.getInt(NUM_TIMES))
                .setNumDays(rs.getInt(NUM_DAYS))
                .build();
    }

    @Override
    public Optional<AssignationsDrugs> find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AssignationsDrugs> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(AssignationsDrugs assignationsDrugs) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_ASSIGNATIONS_DRUGS, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(assignationsDrugs.getDiagnosisHistoryId()));
            query.setString(2, String.valueOf(assignationsDrugs.getDrug().getId()));
            query.setString(3, String.valueOf(assignationsDrugs.getNumUnits()));
            query.setString(4, String.valueOf(assignationsDrugs.getNumTimes()));
            query.setString(5, String.valueOf(assignationsDrugs.getNumDays()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                assignationsDrugs.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
    }

    @Override
    public void update(AssignationsDrugs assignationsDrugs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
