package app.guide.service;

import java.util.List;

import org.springframework.data.domain.Page;

import app.guide.model.Monument;

public interface IMonumentService {
	
	Monument saveMonument(Monument m);
	Monument updateMonument(Monument m);
	void deleteMonument(Monument m);  
	void deleteMonumentById(String id);  
	Monument getMonument(String id);  
	List<Monument> getAllMonuments();  
	
	List<Monument> findByNomM(String nomM);
	Page<Monument> findByNomMContains(String nomM, int page, int size);

	Page<Monument> getAllMonumentsParPage(int page, int size);
	
	public double distance(String codeMA, String codeMB);
	
	
}

