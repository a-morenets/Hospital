package model.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {

    public abstract DaoConnection getConnection();

    public abstract StaffDao createStaffDao(DaoConnection connection);
    public abstract PatientDao createPatientDao(DaoConnection connection);
    public abstract DiagnosisHistoryDao createDiagnosisHistoryDao(DaoConnection connection);
    public abstract DiagnosisDao createDiagnosisDao(DaoConnection connection);
    public abstract AssignationDrugDao createAssignationDrugDao(DaoConnection connection);
    public abstract AssignationProcedureDao createAssignationProcedureDao(DaoConnection connection);
    public abstract AssignationSurgeryDao createAssignationSurgeryDao(DaoConnection connection);
    public abstract DrugDao createDrugDao(DaoConnection connection);
    public abstract ProcedureDao createProcedureDao(DaoConnection connection);
    public abstract SurgeryDao createSurgeryDao(DaoConnection connection);

    public static final String DB_FILE = "/db.properties";

    private static final String DB_FACTORY_CLASS = "factory.class";

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
