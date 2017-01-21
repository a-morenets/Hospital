package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

import model.dao.PersonDao;
import model.entities.Person;
import model.entities.Person.Role;

public class JdbcPersonDao implements PersonDao {

    private static final String SELECT_PERSON_BY_LOGIN = "SELECT * FROM staff WHERE lower(email) = ?";
    private Connection connection;

    JdbcPersonDao(Connection connection) {
        super();
        this.connection = connection;
    }

    @Override
    public PersonDao find(int id) {
        return null;
    }

    @Override
    public List<PersonDao> findAll() {
        return null;
    }

    @Override
    public void create(PersonDao personDao) {

    }

    @Override
    public void update(PersonDao personDao) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<Person> getPersonByEmail(String email) {
        Optional<Person> result = Optional.empty();

        try (PreparedStatement query = connection.prepareStatement(SELECT_PERSON_BY_LOGIN)) {
            query.setString(1, email.toLowerCase());
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                Person person = getPersonFromResultSet(rs);
                result = Optional.of(person);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return result;

    }

    private Person getPersonFromResultSet(ResultSet rs) throws SQLException {
        Person person = new Person.Builder()
                .setId(rs.getInt("id"))
                .setFirstName(rs.getString("firstname"))
                .setLastName(rs.getString("lastname"))
                .setSurName(rs.getString("surname"))
                .setRole(Role.valueOf(rs.getString("role")))
                .setEmail(rs.getString("email"))
                .setPassword(rs.getString("password"))
                .setBirthDate(rs.getTimestamp("birthdate"))
                .build();
        return person;
    }

}
