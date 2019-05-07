package fi.haagahelia.buffetreview.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Felix Bloch felix.z.bloch@gmail.com
 *
 */
public class SignupForm {
	@NotEmpty
	@Size(min = 2, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 5, max = 50)
	private String email = "";

	@NotEmpty
	@Size(min = 6, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 6, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	private String role = "";

	@NotEmpty
	@Size(min = 2, max = 50)
	private String firstName = "";

	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName = "";
	
	private String captcha;

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
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
