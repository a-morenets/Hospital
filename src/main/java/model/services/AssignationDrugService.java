package model.services;

import model.dao.AssignationDrugDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entities.AssignationDrug;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class AssignationDrugService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final AssignationDrugService INSTANCE = new AssignationDrugService();
    }

    public static AssignationDrugService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<AssignationDrug> getAssignationDrugsByDiagnosisHistoryIdList(int diagnosisHistoryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationDrugDao assignationDrugDao = daoFactory.createAssignationDrugDao(connection);
            connection.commit();
            return assignationDrugDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }
}
