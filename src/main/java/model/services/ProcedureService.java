package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.ProcedureDao;
import model.entities.Procedure;

import java.util.List;

/**
 * ProcedureService
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public class ProcedureService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final ProcedureService INSTANCE = new ProcedureService();
    }
    public static ProcedureService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public List<Procedure> getAllProcedures() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ProcedureDao procedureDao = daoFactory.createProcedureDao(connection);
            return procedureDao.findAll();
        }
    }

}
