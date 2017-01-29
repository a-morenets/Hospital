package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.DrugDao;
import model.entities.Drug;
import view.Errors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcDrugDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcDrugDao implements DrugDao {

    /* SQL */
    private static final String SELECT_FROM_DRUGS = "SELECT * FROM drugs";

    /* Fields */
    private static final String ID = "id";
    private static final String NAME = "name";

    private Connection connection;

    JdbcDrugDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Drug> find(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DRUGS)) {

            while (resultSet.next()) {
                result.add(getEntityFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
        return result;
    }

    private Drug getEntityFromResultSet(ResultSet rs) throws SQLException {
        Drug drug = new Drug();
        drug.setId(rs.getInt(ID));
        drug.setName(rs.getString(NAME));
        return drug;
    }

    @Override
    public void create(Drug drug) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Drug drug) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

}
