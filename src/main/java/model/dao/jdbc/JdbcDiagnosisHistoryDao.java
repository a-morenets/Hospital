package model.dao.jdbc;


import model.dao.DiagnosisHistoryDao;

import model.entities.*;
import model.services.DiagnosisService;
import model.services.PatientService;
import model.services.StaffService;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * JdbcDiagnosisHistoryDao
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisHistoryDao implements DiagnosisHistoryDao {

    /* SQL */
    private static final String SELECT_FROM_DIAGNOSIS_HISTORY =
            "SELECT * FROM diagnosis_history WHERE patient_id = ? ORDER BY diagnosis_date";
    private static final String INSERT_INTO_DIAGNOSIS_HISTORY =
            "INSERT INTO diagnosis_history(diagnosis_date, patient_id, staff_id, diagnosis_id, type)\n" +
                    "VALUES(?, ?, ?, ?, ?)";

    /* Fields */
    private static final String ID = "id";
    private static final String DATE = "diagnosis_date";
    private static final String PATIENT_ID = "patient_id";
    private static final String STAFF_ID = "staff_id";
    private static final String DIAGNOSIS_ID = "diagnosis_id";
    private static final String TYPE = "type";

    private PatientService patientService = PatientService.getInstance();
    private StaffService staffService = StaffService.getInstance();
    private DiagnosisService diagnosisService = DiagnosisService.getInstance();

    private Connection connection;

    JdbcDiagnosisHistoryDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DiagnosisHistory find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DiagnosisHistory> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(DiagnosisHistory diagnosisHistory) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_DIAGNOSIS_HISTORY, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, String.valueOf(diagnosisHistory.getDate()));
            query.setString(2, String.valueOf(diagnosisHistory.getPatient().getId()));
            query.setString(3, String.valueOf(diagnosisHistory.getStaff().getId()));
            query.setString(4, String.valueOf(diagnosisHistory.getDiagnosis().getId()));
            query.setString(5, String.valueOf(diagnosisHistory.getDiagnosisType().name()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                diagnosisHistory.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DiagnosisHistory diagnosisHistory) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DiagnosisHistory> getDiagnosisHistoryByPatientIdList(int patientId) {
        List<DiagnosisHistory> result = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(SELECT_FROM_DIAGNOSIS_HISTORY)) {
            query.setString(1, String.valueOf(patientId));
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                result.add(getDiagnosisHistoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private DiagnosisHistory getDiagnosisHistoryFromResultSet(ResultSet resultSet) throws SQLException {
        // TODO SELECT ... JOIN
        Patient patient = patientService.getPatientById(resultSet.getInt(PATIENT_ID));
        Staff staff = staffService.getStaffById(resultSet.getInt(STAFF_ID));
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(resultSet.getInt(DIAGNOSIS_ID));
        return new DiagnosisHistory.Builder()
                .setId(resultSet.getInt(ID))
                .setDate(resultSet.getTimestamp(DATE))
                .setPatient(patient)
                .setStaff(staff)
                .setDiagnosis(diagnosis)
                .setDiagnosisType(DiagnosisType.valueOf(resultSet.getString(TYPE)))
                .build();
    }

}
