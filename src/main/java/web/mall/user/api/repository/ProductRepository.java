package web.mall.user.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mall.user.api.entity.Member;
import web.mall.user.api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	public List<Product> findAllByProductCategory(int categoryNum);
}
