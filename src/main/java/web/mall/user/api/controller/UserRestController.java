package web.mall.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import web.mall.user.api.model.UserVO;
import web.mall.user.api.service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ResponseEntity<String> signup(UserVO userVO){
		userService.setUser(userVO);
		return ResponseEntity.ok("ok");
	}
}
