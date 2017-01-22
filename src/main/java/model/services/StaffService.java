package model.services;

import java.util.Optional;

import model.dao.DaoFactory;
import model.dao.StaffDao;
import model.entities.Staff;

public class StaffService {

    private StaffDao staffDao = DaoFactory.getInstance().createStaffDao();

    private static class Holder {
        static final StaffService INSTANCE = new StaffService();
    }

    public static StaffService getInstance() {
        return Holder.INSTANCE;
    }

    /* Service methods */

    public Optional<Staff> login(String email, String password) {
        return staffDao.getStaffByEmail(email).filter(staff -> password.equals(staff.getPassword()));
    }

    public Staff getStaffById(int Id) {
        return staffDao.find(Id);
    }
}
