package app.guide.service;

import java.util.List;

import app.guide.model.Celebrite;
import app.guide.repository.CelebriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service 
public class CelebriteServiceImpl implements ICelebriteService {
		
	@Autowired // j'ordonne à Spring d'injecter cet objet Celebreposy grace à @Autowired 
    CelebriteRepository celebriteRepository;  // pour realiser les fonctionnalité CRUD
		
	@Override
	public Celebrite saveCelebrite(Celebrite c) {
		
		return celebriteRepository.save(c) ;
	}

	@Override
	public Celebrite updateCelebrite(Celebrite c) {
	
		return celebriteRepository.save(c) ;    // si l'objet est null donc il vat lancer un insert
												// si l'objet est deja enregister donc un update
	}										    // save va joué un double role.

	@Override
	public void deleteCelebrite(Celebrite c) {
		 celebriteRepository.delete(c);
		
	}

	@Override
	public void deleteCelebriteById(Integer id) {
		celebriteRepository.deleteById(id); ;
		
	}

	@Override
	public Celebrite getCelebrite(Integer id) {
		return celebriteRepository.findById(id).get(); 
	}

	@Override
	public List<Celebrite> getAllCelebrites() {
		return celebriteRepository.findAll() ;
	}

	@Override
	public Page<Celebrite> getAllCelebritesParPage(int page, int size) {
		
		return celebriteRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Celebrite> findByNom(String nom) {
	
		return celebriteRepository.findByNom(nom);
	}

	@Override
	public Page<Celebrite> findByNomContains(String nom, int page, int size) {
		return celebriteRepository.findByNomContains(nom, PageRequest.of(page, size));
	}

}
