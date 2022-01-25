package app.guide.service;
import java.util.List;

import org.springframework.data.domain.Page;

import app.guide.model.Celebrite;

public interface ICelebriteService {
	
	Celebrite saveCelebrite(Celebrite c);
	Celebrite updateCelebrite(Celebrite c);
	void deleteCelebrite(Celebrite c); 
	void deleteCelebriteById(Integer id);  
	Celebrite getCelebrite(Integer id); 
	List<Celebrite> getAllCelebrites(); 
	
	List<Celebrite> findByNom(String nom);
	Page<Celebrite> findByNomContains(String nom, int page, int size);
	Page<Celebrite> getAllCelebritesParPage(int page, int size);
}