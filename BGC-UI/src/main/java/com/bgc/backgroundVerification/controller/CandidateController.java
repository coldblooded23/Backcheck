package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.Candidate_DocumentInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.CustomerInfo;
import com.bgc.backgroundVerification.model.EmployeeInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.model.SsnInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.CandidateService;
import com.bgc.backgroundVerification.service.Candidate_DocumentService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.service.SsnService;
import com.bgc.backgroundVerification.ssnModel.SSNInfo;
import com.bgc.backgroundVerification.util.BgcUIException;

import ch.qos.logback.core.util.SystemInfo;

@Controller
public class CandidateController {

	@Autowired
	CandidateService candidateService;
	
	
	@Autowired
	Candidate_DocumentService documentService;
	
	
	@Autowired
	SsnService ssnService;

	@GetMapping("/candidate")
	public ModelAndView getAllCandidate(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView("candidate");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			CustomerInfo customer = candidateService.getAllCandidateInfo(principal.getName(),jwtToken);
			model.addObject("customerinfo", customer);
			return model;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
	}

	@PostMapping("/candidate/addcandidate")
	public ModelAndView addCandidate(CandidatesInfo candidatesInfo, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView("candidate");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			ResponseInfo response = candidateService.addCandidate(candidatesInfo, principal.getName(),jwtToken);
			if (response.getMessage().equals(Constants.Success)) {
				model.addObject("AddSuccess", "Success");
			} else {
				model.addObject("Addfailed", "failed");
				model.addObject("message", response.getAlertMessage());
			}

			model.addObject("customerinfo", response.getCustomerInfo());
			return model;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}

	}

	@ModelAttribute("candidate")
	public CandidatesInfo candidatesInfo() {
		return new CandidatesInfo();
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/candidate/updatecandidate/{id}")
	public ModelAndView getACandidateInfo(@PathVariable Long id, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("updatecandidate");
		Principal principal = request.getUserPrincipal();
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			CandidatesInfo response = candidateService.getACandidateInfo(id, principal.getName(),jwtToken);
			model.addObject("candidate", response);

		} catch (Exception e) {
			throw new BgcUIException("exeception occered while accrssing the data" + e.getMessage());
		}

		return model;

	}

	@PostMapping("/candidate/updateCandidate")
	public ModelAndView updateEmployee(@ModelAttribute("candidate") CandidatesInfo candidatesInfo,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Principal principal = request.getUserPrincipal();

		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response = candidateService.updateCandidate(candidatesInfo, principal.getName(),jwtToken);

		if (response.getMessage().equals(Constants.Success)) {
			ModelAndView model = new ModelAndView("candidate");
			model.addObject("updateSuccess", "Success");
			model.addObject("customerinfo", response.getCustomerInfo());
			return model;
		} else {
			ModelAndView model = new ModelAndView(
					"redirect:/candidate/updatecandidate/" + candidatesInfo.getCandidateId());

			redirectAttributes.addFlashAttribute("updatefailed", "failed");
			redirectAttributes.addFlashAttribute("message", response.getAlertMessage());

			return model;
		}
	}

	@GetMapping("/candidate/updateresume/{id}")
	public ModelAndView getResumePage(@PathVariable String id, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("uploadresume");
		model.addObject("candidateId", id);
		return model;

	}

	@PostMapping("/candidate/updateresume/{id}")
	public ModelAndView updateEmployee(@RequestParam("file") MultipartFile file, @PathVariable String id,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Principal principal = request.getUserPrincipal();

		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response = candidateService.updateResume(file, id, principal.getName(),jwtToken);

		if (response.getMessage().equals(Constants.Success)) {
			ModelAndView model = new ModelAndView("candidate");
			model.addObject("ResumeSuccess", "Success");
			model.addObject("customerinfo", response.getCustomerInfo());
			return model;
		} else {
			ModelAndView model = new ModelAndView("redirect:/candidate/updateresume/" + id);

			redirectAttributes.addFlashAttribute("updatefailed", "failed");
			redirectAttributes.addFlashAttribute("message", response.getAlertMessage());

			return model;
		}
	}

	@GetMapping("/candidate/downloadresume/{id}")

	public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable Long id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			CandidatesInfo response = candidateService.getACandidateInfo(id, principal.getName(),jwtToken);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(response.getFiletype()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getDocName() + "\"")
					.body(new ByteArrayResource(response.getResume()));
		} catch (Exception e) {
			// TODO: handle exceptio
			throw new BgcUIException("exception occered while accessing the data");
		}

	}

	@PostMapping("/candidate/delete")
	public ModelAndView deleteEmployee(@RequestParam("emailId") String emailId, HttpServletRequest request)
			throws ParseException {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView("candidate");

		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		ResponseInfo response = candidateService.deleteCandidate(emailId, principal.getName(),jwtToken);

		if (response.getMessage().equals(Constants.Success)) {
			model.addObject("DeleteSuccess", "Success");
		} else {
			model.addObject("Addfailed", "failed");
			model.addObject("message", response.getAlertMessage());
		}

		model.addObject("customerinfo", response.getCustomerInfo());
		return model;

	}
	
	
	@GetMapping("/candidateInfo/{id}")
	public ModelAndView getCandidateInfo(@PathVariable String id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("candidateDetailInfo");
		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			CandidatesInfo candidatesInfo = candidateService.getCandidateDetailsByID(id,jwtToken);
			model.addObject("candidate", candidatesInfo);
			return model;
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data" + e.getMessage());
		}
	}
	
	
	@PostMapping("/verifySsnNumber")
	public ModelAndView verifySsnNumber(@RequestParam String ssn,@RequestParam Long candidateId, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		ModelAndView model = new ModelAndView("ssnPage");
		String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
		
		System.out.println(principal.getName()+"sss"+ candidateId+"nnnn"+ ssn);
		SsnInfo ssni = new SsnInfo(principal.getName(), candidateId, ssn);
		SSNInfo ssnInfo=ssnService.verifySsnNumber(ssni, jwtToken);
		model.addObject("candidateId", candidateId);
		model.addObject("ssnInfo", ssnInfo);
		return model;
		
	}

	
	@GetMapping("/downloadDocument/{id}")
	public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable String id, HttpServletRequest request) {

		try {
			String jwtToken=AddAndRetriveCookieService.readAdminServletCookie(request);
			Candidate_DocumentInfo response = documentService.getADoc(id, jwtToken);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(response.getDocumenttype()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getDocName() + "\"")
					.body(new ByteArrayResource(response.getDocument()));
		} catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data");
		}

	}
	
}
