package fi.haagahelia.buffetreview.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.buffetreview.domain.SignupForm;
import fi.haagahelia.buffetreview.domain.User;
import fi.haagahelia.buffetreview.domain.UserRepository;
import fi.haagahelia.buffetreview.recaptcha.ReCaptchaService;

/**
 * Controller for user related operations.
 */
@Controller
public class UserController {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	ReCaptchaService captchaService;
	
	/**
	 * Mapping for the signup page.
	 * 
	 * Allows the user to enter his data and check the reCAPTCHA.
	 */
	@RequestMapping(value = "signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "signup";
	}
	
	/**
	 * Takes the entered data, runs it through various tests and saves it to the DB.
	 */
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult,
			@RequestParam(name = "g-recaptcha-response") String reCaptchaResponse, HttpServletRequest request) {
		
		// Checks for validation errors.
		if (!bindingResult.hasErrors()) { 
			
			// Checks if the passwords match.
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { 
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole(signupForm.getRole());
				newUser.setFirstName(signupForm.getFirstName());
				newUser.setLastName(signupForm.getLastName());
				
				// Checks if the username exists.
				if (repository.findByUsername(signupForm.getUsername()) == null) { 
					
					// Checks if the email exists.
					if (repository.findByUsername(signupForm.getEmail()) == null) { 

						String ip = request.getRemoteAddr();

						String captchaVerifyMessage = captchaService.verifyRecaptcha(ip, reCaptchaResponse);
						
						// Checks that the response from Google for the reCAPTCHA is not empty.
						if (StringUtils.isNotEmpty(captchaVerifyMessage)) {
							Map<String, Object> response = new HashMap<>();
							response.put("message", captchaVerifyMessage);

							bindingResult.rejectValue("captcha", "err.captcha", "reCAPTCHA failed.");
							return "signup";
						}
						
						repository.save(newUser);

					} else {
						bindingResult.rejectValue("email", "err.email", "Email already exists");
						
						return "signup";
					}
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				
				return "signup";
			}
		} else {
			System.out.println(bindingResult.getAllErrors());
			
			return "signup";
		}
		return "redirect:/login";
	}

}
