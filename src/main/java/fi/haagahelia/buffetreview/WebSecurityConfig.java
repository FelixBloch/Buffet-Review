package fi.haagahelia.buffetreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.haagahelia.buffetreview.domain.UserDetailServiceImpl;

/**
 * 
 * @author Test Felix Bloch felix.z.bloch@gmail.com
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Defines which paths are secured and the path to the login page.
	 * The signup page does not require authentication.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll().and().authorizeRequests().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/reviews").permitAll().and()
				.logout().permitAll();
	}

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	/**
	 * Enables BCrypt as the encoder for passwords.
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
