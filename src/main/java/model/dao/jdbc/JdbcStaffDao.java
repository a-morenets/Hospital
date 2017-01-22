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

public class JdbcStaffDao implements StaffDao {

    /* SELECT */
    private static final String SELECT_STAFF_BY_LOGIN = "SELECT * FROM staff WHERE lower(email) = ?";

    /* Fields */
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private Connection connection;

    JdbcStaffDao(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public Staff find(int id) {
        return null;
    }

    @Override
    public List<Staff> findAll() {
        return null;
    }

    @Override
    public void create(Staff staff) {

    }

    @Override
    public void update(Staff staff) {

    }

    @Override
    public void delete(int id) {

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
        Staff person = new Staff.Builder()
                .setId(resultSet.getInt(ID))
                .setFirstName(resultSet.getString(FIRSTNAME))
                .setLastName(resultSet.getString(LASTNAME))
                .setSurName(resultSet.getString(SURNAME))
                .setRole(Role.valueOf(resultSet.getString(ROLE)))
                .setEmail(resultSet.getString(EMAIL))
                .setPassword(resultSet.getString(PASSWORD))
                .build();
        return person;
    }

}
