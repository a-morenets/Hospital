package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.SurgeryDao;
import model.entities.Surgery;

import java.util.List;

/**
 * SurgeryService
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class SurgeryService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final SurgeryService INSTANCE = new SurgeryService();
    }

    public static SurgeryService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<Surgery> getAllSurgeries() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            SurgeryDao surgeryDao = daoFactory.createSurgeryDao(connection);
            return surgeryDao.findAll();
        }
    }
}
