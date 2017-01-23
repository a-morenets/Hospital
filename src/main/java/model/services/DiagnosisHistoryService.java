package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DiagnosisHistoryDao;
import model.entities.DiagnosisHistory;

import java.util.List;

/**
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
            connection.begin();
            DiagnosisHistoryDao diagnosisHistoryDao = daoFactory.createDiagnosisHistoryDao(connection);
            return diagnosisHistoryDao.getDiagnosisHistoryByPatientIdList(id);
        }
    }
}
