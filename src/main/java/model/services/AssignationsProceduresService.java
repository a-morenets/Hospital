package model.services;

import model.dao.AssignationsProceduresDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entities.AssignationsProcedures;

import java.util.List;

/**
 * AssignationsProceduresService
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class AssignationsProceduresService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final AssignationsProceduresService INSTANCE = new AssignationsProceduresService();
    }

    public static AssignationsProceduresService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<AssignationsProcedures> getAssignationProceduresByDiagnosisHistoryIdList(int diagnosisHistoryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            AssignationsProceduresDao assignationsProceduresDao = daoFactory.createAssignationsProceduresDao(connection);
            return assignationsProceduresDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }

    public void createAssignationsProcedures(List<AssignationsProcedures> assignationsProceduresList) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationsProceduresDao assignationsProceduresDao = daoFactory.createAssignationsProceduresDao(connection);
            for (AssignationsProcedures assignationsProcedures : assignationsProceduresList) {
                assignationsProceduresDao.create(assignationsProcedures);
            }
            connection.commit();
        }
    }

}
