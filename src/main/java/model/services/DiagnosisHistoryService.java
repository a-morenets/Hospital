package model.services;

import model.dao.DaoFactory;
import model.dao.DiagnosisHistoryDao;
import model.entities.DiagnosisHistory;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisHistoryService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final DiagnosisHistoryService INSTANCE = new DiagnosisHistoryService();
    }

    public static DiagnosisHistoryService getInstance() {
        return Holder.INSTANCE;
    }

    public List<DiagnosisHistory> getDiagnosesByPatient(int id) {
        DiagnosisHistoryDao dao = daoFactory.createDiagnosisHistoryDao();
        return dao.getDiagnosisHistoryByPatientIdList(id);
    }
}
