package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PatientDao;
import model.entities.Patient;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 21.01.2017.
 */
public class PatientService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final PatientService INSTANCE = new PatientService();
    }

    public static PatientService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<Patient> getAllPatients() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            connection.commit();
            return patientDao.findAll();
        }
    }

    public void createPatient(Patient patient) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            patientDao.create(patient);
            connection.commit();
        }
    }

    public Patient getPatientById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            connection.commit();
            return patientDao.find(id);
        }
    }

}
