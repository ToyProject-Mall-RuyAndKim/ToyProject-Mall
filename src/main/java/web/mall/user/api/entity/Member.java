package web.mall.user.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="tb_user")
public class Member{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_idx")
	private int userIdx;
	@Column(name="user_id")
	private String userId;
	@Column(name="user_password")
	private String userPassword;
	@Column(name="user_name")
	private String userName;
	@Column(name="user_rank")
	private int userRank;
	@Column(name="user_nickname")
	private String userNickname;
	@Column(name="user_email")
	private String userEmail;
	@Column(name="user_post_number")
	private String userPostNumber;
	@Column(name="user_address")
	private String userAddress;
	@Column(name="user_address_detail")
	private String userAddressDetail;
	@Column(name="user_phone_number")
	private String userPhoneNumber;
	@Column(name="user_begin_date")
	private Date userBeginDate;
	@Column(name="user_grant")
	private String userGrant;
		
	public Member() {}
	
	@PrePersist
	private void onCreate() {
		this.userBeginDate = new Date();
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

	public Date getUserBeginDate() {
		return userBeginDate;
	}

	public void setUserBeginDate(Date userBeginDate) {
		this.userBeginDate = userBeginDate;
	}

	public String getUserGrant() {
		return userGrant;
	}

	public void setUserGrant(String userGrant) {
		this.userGrant = userGrant;
	}
}
