package kelley.josh.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
//					.authorizeRequests()   works to allow all
//					.anyRequest().permitAll();

//				spring boot security
						.httpBasic()
					.and()
						.authorizeRequests()
							.antMatchers("/index", "/home", "/login", "/").permitAll()
							.anyRequest().authenticated();

//			http.authorizeRequests()
//			.antMatchers("/**").authenticated()
//			.and()
//			.formLogin().loginPage("/login").permitAll()
//			.and()
//			.logout().permitAll()
//			.and()
//			.csrf().disable();
			   	
		}
		
		@Configuration
		static protected class LoginController extends WebMvcConfigurerAdapter {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/login").setViewName("login");
			}
		}
}
