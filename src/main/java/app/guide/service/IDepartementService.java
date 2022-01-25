package app.guide.service;

import java.util.List;

import org.springframework.data.domain.Page;

import app.guide.model.Departement;

public interface IDepartementService {
	
	Departement saveDepartement(Departement d);
	Departement updateDepartement(Departement d);
	void deleteDepartement(Departement d);  
	void deleteDepartementById(String id);  
	Departement getDepartement(String id); 
	List<Departement> getAllDepartements();   
	
	List<Departement> findByDep(String dep);
	Page<Departement> findByNomDepContains(String dep, int page, int size);
	Page<Departement> getAllDepartementsParPage(int page, int size);
}
