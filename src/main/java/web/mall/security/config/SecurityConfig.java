package web.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import web.mall.security.filter.JwtFilter;

/*
 *  FrameWork : Spring Boot , Spring Security
 *  Language : Java 1.8
 *  DataBase : MySQL
 *  ORM : Spring Data JPA
 *  Bulid : Maven
 *  API DOC : Swagger
 *  Version Control : Git
 *  Server : AWS EC2
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtFilter jwtFilter; 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)	//쿠키 - 세션 방식 사용 안함
		.and()
			.httpBasic().disable()
			.authorizeRequests()	//리소스 URL 접근 권한 설정
			.antMatchers("/api/all/**").permitAll()
			.antMatchers("/api/admin/**").hasRole("ADMIN")
			.antMatchers("/api/user/**").hasRole("USER")
			.anyRequest().authenticated()
		.and().csrf()
			.disable();
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//jwt 필터가 우선 실행되도록 설정
	}
	@Override	//security 에서 swagger 막지 못하도록 설정
	public void configure(WebSecurity web) throws Exception{
		super.configure(web); web.ignoring().antMatchers("/assest/**"
				,"/v2/api-docs"
				, "/configuration/ui"
				, "/swagger-resources/**"
				, "/configuration/security"
				, "/swagger-ui.html"
				, "/webjars/**");
	}
}

		