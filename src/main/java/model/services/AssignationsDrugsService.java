package model.services;

import model.dao.AssignationsDrugsDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entities.AssignationsDrugs;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class AssignationsDrugsService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final AssignationsDrugsService INSTANCE = new AssignationsDrugsService();
    }

    public static AssignationsDrugsService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<AssignationsDrugs> getAssignationDrugsByDiagnosisHistoryIdList(int diagnosisHistoryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationsDrugsDao assignationsDrugsDao = daoFactory.createAssignationsDrugsDao(connection);
            connection.commit();
            return assignationsDrugsDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }

    public void createAssignationDrug(List<AssignationsDrugs> assignationsDrugsList) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationsDrugsDao assignationsDrugsDao = daoFactory.createAssignationsDrugsDao(connection);
            for (AssignationsDrugs assignationsDrugs : assignationsDrugsList) {
                assignationsDrugsDao.create(assignationsDrugs);
            }
            connection.commit();
        }
    }

}
