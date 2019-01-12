package model.entities;

/**
 * Patient
 * Created by alexey.morenets@gmail.com on 21.01.2017.
 */
public class Staff {

	private int id;
	private String lastName;
	private String firstName;
	private String surName;
	private Role role;
	private String email;
	private String password;
	
	public enum Role {
		DOCTOR, NURSE
	}

	public static class Builder {
		private int id;
		private String lastName;
		private String firstName;
		private String surName;
		private Role role;
		private String email;
		private String password;
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		
		public Builder setLastName(String lastNname) {
			this.lastName = lastNname;
			return this;
		}

		public Builder setFirstName(String firstNname) {
			this.firstName = firstNname;
			return this;
		}

		public Builder setSurName(String surNname) {
			this.surName = surNname;
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
		
		public Staff build(){
			Staff staff = new Staff();
			staff.setId(id);
			staff.setLastName(lastName);
			staff.setFirstName(firstName);
			staff.setSurName(surName);
			staff.setRole(role);
			staff.setEmail(email);
			staff.setPassword(password);
			return staff;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastNname) {
		this.lastName = lastNname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
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

}
