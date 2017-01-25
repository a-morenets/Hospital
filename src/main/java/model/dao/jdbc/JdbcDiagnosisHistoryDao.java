package model.dao.jdbc;


import model.dao.DiagnosisHistoryDao;

import model.entities.Diagnosis;
import model.entities.DiagnosisHistory;
import model.entities.Staff;
import model.services.DiagnosisHistoryService;
import model.services.DiagnosisService;
import model.services.StaffService;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisHistoryDao implements DiagnosisHistoryDao {

    /* SELECT */
    private static final String SELECT_FROM_DIAGNOSIS_HISTORY =
            "SELECT * FROM diagnosis_history WHERE patient_id = ? ORDER BY diagnosis_date";

    /* Fields */
    public static final String ID = "id";
    public static final String DATE = "diagnosis_date";
    public static final String PATIENT_ID = "patient_id";
    public static final String STAFF_ID = "staff_id";
    public static final String DIAGNOSIS_ID = "diagnosis_id";
    public static final String TYPE = "type";

    private StaffService staffService = StaffService.getInstance();
    private DiagnosisService diagnosisService = DiagnosisService.getInstance();

    private Connection connection;

    public JdbcDiagnosisHistoryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DiagnosisHistory find(int id) {
        return null;
    }

    @Override
    public List<DiagnosisHistory> findAll() {
        return null;
    }

    @Override
    public void create(DiagnosisHistory diagnosisHistory) {

    }

    @Override
    public void update(DiagnosisHistory diagnosisHistory) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<DiagnosisHistory> getDiagnosisHistoryByPatientIdList(int patientId) {
        List<DiagnosisHistory> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_HISTORY)) {
            query.setString(1, String.valueOf(patientId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getPatientFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private DiagnosisHistory getPatientFromResultSet(ResultSet resultSet) throws SQLException {
        Staff staff = staffService.getStaffById(resultSet.getInt(STAFF_ID));
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(resultSet.getInt(DIAGNOSIS_ID));
        return new DiagnosisHistory.Builder()
                .setId(resultSet.getInt(ID))
                .setDate(resultSet.getTimestamp(DATE))
                .setPatientId(resultSet.getInt(PATIENT_ID))
                .setStaff(staff)
                .setDiagnosis(diagnosis)
                .setType(DiagnosisHistory.Type.valueOf(resultSet.getString(TYPE)))
                .build();
    }

}
