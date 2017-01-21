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

    private static final String SELECT_STAFF_BY_LOGIN = "SELECT * FROM staff WHERE lower(email) = ?";
    private Connection connection;

    JdbcStaffDao(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public StaffDao find(int id) {
        return null;
    }

    @Override
    public List<StaffDao> findAll() {
        return null;
    }

    @Override
    public void create(StaffDao staffDao) {

    }

    @Override
    public void update(StaffDao staffDao) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        Optional<Staff> result = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(SELECT_STAFF_BY_LOGIN)) {
            query.setString(1, email.toLowerCase());
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Staff staff = getStaffFromResultSet(rs);
                result = Optional.of(staff);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return result;

    }

    private Staff getStaffFromResultSet(ResultSet rs) throws SQLException {
        Staff person = new Staff.Builder()
                .setId(rs.getInt("id"))
                .setFirstName(rs.getString("firstname"))
                .setLastName(rs.getString("lastname"))
                .setSurName(rs.getString("surname"))
                .setRole(Role.valueOf(rs.getString("role")))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"))
                .build();
        return person;
    }

}
