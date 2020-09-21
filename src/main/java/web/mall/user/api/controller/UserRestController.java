package web.mall.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.mall.security.domain.UserInfo;
import web.mall.security.service.MemberService;

@RestController
@RequestMapping(value="/api/user")
public class UserRestController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ResponseEntity<String> signup(UserInfo userVO){
		memberService.setUser(userVO);
		return ResponseEntity.ok("ok");
	}
}
