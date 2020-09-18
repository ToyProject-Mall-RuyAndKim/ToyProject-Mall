package web.mall.user.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mall.user.api.domain.Member;

public interface UserRepository extends JpaRepository<Member,Integer>{

}
