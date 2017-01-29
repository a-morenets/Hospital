package model.services;

import java.util.Optional;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.StaffDao;
import model.entities.Staff;

/**
 * StaffService
 * Created by alexey.morenets@gmail.com on 21.01.2017.
 */
public class StaffService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final StaffService INSTANCE = new StaffService();
    }

    public static StaffService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Optional<Staff> login(String email, String password) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            StaffDao staffDao = daoFactory.createStaffDao(connection);
            return staffDao.getStaffByEmail(email).filter(staff -> password.equals(staff.getPassword()));
        }
    }

    public Optional<Staff> getStaffById(int Id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            StaffDao staffDao = daoFactory.createStaffDao(connection);
            return staffDao.find(Id);
        }
    }

}
