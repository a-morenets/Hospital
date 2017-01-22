package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    public abstract StaffDao createStaffDao();
    public abstract PatientDao createPatientDao();
    public abstract DiagnosisHistoryDao createDiagnosisHistoryDao();

    private static final String DB_FACTORY_CLASS = "factory.class";
    public static final String DB_FILE = "/db.properties";

    private static DaoFactory instance;

    public static DaoFactory getInstance() {
        if (instance == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                instance = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}
