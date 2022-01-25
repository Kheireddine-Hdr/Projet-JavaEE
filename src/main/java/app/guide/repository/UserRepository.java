package app.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.guide.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{  //JpA pour acceder à CRUD database
	User findByEmail(String email);
}