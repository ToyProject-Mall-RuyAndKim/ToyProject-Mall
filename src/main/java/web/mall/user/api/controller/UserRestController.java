package web.mall.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import web.mall.security.domain.UserInfo;
import web.mall.security.model.AuthenticationResponse;
import web.mall.security.service.MemberService;
import web.mall.security.util.JwtUtils;

@RestController
@RequestMapping(value="/api/all")
public class UserRestController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private MemberService memberService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@ApiOperation(value = "회원가입 API (UserInfo) Return ok")
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ResponseEntity<String> signup(UserInfo userInfo) throws Exception{
		try{
			memberService.setUser(userInfo);
		}catch(Exception e){
			throw new Exception("Already have userId");
		}
		return ResponseEntity.ok("ok");
	}
	
	@ApiOperation(value = "로그인 API (UserName, UserPassword) Return - JWT Token")
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ResponseEntity<?> logIn(@RequestBody UserInfo userInfo) throws Exception{
		try{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(),userInfo.getPassword()));
			/*
			 * authenticate - UsernamePasswordAuthenticationToken을 기본 AuthenticationProvider로 전달
			 * UserDetailsService를 사용하여 사용자 이름을 기반으로 사용자를 가져오고 해당 사용자의 비밀번호를 인증 토큰의 비밀번호와 비교
			 * Spring Security는 하나의 실제 AuthenticationManager만 구현
			 * */
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}	//id,password 검증 문제있을 경우 throw Exception
		
		final UserDetails userDetails = memberService.loadUserByUsername(userInfo.getUsername());
		final String token = jwtUtils.createToken(userDetails.getUsername());	//유저이름, 권한List를 파라미터로 넣음
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@ApiOperation(value="ID 중복 확인 API (String UserId) Return - 아이디 존재시 fail 없을경우 ok")
	@RequestMapping(value = "/id-check",method = RequestMethod.GET)
	public String checkId(@RequestParam(value="userId",defaultValue="0")String userId){
		
		return memberService.checkUserExist(userId);
	}
}
