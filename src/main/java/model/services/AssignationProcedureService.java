package model.services;

import model.dao.AssignationProcedureDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entities.AssignationProcedure;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class AssignationProcedureService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final AssignationProcedureService INSTANCE = new AssignationProcedureService();
    }

    public static AssignationProcedureService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<AssignationProcedure> getAssignationProceduresByDiagnosisHistoryIdList(int diagnosisHistoryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationProcedureDao assignationProcedureDao = daoFactory.createAssignationProcedureDao(connection);
            connection.commit();
            return assignationProcedureDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }
}
