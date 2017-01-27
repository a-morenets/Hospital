package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import model.dao.StaffDao;
import model.entities.Staff;
import model.entities.Staff.Role;

/**
 * JdbcStaffDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcStaffDao implements StaffDao {

    /* SQL */
    private static final String SELECT_STAFF_BY_LOGIN = "SELECT * FROM staff WHERE lower(email) = ?";
    private static final String SELECT_STAFF_BY_ID = "SELECT * FROM staff WHERE id = ?";

    /* Fields */
    private static final String ID = "id";
    private static final String LASTNAME = "lastname";
    private static final String FIRSTNAME = "firstname";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private Connection connection;

    JdbcStaffDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Staff find(int id) {
        Staff staff = null;
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_ID)) {
            query.setString(1, String.valueOf(id));
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                staff = getStaffFromResultSet(resultSet);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return staff;
    }

    @Override
    public List<Staff> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Staff staff) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Staff staff) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        Optional<Staff> result = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_LOGIN)) {
            query.setString(1, email.toLowerCase());
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                Staff staff = getStaffFromResultSet(resultSet);
                result = Optional.of(staff);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private Staff getStaffFromResultSet(ResultSet resultSet) throws SQLException {
        return new Staff.Builder()
                .setId(resultSet.getInt(ID))
                .setLastName(resultSet.getString(LASTNAME))
                .setFirstName(resultSet.getString(FIRSTNAME))
                .setSurName(resultSet.getString(SURNAME))
                .setRole(Role.valueOf(resultSet.getString(ROLE)))
                .setEmail(resultSet.getString(EMAIL))
                .setPassword(resultSet.getString(PASSWORD))
                .build();
    }

}
