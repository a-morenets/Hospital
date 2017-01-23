package model.dao.jdbc;

import model.dao.PatientDao;
import model.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 21.01.2017.
 */
public class JdbcPatientDao implements PatientDao {

    /* SELECT */
    private static final String SELECT_FROM_PATIENTS = "SELECT * FROM patients";
    private static final String SELECT_PATIENT_BY_ID = "SELECT * FROM patients WHERE id = ?";
    private static final String INSERT_INTO_CITY_NAME_VALUES =
            "INSERT INTO patients (lastname, firstname, surname) VALUES (?, ?, ?)";

    /* Fields */
    private static final String ID = "id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";

    private Connection connection;

    JdbcPatientDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Patient find(int id) {
        Patient patient = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_PATIENT_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                patient = getPatientFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return patient;
    }

    private Patient getPatientFromResultSet(ResultSet resultSet) throws SQLException {
        return new Patient.Builder()
                .setId(resultSet.getInt(ID))
                .setFirstName(resultSet.getString(FIRSTNAME))
                .setLastName(resultSet.getString(LASTNAME))
                .setSurName(resultSet.getString(SURNAME))
                .build();
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PATIENTS)) {

            while (resultSet.next()) {
                result.add(getPatientFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void create(Patient patient) {
        try (PreparedStatement query =
                     connection.prepareStatement(INSERT_INTO_CITY_NAME_VALUES, Statement.RETURN_GENERATED_KEYS)) {

            query.setString(1, patient.getLastName());
            query.setString(2, patient.getFirstName());
            query.setString(3, patient.getSurName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();

            if (keys.next()) {
                patient.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void delete(int id) {

    }

}
