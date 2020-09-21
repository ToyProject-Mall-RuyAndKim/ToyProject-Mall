package web.mall.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import web.mall.security.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private MemberService memberService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()	//리소스 URL 접근 권한 설정
			.antMatchers("/user/**").permitAll()
			.antMatchers("/api/**").hasRole("ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and().logout()
			.logoutUrl("/logout")
			.invalidateHttpSession(true) //HTTP 세션 초기화
		.and().csrf()
			.disable();
	}
	// 입력된 password 인코딩과 db에 인코딩된 password 해쉬 확인
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}

}

		