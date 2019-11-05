package pe.edu.upn.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	@Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/index.html").permitAll()
			.antMatchers("/menuestudiante").hasRole("ALUMNO")
			.antMatchers("/cliente").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/cliente/nuevo").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/cliente/info/**").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/cliente/**/nuevopedido").hasAnyRole("ADMIN","TRABAJADOR")
	
			.antMatchers("/cliente/**/nuevousuario").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/cliente/edit/**").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/cliente/del/**").hasRole("ADMIN")
			.antMatchers("/platos").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/platos/info/**").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/platos/nuevo").hasRole("ADMIN")
			
		
			.antMatchers("/platos/edit/**").hasRole("ADMIN")
			.antMatchers("/platos/del/**").hasRole("ADMIN")
			.antMatchers("/personal").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/personal/**/nuevousuario").hasRole("ADMIN")
			.antMatchers("/personal/nuevo").hasRole("ADMIN")
			.antMatchers("/personal/edit/**").hasRole("ADMIN")
			.antMatchers("/personal/info/**").hasAnyRole("ADMIN","TRABAJADOR")
			.antMatchers("/personal/del/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginProcessingUrl("/signin")
				.loginPage("/login").permitAll()
				.usernameParameter("inputUsername")
                .passwordParameter("inputPassword")
			.and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        	.logoutSuccessUrl("/")
	        .and()
            .rememberMe()
            	.tokenValiditySeconds(2592000)
            	.key("Cl4v3.")
            	.rememberMeParameter("checkRememberMe")
            	.userDetailsService(usuarioDetailsService)
            .and()
                .exceptionHandling()
                    .accessDeniedHandler(accessDeniedHandler);
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder( ) {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.usuarioDetailsService);

        return daoAuthenticationProvider;
    }
	
}
