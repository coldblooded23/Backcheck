package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.PrivilegesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.RolesInfo;
import com.bgc.backgroundVerification.model.UserDetailsInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.RolesService;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
public class RolesController {

	@Autowired
	RolesService rolesService;

	@GetMapping("/roles")

	public ModelAndView getlistOfRoles(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			ModelAndView model = new ModelAndView("rolees");

			String jwtToken = AddAndRetriveCookieService.readAdminServletCookie(request);
			System.out.println(jwtToken + " " + principal.getName());
			UserDetailsInfo details = rolesService.getRoleByEmail(principal.getName(), jwtToken);
			model.addObject("userInfo", details);

			return model;
		} catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
	}

	@GetMapping("/roles/{id}")
	public ModelAndView addRolesPage(@PathVariable Long id, HttpServletRequest request) {

		try {
			ModelAndView model = new ModelAndView("addRoles");
			String jwtToken = AddAndRetriveCookieService.readAdminServletCookie(request);

			RolesInfo roles = new RolesInfo();
			roles.setCustomerId(id);
			List<PrivilegesInfo> privilages = rolesService.listofPrivilages(jwtToken);
			model.addObject("roles", roles);
			model.addObject("ListOfprivilages", privilages);
			return model;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());

		}

	}

	@PostMapping("/roles/addRoles")
	public ModelAndView addRole(@ModelAttribute RolesInfo roles, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			ModelAndView model = new ModelAndView("redirect:/roles/" + roles.getCustomerId());
			String jwtToken = AddAndRetriveCookieService.readAdminServletCookie(request);
			ResponseInfo responce = rolesService.addARole(roles, jwtToken);
			if (responce.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				redirectAttributes.addFlashAttribute("AddSuccess", "success");
			} else {
				redirectAttributes.addFlashAttribute("Addfailed", "failed");
				redirectAttributes.addFlashAttribute("message", responce.getAlertMessage());
			}

			return model;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
			// TODO: handle exception
		}

	}
	
	@PostMapping("/roles/updateRoles")
	public ModelAndView UpdateRole(@ModelAttribute RolesInfo roles, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		try {
			
			String jwtToken = AddAndRetriveCookieService.readAdminServletCookie(request);
			ResponseInfo responce = rolesService.updateARole(roles, jwtToken);
			if (responce.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				ModelAndView model = new ModelAndView("redirect:/roles");				
				redirectAttributes.addFlashAttribute("UpdateSuccess", "success");
				return model;
			} else {
				ModelAndView model = new ModelAndView("redirect:/roles/updateRole/" + roles.getRolesId());
				
				redirectAttributes.addFlashAttribute("Updatefailed", "failed");
				redirectAttributes.addFlashAttribute("message", responce.getAlertMessage());
				return model;
			}

			
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
			// TODO: handle exception
		}

	}
	
	
@GetMapping("/roles/updateRole/{id}")
public ModelAndView getRoleById(@PathVariable Long id, HttpServletRequest request) {
	try {
	ModelAndView model = new ModelAndView("updateRole");
	String jwtToken = AddAndRetriveCookieService.readAdminServletCookie(request);
   RolesInfo roles=rolesService.getRolebyId(id, jwtToken);
	List<PrivilegesInfo> privilages = rolesService.listofPrivilages(jwtToken);
	model.addObject("roles", roles);
	model.addObject("ListOfprivilages", privilages);
	return model;
	}catch (Exception e) {
		// TODO: handle exception
		throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		// TODO: handle exception

	}
	}



@PostMapping("/roles/deleteRole")
public ModelAndView deleterole( @RequestParam String emailId,HttpServletRequest request,RedirectAttributes redirectAttributes) throws ParseException {
	String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
	ResponseInfo response=rolesService.deleteRole(emailId, jwtToken);
	ModelAndView model =  new ModelAndView("redirect:/roles");
	  if(response.getAlertMessage().equals(Constants.AuthenticationSuccess))
	  {
		  redirectAttributes.addFlashAttribute("DeleteSuccess", "Success");
	   System.out.println("11111111");
	  }else {
		  redirectAttributes.addFlashAttribute("Addfailed", "failed");
		  redirectAttributes.addFlashAttribute("message",response.getAlertMessage());
	  }
	 
	return model;
	
}

}
