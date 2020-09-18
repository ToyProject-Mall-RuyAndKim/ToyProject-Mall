package web.mall.user.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mall.user.api.domain.Member;
import web.mall.user.api.model.UserVO;
import web.mall.user.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void setUser(UserVO userVO) {
		Member member = new Member();
		member.setUser_id(userVO.getUser_id());
		member.setUser_password(userVO.getUser_password());
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
}
