package model.services;

import model.dao.AssignationSurgeryDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entities.AssignationSurgery;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 25.01.2017.
 */
public class AssignationSurgeryService {

    private Connection connection;

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final AssignationSurgeryService INSTANCE = new AssignationSurgeryService();
    }

    public static AssignationSurgeryService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<AssignationSurgery> getAssignationSurgeriesByDiagnosisHistoryIdList(int diagnosisHistoryId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            AssignationSurgeryDao assignationSurgeryDao = daoFactory.createAssignationSurgeryDao(connection);
            connection.commit();
            return assignationSurgeryDao.findByDiagnosisHistoryId(diagnosisHistoryId);
        }
    }
}

