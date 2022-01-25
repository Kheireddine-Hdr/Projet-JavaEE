package app.guide.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.guide.model.Celebrite;
import app.guide.model.Monument;
import app.guide.service.ICelebriteService;
import app.guide.service.IMonumentService;

@Controller
public class CelebriteCotroller {


    @Autowired  
    ICelebriteService celebriteService;

    @Autowired  
    IMonumentService iMonumentService;

    
    @RequestMapping("/showCreate")  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreate(ModelMap modelMap) throws ParseException {
        List<Monument> mnmts = iMonumentService.getAllMonuments();
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("monuments", mnmts);
        return "Celebrites/formCelebrite";
    }

    
    @RequestMapping("/saveCelebrite")  
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCelebrite(@ModelAttribute("celebrite") Celebrite celebrite, 
                                ModelMap modelMap) throws ParseException {


        Celebrite saveCelebrite = celebriteService.saveCelebrite(celebrite);  
        String msg = "clebrite enregistré avec Id " + saveCelebrite.getNumCelebrite();

        List<Monument> mnmts = iMonumentService.getAllMonuments();

        modelMap.addAttribute("msg", msg); 
        modelMap.addAttribute("mode", "new");
        modelMap.addAttribute("monuments", mnmts);
        return "Celebrites/formCelebrite";

    }

    
    @RequestMapping("/ListeCelebrites")
    public String listeCelebrites(ModelMap modelMap,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<Celebrite> celebs = celebriteService.getAllCelebritesParPage(page, size);
        modelMap.addAttribute("celebrites", celebs);
        modelMap.addAttribute("pages", new int[celebs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Celebrites/listeCelebrites";
    }

    
	@RequestMapping("/SearchCelebrites")
	public String searchMonuments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "5") int size) {
		Page<Celebrite> celebs = celebriteService.findByNomContains(keyword, page, size);
		modelMap.addAttribute("celebrites", celebs);
		modelMap.addAttribute("pages", new int[celebs.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Celebrites/listeCelebrites";
	}

	
    @RequestMapping("/supprimerCelebrite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String supprimerCelebrite(@RequestParam("id") Integer id,
                                     ModelMap modelMap,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "5") int size) {

        celebriteService.deleteCelebriteById(id);

        Page<Celebrite> celebs = celebriteService.getAllCelebritesParPage(page, size);
        modelMap.addAttribute("celebrites", celebs);
        modelMap.addAttribute("pages", new int[celebs.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "Celebrites/listeCelebrites";
    }

    
    @RequestMapping("/modifierCelebrite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editerCelebrite(@RequestParam("id") Integer id,
                                  ModelMap modelMap) {
        Celebrite c = celebriteService.getCelebrite(id);
        List<Monument> monuments = iMonumentService.getAllMonuments();
        modelMap.addAttribute("monuments", monuments);
        modelMap.addAttribute("celebrite", c);

        modelMap.addAttribute("mode", "edit");
        return "Celebrites/formCelebrite";
    }

    
    @RequestMapping("/updateCelebrite")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCelebrite(@ModelAttribute("celebrite") Celebrite celebrite,
                                  ModelMap modelMap) {
        celebriteService.updateCelebrite(celebrite);
        List<Celebrite> celebs = celebriteService.getAllCelebrites();
        modelMap.addAttribute("celebrites", celebs);
        List<Monument> mnmts = iMonumentService.getAllMonuments();
        modelMap.addAttribute("monuments", mnmts);
        return "Celebrites/ListeCelebrites";


    }

}
