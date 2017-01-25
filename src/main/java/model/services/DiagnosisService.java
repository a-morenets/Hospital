package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DiagnosisDao;
import model.entities.Diagnosis;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DiagnosisService INSTANCE = new DiagnosisService();
    }

    public static DiagnosisService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Diagnosis getDiagnosisById(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DiagnosisDao diagnosisDao = daoFactory.createDiagnosisDao(connection);
            connection.commit();
            return diagnosisDao.find(id);
        }
    }

    public List<Diagnosis> getAllDiagnoses() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            DiagnosisDao diagnosisDao = daoFactory.createDiagnosisDao(connection);
            connection.commit();
            return diagnosisDao.findAll();
        }
    }
}
