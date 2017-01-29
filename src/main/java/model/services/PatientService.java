package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.PatientDao;
import model.entities.DiagnosisType;
import model.entities.Patient;

import java.util.List;
import java.util.Optional;

/**
 * PatientService
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
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            return patientDao.findAll();
        }
    }

    public void createPatient(Patient patient) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            patientDao.create(patient);
        }
    }

    public Optional<Patient> getPatientById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            return patientDao.find(id);
        }
    }

    public boolean isPatientOnCure(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            PatientDao patientDao = daoFactory.createPatientDao(connection);
            return patientDao.getDiagnosisType(id) == DiagnosisType.PRIMARY;
        }
    }

}
