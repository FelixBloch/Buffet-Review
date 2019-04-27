package fi.haagahelia.buffetreview.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", nullable = false, updatable = false)
	private long userId;
	
	// User Info
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String passwordHash;
	@Column(name = "role", nullable = false) // Admin, Student or Teacher (Admins are added separately)
	private String role;
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	/*@Column(name = "university", nullable = false)
	private String university;*/
	
	// User Address
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "postcode", nullable = true)
	private String postcode;
	@Column(name = "state", nullable = true)
	private String state;
	@Column(name = "country", nullable = false)
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Review> reviews;
	
	public User(String username, String email, String password, String role, String firstName,  String lastName, String address, String city, String postcode, String state, String country) {
		this.username = username;
		this.email = email;
		this.passwordHash = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
	}
	
	public User() {
		
	}


	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> books) {
		this.reviews = reviews;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", passwordHash=" + passwordHash
				+ ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", city=" + city + ", postcode=" + postcode + ", state=" + state + ", country=" + country + "]";
	}
	
}
