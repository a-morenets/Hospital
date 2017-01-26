package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.DrugDao;
import model.entities.Drug;

import java.util.List;

/**
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class DrugService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {

        static final DrugService INSTANCE = new DrugService();
    }
    public static DrugService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<Drug> getAllDrugs() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            DrugDao drugDao = daoFactory.createDrugDao(connection);
            return drugDao.findAll();
        }
    }

}
