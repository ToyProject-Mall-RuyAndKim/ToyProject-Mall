package web.mall.user.api.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import web.mall.user.api.domain.Product;
import web.mall.user.api.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	final String baseDir = "C:\\serverImg";
	
	@ApiOperation(value = "카테고리별 상품리스트 Get")
	public List<Product> getProductInfoList(int categoryNum){
		return productRepository.findAllByProductCategory(categoryNum);
	}
	@ApiOperation(value = "상품 등록 API(Product) return Product")
	public Product setProductInfo(Product product,MultipartFile img) throws FileNotFoundException,IOException{
		/* 파일 업로드시 파일명 중복 문제 개선 필요 20.09.25 (UUID 활용 ) */
		String filePath = baseDir + "\\" + img.getOriginalFilename();
		String fileOriginalName = img.getOriginalFilename();
		product.setProductImgPath(filePath);
		product.setPrudct_img_name(fileOriginalName);
		File file = new File(filePath);
		if(!file.exists()) {	// file 경로에 폴더가 없을 경우폴더 생성
			file.mkdirs();
		}
		try {
			img.transferTo(file);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new FileNotFoundException("파일 저장 오류.");
		}catch(IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
		
		return productRepository.save(product);
	}
}
