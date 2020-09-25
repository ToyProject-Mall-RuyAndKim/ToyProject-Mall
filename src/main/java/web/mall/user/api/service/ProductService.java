package web.mall.user.api.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import web.mall.user.api.domain.Product;
import web.mall.user.api.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	final String baseDir = "C:\\serverImg";
	
	public Product setProductInfo(Product product,MultipartFile img) throws Exception{

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
		}catch(Exception e) {
			e.printStackTrace();
			throw new FileNotFoundException("파일 저장 오류.");
		}
		
		return productRepository.save(product);
	}
}
