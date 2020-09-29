package web.mall.user.api.entity;

import javax.persistence.Entity;

@Entity
public class Cart {
	private int cartIdx;
	private int cartUserIdx;
	private int cartProductIdx;
	private int cartProductAmount;
}
