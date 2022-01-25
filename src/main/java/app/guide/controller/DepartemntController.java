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

import app.guide.model.Departement;
import app.guide.service.IDepartementService;

@Controller
public class DepartemntController {

	@Autowired 
	IDepartementService iDepartementService;

	
	@RequestMapping("/showCreateD") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String showCreate(ModelMap modelMap) throws ParseException {
		modelMap.addAttribute("mode", "new");
		return "Departements/formDepartement";
	}

	
	@RequestMapping("/saveDepartement") 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String saveDepartement(@ModelAttribute("departement") Departement departement, 

			ModelMap modelMap) throws ParseException {

		Departement saveDepartement = iDepartementService.saveDepartement(departement); 
		String msg = "clebrite enregistré avec Id " + saveDepartement.getDep();
		modelMap.addAttribute("msg", msg); 
		modelMap.addAttribute("mode", "new");
		return "Departements/formDepartement";

	}

	
	@RequestMapping("/ListeDepartements")
	public String listeDepartements(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "8") int size) {
		
		Page<Departement> depts = iDepartementService.getAllDepartementsParPage(page, size);
		modelMap.addAttribute("departements", depts);
		modelMap.addAttribute("pages", new int[depts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "Departements/listeDepartements";
	}

	
	@RequestMapping("/SearchDepartments")
	public String searchDepartments(ModelMap modelMap, @RequestParam("searchKeyword") String keyword,
								  @RequestParam(name = "page", defaultValue = "0") int page,
								  @RequestParam(name = "size", defaultValue = "8") int size) {
		Page<Departement> mnmts = iDepartementService.findByNomDepContains(keyword, page, size);
		modelMap.addAttribute("departements", mnmts);
		modelMap.addAttribute("pages", new int[mnmts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("searchKeyword", keyword);
		return "Departements/listeDepartements";
	}

	
	@RequestMapping("/supprimerDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String supprimerDepartement(@RequestParam("id") String id, ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "8") int size) {

		iDepartementService.deleteDepartementById(id);

		Page<Departement> depts = iDepartementService.getAllDepartementsParPage(page, size);
		modelMap.addAttribute("departements", depts);
		modelMap.addAttribute("pages", new int[depts.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "Departements/listeDepartements";
	}

	
	@RequestMapping("/modifierDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String editerDepartement(@RequestParam("id") String id, ModelMap modelMap) {

		Departement d = iDepartementService.getDepartement(id);
		modelMap.addAttribute("departement", d);
		modelMap.addAttribute("mode", "edit");
		return "Departements/formDepartement";
	}

	
	@RequestMapping("/showSearch") 
	public String showFindDepartment(ModelMap modelMap) throws ParseException {
		System.out.println("Show Search");

		return "Departements/findDepartement";
	}

	
	@RequestMapping("/updateDepartement")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateDepartement(@ModelAttribute("departement") Departement departement, ModelMap modelMap) { 
		
		iDepartementService.updateDepartement(departement);
		List<Departement> depts = iDepartementService.getAllDepartements();
		modelMap.addAttribute("departements", depts);
		return "Departements/ListeDepartements";

	}

}
