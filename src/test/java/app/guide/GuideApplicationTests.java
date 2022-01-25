package app.guide;

import app.guide.model.Celebrite;
import app.guide.model.Monument;
import app.guide.model.Lieu;
import app.guide.model.Departement;

import app.guide.repository.CelebriteRepository;
import app.guide.repository.MonumentRepository;
import app.guide.repository.LieuRepository;
import app.guide.repository.DepartementRepository;

import app.guide.service.ICelebriteService;
import app.guide.service.IMonumentService;
import app.guide.service.ILieuService;
import app.guide.service.IDepartementService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class GuideApplicationTests {

	
	@Autowired   
	private CelebriteRepository celebriteRepository; 
		
	@Autowired 
	private MonumentRepository monumentRepository;
	
	@Autowired 
	private LieuRepository LieuRepository;
		
	@Autowired 
	private DepartementRepository depRepository;
	
	//--------------------------------- Lieu :----------------------------------------------------
	
		@Test
		public void testFindLieu() {
			Lieu l = LieuRepository.findById("34172").get();
			System.out.println(l);
		}
		
		@Test
		public void testFindAllLieux() {
			List<Lieu> lx = LieuRepository.findAll();
		
			for (Lieu l: lx) {
				System.out.println(l);
			}
		}
		
	//--------------------------------- Departement :----------------------------------------------------
		
		@Test
		public void testFindDepartement() {
			Departement d = depRepository.findById("34").get();
			System.out.println(d);
		} 
		
		@Test
		public void testFindAllDepartements() {
			List<Departement> deps = depRepository.findAll();
		
			for (Departement d: deps) {
				System.out.println(d);
			}
		} 
		
	//--------------------------------- Celebrité :----------------------------------------------------
	
		@Test
		public void testCreationCelebrite() {
			Celebrite c = new  Celebrite(30, "Fabre", "François-Xavier", "Française", "18ème");
			
			celebriteRepository.save(c);  // Jpa repository fourni toute les methodes necessaire 
		}
		
		@Test
		public void testFindCelebrite() {
			Celebrite c = celebriteRepository.findById(30).get();
			System.out.println(c);
		
		}
		
		@Test
		public void testUpdateCelebrite() {
			Celebrite c = celebriteRepository.findById(30).get();
			c.setEpoque("19ème");
			celebriteRepository.save(c);
			System.out.println(c);
		}
		
		@Test
		public void testDeleteCelebrite() {
			celebriteRepository.deleteById(30);
			
		} 
		
	
		@Test
		public void testFindAllCelebrite() {
			List<Celebrite> celebs = celebriteRepository.findAll();
		
			for (Celebrite c:celebs)
				System.out.println(c);
		}
	
	//--------------------------------- Monument :----------------------------------------------------
	
		@Test
		public void testFindMonument() {
			Monument m = monumentRepository.findById("spdrjzvhx3eu").get();
			System.out.println(m);
		} 
		
		@Test
		public void testFindAllMonuments() {
			List<Monument> mnts = monumentRepository.findAll();
		
			for (Monument m: mnts) {
				System.out.println(m);
			}
		} 
	
}


	

