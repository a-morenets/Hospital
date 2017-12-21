package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DiagnosisHistoryDao;
import model.entities.DiagnosisHistory;

import java.util.List;

/**
 * DiagnosisHistoryService
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisHistoryService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DiagnosisHistoryService INSTANCE = new DiagnosisHistoryService();
    }

    public static DiagnosisHistoryService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<DiagnosisHistory> getDiagnosisHistoryByPatient(int id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            DiagnosisHistoryDao diagnosisHistoryDao = daoFactory.createDiagnosisHistoryDao(connection);
            return diagnosisHistoryDao.getDiagnosisHistoryByPatientId(id);
        }
    }

    public void createDiagnosisHistory(DiagnosisHistory diagnosisHistory) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            DiagnosisHistoryDao diagnosisHistoryDao = daoFactory.createDiagnosisHistoryDao(connection);
            diagnosisHistoryDao.create(diagnosisHistory);
        }
    }

}
