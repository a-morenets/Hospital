package model.entities;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private String surName;
	private LocalDate birthDate;
	private Role role;
	private String email;
	private String password;
	
	public enum Role{
		DOCTOR, NURSE
	}

	public static class Builder {
		private int id;
		private String firstName;
		private String lastName;
		private String surName;
		private LocalDate birthDate;
		private Role role;
		private String email;
		private String password;
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		
		public Builder setFirstName(String firstNname) {
			this.firstName = firstNname;
			return this;
		}
		
		public Builder setLastName(String lastNname) {
			this.lastName = lastNname;
			return this;
		}
		
		public Builder setSurName(String surNname) {
			this.surName = surNname;
			return this;
		}
		
		public Builder setBirthDate(Timestamp birthDate) {
			this.birthDate = birthDate.toLocalDateTime().toLocalDate();
			return this;
		}
		
		public Builder setRole(Role role) {
			this.role = role;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		public Person build(){
			Person person = new Person();
			person.setId(id);
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setSurName(surName);
			person.setBirthDate(birthDate);
			person.setRole(role);
			person.setEmail(email);
			person.setPassword(password);
			return person;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstNname) {
		this.firstName = firstNname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNname) {
		this.lastName = lastNname;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", surName=" + surName
				+ ", birthDate=" + birthDate + ", role=" + role + ", email=" + email + ", password=" + password + "]";
	}

}
