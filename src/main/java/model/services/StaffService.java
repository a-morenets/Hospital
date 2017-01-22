package model.services;

import java.util.Optional;

import model.dao.DaoFactory;
import model.dao.StaffDao;
import model.entities.Staff;

public class StaffService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final StaffService INSTANCE = new StaffService();
    }

    public static StaffService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Optional<Staff> login(String email, String password) {
        StaffDao dao = daoFactory.createStaffDao();
        return dao.getStaffByEmail(email).filter(staff -> password.equals(staff.getPassword()));
    }

}
