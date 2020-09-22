package web.mall.user.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.mall.user.api.domain.Member;

@Repository
public interface UserRepository extends JpaRepository<Member,Integer>{
	//findByooo로 시작하면 자동으로 ooo검색 쿼리를 만들어준다.
	public Optional<Member> findByUserId(String userId);
}
