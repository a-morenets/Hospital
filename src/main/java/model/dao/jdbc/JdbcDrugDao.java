package model.dao.jdbc;

import model.dao.DrugDao;
import model.entities.Drug;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcDrugDao implements DrugDao {

    /* SELECT */
    private static final String SELECT_FROM_DRUGS = "SELECT * FROM drugs";

    /* Fields */
    private static final String ID = "id";
    public static final String NAME = "name";

    private Connection connection;

    JdbcDrugDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Drug find(int id) {
        return null;
    }

    @Override
    public List<Drug> findAll() {
        List<Drug> result = new ArrayList<>();

        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(SELECT_FROM_DRUGS)) {

            while (resultSet.next()) {
                result.add(getDrugFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Drug getDrugFromResultSet(ResultSet resultSet) throws SQLException {
        Drug drug = new Drug();
        drug.setId(resultSet.getInt(ID));
        drug.setName(resultSet.getString(NAME));
        return drug;
    }

    @Override
    public void create(Drug drug) {

    }

    @Override
    public void update(Drug drug) {

    }

    @Override
    public void delete(int id) {

    }
}
