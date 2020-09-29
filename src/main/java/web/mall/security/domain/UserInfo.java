package web.mall.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import web.mall.user.api.entity.Member;

public class UserInfo implements UserDetails{

	private static final long serialVersionUID = 1L;
	private int userIdx;
	private String userId;
	private String userPassword;
	private String userName;
	private int userRank;
	private String userNickname;
	private String userEmail;
	private String userPostNumber;
	private String userAddress;
	private String userAddressDetail;
	private String userPhoneNumber;
	private String userGrant;
	
	public UserInfo() {};
	public UserInfo(Member member) {
		this.userIdx = member.getUserIdx();
		this.userId = member.getUserId();
		this.userPassword = member.getUserPassword();
		this.userName = member.getUserName();
		this.userNickname = member.getUserNickname();
		this.userEmail = member.getUserEmail();
		this.userGrant = member.getUserGrant();
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        String roleGrant = "ROLE_" + userGrant;
        
        GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(myGrant);
        
        return authorities;
    }
	@Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return userPassword;
    }
    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return userId;
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
	public int getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserRank() {
		return userRank;
	}
	public void setUserRank(int userRank) {
		this.userRank = userRank;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPostNumber() {
		return userPostNumber;
	}
	public void setUserPostNumber(String userPostNumber) {
		this.userPostNumber = userPostNumber;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserAddressDetail() {
		return userAddressDetail;
	}
	public void setUserAddressDetail(String userAddressDetail) {
		this.userAddressDetail = userAddressDetail;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public String getUserGrant() {
		return userGrant;
	}
	public void setUserGrant(String userGrant) {
		this.userGrant = userGrant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
