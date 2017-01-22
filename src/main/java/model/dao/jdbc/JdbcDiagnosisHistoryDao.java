package model.dao.jdbc;


import model.dao.DiagnosisHistoryDao;

import model.entities.DiagnosisHistory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class JdbcDiagnosisHistoryDao implements DiagnosisHistoryDao {

    /* SELECT */
    public static final String SELECT_FROM_DIAGNOSIS_HISTORY =
            "SELECT * FROM diagnosis_history WHERE patient_id = ? ORDER BY date";

    /* Fields */
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String PATIENT_ID = "patient_id";
    public static final String STAFF_ID = "staff_id";
    public static final String DIAGNOSIS_ID = "diagnosis_id";
    public static final String TYPE = "type";

    private Connection connection;

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

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DIAGNOSIS_HISTORY)) {

            while (resultSet.next()) {
                result.add(getPatientFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private DiagnosisHistory getPatientFromResultSet(ResultSet rs) throws SQLException {
        DiagnosisHistory diagnosisHistory = new DiagnosisHistory.Builder()
                .setId(rs.getInt(ID))
                .setDate(rs.getTimestamp(DATE))
                .setPatientId(rs.getInt(PATIENT_ID))
                .setStaffId(rs.getInt(STAFF_ID))
                .setDiagnosisId(rs.getInt(DIAGNOSIS_ID))
                .setType(DiagnosisHistory.Type.valueOf(rs.getString(TYPE)))
                .build();
        return diagnosisHistory;
    }

}
