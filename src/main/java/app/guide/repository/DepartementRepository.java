package app.guide.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.guide.model.Departement;


public interface DepartementRepository extends JpaRepository<Departement, String> {

	
	List<Departement> findByDep(String dep );

	List<Departement> findByDepContains(String dep);
	Page<Departement> findByDepContains(String dep, Pageable pageable);
	Page<Departement> findByNomDepContains(String dep, Pageable pageable);
}
