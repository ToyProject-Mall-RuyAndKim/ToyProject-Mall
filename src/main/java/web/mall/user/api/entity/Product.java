package web.mall.user.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Table(name="tb_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiParam(value="입력 필요 X")
	@Column(name="product_idx")
	private int productIdx;
	@Column(name="product_name")
	private String productName;
	@Column(name="product_price")
	private int productPrice;
	@Column(name="product_category")
	private int productCategory;
	@ApiParam(value="입력 필요 X")
	@Column(name="product_img_path")
	private String productImgPath;
	@ApiParam(value="입력 필요 X")
	@Column(name="product_begin_date")
	private Date productBeginDate;
	@Column(name="product_img_name")
	private String prudctImgName;
	
	public void update(Product product) {
		this.productName = product.getProductName();
		this.productPrice = product.getProductPrice();
		this.productCategory = product.getProductCategory();
	}
	@PrePersist
	private void onCreate() {
		this.productBeginDate = new Date();
	}
	public int getProductIdx() {
		return productIdx;
	}
	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(int productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductImgPath() {
		return productImgPath;
	}
	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}
	public Date getProductBeginDate() {
		return productBeginDate;
	}
	public void setProductBeginDate(Date productBeginDate) {
		this.productBeginDate = productBeginDate;
	}
	public String getPrudctImgName() {
		return prudctImgName;
	}
	public void setPrudctImgName(String prudctImgName) {
		this.prudctImgName = prudctImgName;
	}
}
