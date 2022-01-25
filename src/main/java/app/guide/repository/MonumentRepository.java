package app.guide.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.guide.model.Monument;


public interface MonumentRepository extends JpaRepository<Monument, String> {

	
	List<Monument> findByNomM(String nomM);

	List<Monument> findByNomMContains(String nomM);
	Page<Monument> findByNomMContains(String nomM, Pageable pageable);

}
