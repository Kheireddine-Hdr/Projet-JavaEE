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

import app.guide.model.Lieu;
import app.guide.service.ILieuService;


@Controller
public class LieuController {


    @Autowired
    ILieuService iLieuService;


    @RequestMapping("/showCreateL")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showCreate(ModelMap modelMap) throws ParseException {
        modelMap.addAttribute("newLieu", new Lieu());
        modelMap.addAttribute("mode", "new");
        return "Lieux/formLieu";
    }

    
    @RequestMapping("/saveLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveLieu(@ModelAttribute("newLieu") Lieu lieu, 

                           ModelMap modelMap) throws ParseException {

        Lieu saveLieu = iLieuService.saveLieu(lieu);  
        String msg = "Lieu enregistré avec codeM " + saveLieu.getCodeInsee();
        modelMap.addAttribute("msg", msg); 
        modelMap.addAttribute("mode", "new");
        return "Lieux/formLieu";

    }
    

    @RequestMapping("/ListeLieux")
    public String listeLieus(ModelMap modelMap,
                             @RequestParam(name = "page", defaultValue = "0") int page,
                             @RequestParam(name = "size", defaultValue = "8") int size) {
        
        Page<Lieu> lieux = iLieuService.getAllLieuxParPage(page, size);
        modelMap.addAttribute("lieux", lieux);
        modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "Lieux/listeLieux";
    }

    
	@RequestMapping("/SearchLieux")
	public String searchMonuments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "8") int size) {
		Page<Lieu> lieux = iLieuService.findByNomComContains(keyword, page, size);
		modelMap.addAttribute("lieux", lieux);
		modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Lieux/listeLieux";
	}

	
    @RequestMapping("/supprimerLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String supprimerLieu(@RequestParam("id") String id,
                                ModelMap modelMap,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "8") int size) {
        
        iLieuService.deleteLieuById(id);

        Page<Lieu> lieux = iLieuService.getAllLieuxParPage(page, size);
        modelMap.addAttribute("lieux", lieux);
        modelMap.addAttribute("pages", new int[lieux.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);
        return "Lieux/listeLieux";
    }

    
    @RequestMapping("/modifierLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editerLieu(@RequestParam("id") String id,
                             ModelMap modelMap) {
        Lieu l = iLieuService.getLieu(id);
        modelMap.addAttribute("lieu", l);
        modelMap.addAttribute("mode", "edit");
        return "Lieux/formLieu";
    }

    
    @RequestMapping("/updateLieu")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateLieu(@ModelAttribute("lieu") Lieu lieu,
                             ModelMap modelMap) {
        iLieuService.updateLieu(lieu);
        List<Lieu> lieux = iLieuService.getAllLieux();
        modelMap.addAttribute("lieux", lieux);
        return "Lieux/ListeLieux";


    }

}
