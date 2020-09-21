package web.mall.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import web.mall.security.domain.UserInfo;
import web.mall.user.api.domain.Member;
import web.mall.user.api.repository.UserRepository;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public void setUser(UserInfo userVO) {
		Member member = new Member();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		member.setUser_id(userVO.getUser_id());
		member.setUser_password(passwordEncoder.encode(userVO.getUser_password()));
		member.setUser_name(userVO.getUser_name());
		member.setUser_rank(0);
		member.setUser_nickname(userVO.getUser_nickname());
		member.setUser_email(userVO.getUser_email());
		member.setUser_post_number(userVO.getUser_post_number());
		member.setUser_address(userVO.getUser_address());
		member.setUser_address_detail(userVO.getUser_address_detail());
		member.setUser_phone_number(userVO.getUser_phone_number());
		
		userRepository.save(member);
	}
	
	//스프링이 로그인 요청을 가로챌 때,username,password 변수 2개를 가로채는데
	//password 부분 처리는 알아서함
	//username이 DB에 존재하는지 확인해서 return 필요
	@Override	
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException{
		Member member = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username);
				});
		
		return new UserInfo(member);	// 시큐리티의 세션에 유저 정보 저장
	}
}
