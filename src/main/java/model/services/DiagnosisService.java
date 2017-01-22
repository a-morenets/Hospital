package model.services;

import model.dao.DaoFactory;
import model.dao.DiagnosisDao;
import model.entities.Diagnosis;

/**
 * Created by alexey.morenets@gmail.com on 22.01.2017.
 */
public class DiagnosisService {

    private DiagnosisDao diagnosisDao = DaoFactory.getInstance().createDiagnosisDao();

    private static class Holder {
        static final DiagnosisService INSTANCE = new DiagnosisService();
    }

    public static DiagnosisService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Diagnosis getDiagnosisById(int id) {
        return diagnosisDao.find(id);
    }
}
