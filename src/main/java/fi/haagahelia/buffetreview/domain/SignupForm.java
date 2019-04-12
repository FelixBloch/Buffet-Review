package fi.haagahelia.buffetreview.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SignupForm {
	@NotEmpty
    @Size(min=2, max=30)
    private String username = "";
	
	@NotEmpty
    @Size(min=5, max=50)
    private String email = "";

    @NotEmpty
    @Size(min=6, max=30)
    private String password = "";

    @NotEmpty
    @Size(min=6, max=30)
    private String passwordCheck = "";

    @NotEmpty
    private String role = "";
    
    @NotEmpty
    @Size(min=2, max=50)
    private String firstName = "";
    
    @NotEmpty
    @Size(min=2, max=50)
    private String lastName = "";
    
    @NotEmpty
    @Size(min=5, max=50)
    private String address = "";
    
    @NotEmpty
    @Size(min=3, max=30)
    private String city = "";
    
    @NotEmpty
    @Size(min=3, max=15)
    private String postcode;
    
    @NotEmpty
    @Size(min=3, max=30)
    private String state = "";
    
    @NotEmpty
    @Size(min=3, max=30)
    private String country = "";

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
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
    
    
}
