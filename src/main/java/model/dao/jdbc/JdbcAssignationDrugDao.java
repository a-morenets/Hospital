package model.dao.jdbc;

import model.dao.AssignationDrugDao;
import model.entities.AssignationDrug;
import model.entities.Drug;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class JdbcAssignationDrugDao implements AssignationDrugDao {

    /* SELECT */
    private static final String SELECT_FROM_ASSIGNATIONS_DRUGS =
            "SELECT ad.id, diagnosis_history_id, drug_id, num_units, num_times, num_days, d.id id_drug, name\n" +
                    "FROM assignations_drugs ad JOIN drugs d\n" +
                    "ON ad.drug_id = d.id\n" +
                    "WHERE ad.diagnosis_history_id = ?";

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

    JdbcAssignationDrugDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<AssignationDrug> findByDiagnosisHistoryId(int diagnosisHistoryId) {
        List<AssignationDrug> result = new ArrayList<>();
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

    private AssignationDrug getAssignationDrugFromResultSet(ResultSet resultSet) throws SQLException {
        Drug drug = new Drug();
        drug.setId(resultSet.getInt(ID_DRUG));
        drug.setName(resultSet.getString(NAME));
        return new AssignationDrug.Builder()
                .setId(resultSet.getInt(ID))
                .setDiagnosisHistoryId(resultSet.getInt(DIAGNOSIS_HISTORY_ID))
                .setDrug(drug)
                .setNumUnits(resultSet.getInt(NUM_UNITS))
                .setNumTimes(resultSet.getInt(NUM_TIMES))
                .setNumDays(resultSet.getInt(NUM_DAYS))
                .build();
    }

    @Override
    public AssignationDrug find(int id) {
        return null;
    }

    @Override
    public List<AssignationDrug> findAll() {
        return null;
    }

    @Override
    public void create(AssignationDrug assignationDrug) {

    }

    @Override
    public void update(AssignationDrug assignationDrug) {

    }

    @Override
    public void delete(int id) {

    }
}
