package model.dao.jdbc;

import model.dao.AssignationsDrugsDao;
import model.entities.AssignationsDrugs;
import model.entities.Drug;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class JdbcAssignationsDrugsDao implements AssignationsDrugsDao {

    /* SELECT */
    private static final String SELECT_FROM_ASSIGNATIONS_DRUGS =
            "SELECT ad.id, diagnosis_history_id, drug_id, num_units, num_times, num_days, d.id id_drug, name\n" +
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
    public static final String DRUG_ID = "drug_id";
    public static final String NUM_UNITS = "num_units";
    public static final String NUM_TIMES = "num_times";
    public static final String NUM_DAYS = "num_days";

    /* Fields for drugs */
    public static final String ID_DRUG = "id_drug";
    public static final String NAME = "name";

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
                result.add(getAssignationDrugFromResultSet(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private AssignationsDrugs getAssignationDrugFromResultSet(ResultSet resultSet) throws SQLException {
        Drug drug = new Drug();
        drug.setId(resultSet.getInt(ID_DRUG));
        drug.setName(resultSet.getString(NAME));
        return new AssignationsDrugs.Builder()
                .setId(resultSet.getInt(ID))
                .setDiagnosisHistoryId(resultSet.getInt(DIAGNOSIS_HISTORY_ID))
                .setDrug(drug)
                .setNumUnits(resultSet.getInt(NUM_UNITS))
                .setNumTimes(resultSet.getInt(NUM_TIMES))
                .setNumDays(resultSet.getInt(NUM_DAYS))
                .build();
    }

    @Override
    public AssignationsDrugs find(int id) {
        return null;
    }

    @Override
    public List<AssignationsDrugs> findAll() {
        return null;
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(AssignationsDrugs assignationsDrugs) {

    }

    @Override
    public void delete(int id) {

    }
}
