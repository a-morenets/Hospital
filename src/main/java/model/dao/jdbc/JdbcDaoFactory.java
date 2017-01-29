package model.dao.jdbc;

import controller.exception.AppException;
import model.dao.*;
import view.Errors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * JdbcDaoConnection
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/hospital");
        } catch (NamingException e) {
            throw new AppException(Errors.NAMING_EXCEPTION, e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new AppException(Errors.SQL_ERROR, e);
        }
    }

    @Override
    public StaffDao createStaffDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcStaffDao(sqlConnection);
    }

    @Override
    public PatientDao createPatientDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcPatientDao(sqlConnection);
    }

    @Override
    public DiagnosisHistoryDao createDiagnosisHistoryDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcDiagnosisHistoryDao(sqlConnection);
    }

    @Override
    public DiagnosisDao createDiagnosisDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcDiagnosisDao(sqlConnection);
    }

    @Override
    public AssignationsDrugsDao createAssignationsDrugsDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcAssignationsDrugsDao(sqlConnection);
    }

    @Override
    public AssignationsProceduresDao createAssignationsProceduresDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcAssignationsProceduresDao(sqlConnection);
    }

    @Override
    public AssignationsSurgeriesDao createAssignationsSurgeriesDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcAssignationsSurgeriesDao(sqlConnection);
    }

    @Override
    public DrugDao createDrugDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcDrugDao(sqlConnection);
    }

    @Override
    public ProcedureDao createProcedureDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcProcedureDao(sqlConnection);
    }

    @Override
    public SurgeryDao createSurgeryDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcSurgeryDao(sqlConnection);
    }

}
