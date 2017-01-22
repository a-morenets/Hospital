package model.dao.jdbc;

import com.mysql.jdbc.Driver;

import model.dao.DaoFactory;
import model.dao.DiagnosisHistoryDao;
import model.dao.PatientDao;
import model.dao.StaffDao;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Properties;

public class JdbcDaoFactory extends DaoFactory {

    private Connection connection;
    private static final String DB_URL = "url";

    public JdbcDaoFactory() {
        try {
            InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
            Properties dbProps = new Properties();
            dbProps.load(inputStream);
            String url = dbProps.getProperty(DB_URL);
            new Driver();
            connection = DriverManager.getConnection(url, dbProps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StaffDao createStaffDao() {
        return new JdbcStaffDao(connection);
    }

    @Override
    public PatientDao createPatientDao() {
        return new JdbcPatientDao(connection);
    }

    @Override
    public DiagnosisHistoryDao createDiagnosisHistoryDao() {
        return new JdbcDiagnosisHistoryDao(connection);
    }
}
