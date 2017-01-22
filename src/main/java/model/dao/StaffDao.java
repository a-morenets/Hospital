package model.dao;

import java.util.Optional;

import model.entities.Staff;

public interface StaffDao extends GenericDao<Staff> {
	Optional<Staff> getStaffByEmail(String email);
}
