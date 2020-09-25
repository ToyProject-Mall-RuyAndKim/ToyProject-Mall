package web.mall.user.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import web.mall.user.api.domain.Product;
import web.mall.user.api.service.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/admin/product",method = RequestMethod.POST)
	@ApiOperation(value = "상품 등록 API(Product) return Product")
	public Product setProduct(Product product,@RequestParam("productFile")MultipartFile file) throws Exception {
		
		return productService.setProductInfo(product,file); 
	}
}
