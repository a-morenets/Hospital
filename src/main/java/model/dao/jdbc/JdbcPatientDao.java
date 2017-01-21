package model.dao.jdbc;

import model.dao.PatientDao;
import model.entities.Patient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 21.01.2017.
 */
public class JdbcPatientDao implements PatientDao {

    private static final String SELECT_FROM_PATIENTS = "SELECT * FROM patients";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String SUR_NAME = "surname";
    private Connection connection;

    JdbcPatientDao(Connection connection) {
        super();
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Patient find(int id) {
        return null;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> result = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_PATIENTS)) {

            while (resultSet.next()) {
                result.add(new Patient.Builder()
                        .setId(resultSet.getInt(ID))
                        .setFirstName(resultSet.getString(FIRST_NAME))
                        .setLastName(resultSet.getString(LAST_NAME))
                        .setSurName(resultSet.getString(SUR_NAME))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void create(Patient patient) {

    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void delete(int id) {

    }
}
