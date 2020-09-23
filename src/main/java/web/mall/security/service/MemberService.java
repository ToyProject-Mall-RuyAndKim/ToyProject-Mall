package web.mall.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
	
	public void setUser(UserInfo userInfo) throws Exception{
		
		if(userRepository.findByUserId(userInfo.getUserId()) != null) {
			throw new Exception();
		}
		Member member = new Member();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		member.setUserId(userInfo.getUserId());
		member.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
		member.setUserName(userInfo.getUserName());
		member.setUserRank(0);
		member.setUserNickname(userInfo.getUserNickname());
		member.setUserEmail(userInfo.getUserEmail());
		member.setUserPostNumber(userInfo.getUserPostNumber());
		member.setUserAddress(userInfo.getUserAddress());
		member.setUserAddressDetail(userInfo.getUserAddressDetail());
		member.setUserPhoneNumber(userInfo.getUserPhoneNumber());
		member.setUserGrant("USER");
		
		userRepository.save(member);
	}
	public String checkUserExist(String userId) {	//유저 존재 여부 확인
		System.out.println(userRepository.findByUserId(userId).isPresent());
		if(userRepository.findByUserId(userId).isPresent()) { return "fail"; }
		else { return "ok";}
	}
	//스프링이 로그인 요청을 가로챌 때,username,password 변수 2개를 가로채는데
	//password 부분 처리는 알아서함
	//username이 DB에 존재하는지 확인해서 return 필요
	@Override	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
		Member member = userRepository.findByUserId(userId)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+userId);
				});
		if(member.getUserId().equals(userId)) {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(member.getUserId(),member.getUserPassword(),roles);	//ArrayList = role
		}else {
			throw new UsernameNotFoundException("User not found with username: " +userId);
		}
	}
}
