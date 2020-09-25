package web.mall.security.util;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import web.mall.security.service.MemberService;

@Component
public class JwtUtils {
	
	private String secretKey ="secret";
	private long tokenValidTime = 30 * 60 * 1000L;
	
	@Autowired
	private MemberService memberService;
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	//JWT 토큰 생성
	public String createToken(String userId) {
		Claims claims = Jwts.claims().setSubject(userId);	//JWT Payload에 저장되는 단위
		claims.put("roles", memberService.getUser(userId).getUserGrant());
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)	//정보 저장
				.setIssuedAt(now)	// 토큰 발행 시간 정보
				.setExpiration(new Date(now.getTime() + tokenValidTime))	//토큰 만료 시간
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	//토큰에서 인증 정보 조회
	public Authentication getAuthentication(String token) {
		System.out.println("getAuthentication");
		UserDetails userDetails = memberService.loadUserByUsername(this.getUserPk(token));
		return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
	}
	//토큰에서 회원 정보 추출
	public String getUserPk(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	public Boolean validateToken(String token) throws JwtException{
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);	//토큰에 담긴 claims 리스트를 가져옴
		return !claims.getBody().getExpiration().before(new Date());
		//토큰의 만료시간이 현재시간보다 작으면 true
	}
}
