package model.dao;

import java.util.Optional;

import model.entities.Staff;

public interface StaffDao extends GenericDao<StaffDao> {
	Optional<Staff> getStaffByEmail(String email);
}
