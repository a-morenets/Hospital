package model.dao.jdbc;


import controller.exception.AppException;
import model.dao.DiagnosisHistoryDao;

import model.entities.*;
import model.services.DiagnosisService;
import model.services.PatientService;
import model.services.StaffService;
import view.Errors;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcDiagnosisHistoryDao
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisHistoryDao implements DiagnosisHistoryDao {

    /* SQL */
    private static final String SELECT_FROM_DIAGNOSIS_HISTORY =
            "SELECT dh.id, diagnosis_date, type, staff_id, s.lastname, s.firstname, s.surname, s.role," +
                    " diagnosis_id, d.name diagnosis_name\n" +
                    "FROM diagnosis_history dh JOIN staff s\n" +
                    "ON dh.staff_id = s.id\n" +
                    "JOIN diagnosis d\n" +
                    "ON dh.diagnosis_id = d.id\n" +
                    "WHERE patient_id = ?\n" +
                    "ORDER BY diagnosis_date";
    private static final String INSERT_INTO_DIAGNOSIS_HISTORY =
            "INSERT INTO diagnosis_history(diagnosis_date, patient_id, staff_id, diagnosis_id, type)\n" +
                    "VALUES(?, ?, ?, ?, ?)";

    /* Fields for diagnosis_history */
    private static final String ID = "id";
    private static final String DATE = "diagnosis_date";
    private static final String TYPE = "type";

    /* Fields for staff */
    private static final String STAFF_ID = "staff_id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";

    /* Fields for diagnosis */
    private static final String DIAGNOSIS_ID = "diagnosis_id";
    private static final String DIAGNOSIS_NAME = "diagnosis_name";

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
    public Optional<DiagnosisHistory> find(int id) {
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
            query.setString(2, String.valueOf(diagnosisHistory.getPatientId()));
            query.setString(3, String.valueOf(diagnosisHistory.getStaff().getId()));
            query.setString(4, String.valueOf(diagnosisHistory.getDiagnosis().getId()));
            query.setString(5, String.valueOf(diagnosisHistory.getDiagnosisType().name()));

            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                diagnosisHistory.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
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
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    private DiagnosisHistory getEntityFromResultSet(ResultSet rs) throws SQLException {
        Staff staff = new Staff.Builder()
                .setId(rs.getInt(STAFF_ID))
                .setLastName(rs.getString(LASTNAME))
                .setFirstName(rs.getString(FIRSTNAME))
                .setSurName(rs.getString(SURNAME))
                .setRole(Staff.Role.valueOf(rs.getString(ROLE)))
                .build();

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(rs.getInt(DIAGNOSIS_ID));
        diagnosis.setName(rs.getString(DIAGNOSIS_NAME));

        return new DiagnosisHistory.Builder()
                .setId(rs.getInt(ID))
                .setDate(rs.getTimestamp(DATE))
                .setStaff(staff)
                .setDiagnosis(diagnosis)
                .setDiagnosisType(DiagnosisType.valueOf(rs.getString(TYPE)))
                .build();
    }

}
