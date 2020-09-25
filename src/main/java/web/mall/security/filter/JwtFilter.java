package web.mall.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import web.mall.security.util.JwtUtils;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException{
		String token = request.getHeader("X-AUTH-TOKEN");	//Request Header에서 토큰을 가져옴
		System.out.println(token);
		if(token != null && jwtUtils.validateToken(token)) {
			Authentication authentication = jwtUtils.getAuthentication(token);	//토큰이 유효하면 토큰으로부터 유저 정보를 가져옴.
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

	public JwtFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
}
