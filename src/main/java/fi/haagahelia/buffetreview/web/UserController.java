package fi.haagahelia.buffetreview.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.buffetreview.domain.SignupForm;
import fi.haagahelia.buffetreview.domain.User;
import fi.haagahelia.buffetreview.domain.UserRepository;
import fi.haagahelia.buffetreview.recaptcha.ReCaptchaService;

@Controller
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(value = "signup")
    public String addUser(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }
	
	@RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult/*,
    		@RequestParam(name="g-recaptcha-response") String reCaptchaResponse, HttpServletRequest request*/) {
    	if (!bindingResult.hasErrors()) { // Validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // Check if passwords match		
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
		    	
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
		    		if (repository.findByUsername(signupForm.getEmail()) == null) { // Check if email exists
		    			/*String ip = request.getRemoteAddr();
		    			
		    			String captchaVerifyMessage = ReCaptchaService.verifyRecaptcha(ip, reCaptchaResponse);
		    			
		    			if (StringUtils.isEmpty(captchaVerifyMessage)) {
		    		    Map<String, Object> response = new HashMap<>();
		    		    response.put("message", captchaVerifyMessage);
		    		    
		    		    bindingResult.rejectValue("captcha", "err.captcha", "reCAPTCHA failed.");    	
		    			return "signup";
		    		    }*/
		    			
		    			repository.save(newUser);
			    		
			    	}
			    	else {
		    			bindingResult.rejectValue("email", "err.email", "Email already exists");    	
		    			return "signup";		    		
			    	}
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		System.out.println(bindingResult.getAllErrors());
    		return "signup";
    	}
    	return "redirect:/login";    	
    }
}
