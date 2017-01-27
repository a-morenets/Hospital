package model.dao;

import java.util.Optional;
import model.entities.Staff;

/**
 * interface StaffDao
 * Created by alexey.morenets@gmail.com on 26.01.2017.
 */
public interface StaffDao extends GenericDao<Staff> {
	Optional<Staff> getStaffByEmail(String email);
}
