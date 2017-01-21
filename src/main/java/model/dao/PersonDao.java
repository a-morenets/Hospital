package model.dao;

import java.util.Optional;

import model.entities.Person;

public interface PersonDao extends GenericDao<PersonDao> {
	Optional<Person> getPersonByEmail(String email);
}
