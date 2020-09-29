package web.mall.user.api.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import web.mall.user.api.entity.Product;
import web.mall.user.api.service.ProductService;

@RestController
@RequestMapping("/api/")
public class ProductRestController {

	private final ProductService productService;
	
	public ProductRestController (ProductService productServce) {
		this.productService = productServce;
	}
	
	@PostMapping(value="/admin/product")
	@ApiOperation(value = "상품 등록")	
	public ResponseEntity<Product> setProduct(Product product,@RequestParam("productFile")MultipartFile file) throws IOException,FileNotFoundException {
		return ResponseEntity.ok(productService.setProductInfo(product,file)); 
	}
	
	@DeleteMapping(value="/admin/product/{productId}")
	@ApiOperation(value="상품 삭제")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId) throws IllegalArgumentException{
		if(productId == null) {
			throw new IllegalArgumentException ();
		}
		productService.deleteProduct(productId);
		return ResponseEntity.ok("ok");
	}
	
	@PutMapping(value="/admin/product/{productId}")
	@ApiOperation(value="상품 수정")
	public ResponseEntity<Product> updateProduct(Product product,@PathVariable("productId") Integer productId) throws IllegalArgumentException{
		if(productId == null) {
			throw new IllegalArgumentException ();
		}
		Product temProduct = productService.updateProductInfo(product,productId);
		return ResponseEntity.ok(temProduct);
	}
	
	@GetMapping(value="/admin/product")
	@ApiOperation(value = "카테고리별 상품 목록")
	public ResponseEntity<List<Product>> getProductList(@RequestParam("categoryNum")Integer categoryNum) throws IllegalArgumentException {
		if(categoryNum == null) {
			throw new IllegalArgumentException ();
		}
		return ResponseEntity.ok(productService.getProductInfoList(categoryNum));
	}
}
