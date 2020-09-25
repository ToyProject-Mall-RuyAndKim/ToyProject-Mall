package web.mall.user.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping(value="/admin/product")
	@ApiOperation(value = "상품 등록 API(Product) return Product")	
	public Product setProduct(Product product,@RequestParam("productFile")MultipartFile file) throws IOException,FileNotFoundException {	//Exception 구체화 필요 (throws Exception 절대 x)
		return productService.setProductInfo(product,file); 
	}
	@GetMapping(value="/admin/product")
	@ApiOperation(value = "카테고리별 상품 목록(int CategoryNum) return List<Product>")
	public List<Product> getProductList(@RequestParam("categoryNum")Integer categoryNum) throws IllegalArgumentException {
		if(categoryNum == null) {
			throw new IllegalArgumentException ();
		}
		return productService.getProductInfoList(categoryNum);
	}
}
