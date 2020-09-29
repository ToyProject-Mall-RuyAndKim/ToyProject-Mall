package web.mall.user.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_product_category")
public class ProductCategory {
	@Id
	@Column(name="category_name")
	private String categoryName;
	@Column(name="category")
	private String category;
	@Column(name="category_ref")
	private String categoryRef;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategoryRef() {
		return categoryRef;
	}
	public void setCategoryRef(String categoryRef) {
		this.categoryRef = categoryRef;
	}
	
}
