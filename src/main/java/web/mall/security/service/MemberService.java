package web.mall.security.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public void setUser(UserInfo userVO) {
		
		Member member = new Member();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		member.setUserId(userVO.getUserId());
		member.setUserPassword(passwordEncoder.encode(userVO.getUserPassword()));
		member.setUserName(userVO.getUserName());
		member.setUserRank(0);
		member.setUserNickname(userVO.getUserNickname());
		member.setUserEmail(userVO.getUserEmail());
		member.setUserPostNumber(userVO.getUserPostNumber());
		member.setUserAddress(userVO.getUserAddress());
		member.setUserAddressDetail(userVO.getUserAddressDetail());
		member.setUserPhoneNumber(userVO.getUserPhoneNumber());
		member.setUserGrant("USER");
		
		userRepository.save(member);
	}
	
	//스프링이 로그인 요청을 가로챌 때,username,password 변수 2개를 가로채는데
	//password 부분 처리는 알아서함
	//username이 DB에 존재하는지 확인해서 return 필요
	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Member member = userRepository.findByUserName(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username);
				});
		if(member.getUserId().equals(username)) {
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(member.getUserId(),member.getUserPassword(),roles);	//ArrayList = role
		}else {
			throw new UsernameNotFoundException("User not found with username: " +username);
		}
	}
}
