package app.guide.service;

import java.util.List;

import org.springframework.data.domain.Page;

import app.guide.model.Lieu;

public interface ILieuService {
	
	Lieu saveLieu(Lieu l);
	Lieu updateLieu(Lieu l);
	void deleteLieu(Lieu l);  
	void deleteLieuById(String id);  
	Lieu getLieu(String id);  
	List<Lieu> getAllLieux();   
	
	List<Lieu> findByCodeInsee(String CodeInsee);
	Page<Lieu> findByNomComContains(String nomCom, int page, int size);
	Page<Lieu> getAllLieuxParPage(int page, int size);

}


