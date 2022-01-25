package app.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.guide.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
