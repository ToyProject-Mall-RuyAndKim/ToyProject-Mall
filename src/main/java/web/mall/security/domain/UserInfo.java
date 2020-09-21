package web.mall.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import web.mall.user.api.domain.Member;

public class UserInfo implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_idx;
	private String user_id;
	private String user_password;
	private String user_name;
	private int user_rank;
	private String user_nickname;
	private String user_email;
	private String user_post_number;
	private String user_address;
	private String user_address_detail;
	private String user_phone_number;
	private String user_grant;
	
	public UserInfo(Member member) {
		this.user_idx = member.getUser_idx();
		this.user_id = member.getUser_id();
		this.user_password = member.getUser_password();
		this.user_name = member.getUser_name();
		this.user_nickname = member.getUser_nickname();
		this.user_email = member.getUser_email();
		this.user_grant = member.getUser_grant();
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        String roleGrant = "ROLE_" + user_grant;
        
        GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(myGrant);
        
        return authorities;
    }
	@Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user_password;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user_id;
    }
  //계정이 만료되지 않았는지를 확인
	@Override	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정 잠김 여부 확인
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	//비밀번호 만료 여부 확인
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정 활성화 여부
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_rank() {
		return user_rank;
	}
	public void setUser_rank(int user_rank) {
		this.user_rank = user_rank;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_post_number() {
		return user_post_number;
	}
	public void setUser_post_number(String user_post_number) {
		this.user_post_number = user_post_number;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_address_detail() {
		return user_address_detail;
	}
	public void setUser_address_detail(String user_address_detail) {
		this.user_address_detail = user_address_detail;
	}
	public String getUser_phone_number() {
		return user_phone_number;
	}
	public void setUser_phone_number(String user_phone_number) {
		this.user_phone_number = user_phone_number;
	}
	public String getUser_grant() {
		return user_grant;
	}
	public void setUser_grant(String user_grant) {
		this.user_grant = user_grant;
	}
}
