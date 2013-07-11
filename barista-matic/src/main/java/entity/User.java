package entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	private UUID id;
	private String userName;
	private String firstName;
	private String lastName;
	private Role role;

	@Entity
	@Table(name="role")
	public enum Role { 
		CUSTOMER {
			public String toString() {
				return "Customer";
			}
		}, 
		ADMINISTRATOR {
			public String toString() {
				return "Administrator";
			}
		}
	}

	public User() {
		this.id = UUID.randomUUID();
	}

	public User(String userName, String firstName, String lastName, String role) {
		this.id = UUID.randomUUID();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setRole(role);
	}

	@Column(name="id")
	public String getId() {
		return id.toString();
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="role")
	public String getRole() {
		return this.role.toString();
	}

	public void setRole(String role) {
		this.role = Role.valueOf(role);
	}

	public static void main(String args[]) {
		User user = new User("jsmith", "John", "Smith", "CUSTOMER");
		System.out.println(user.getId() + " " + user.getRole());
	}
}
