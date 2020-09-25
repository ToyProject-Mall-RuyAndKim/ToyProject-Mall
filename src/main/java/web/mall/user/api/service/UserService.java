package web.mall.user.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mall.security.domain.UserInfo;
import web.mall.user.api.domain.Member;
import web.mall.user.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
}
