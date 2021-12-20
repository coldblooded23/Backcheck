package com.bgc.backgroundVerification.controller;

import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bgc.backgroundVerification.model.Candidate_DocumentInfo;
import com.bgc.backgroundVerification.model.CandidatesInfo;
import com.bgc.backgroundVerification.model.ResponseInfo;
import com.bgc.backgroundVerification.service.AddAndRetriveCookieService;
import com.bgc.backgroundVerification.service.Candidate_DocumentService;
import com.bgc.backgroundVerification.service.Constants;
import com.bgc.backgroundVerification.util.BgcUIException;

@Controller
public class Candidate_DocumentController {
	
	
	@Autowired
	Candidate_DocumentService documentService;
	
	
	@GetMapping("/user/documentInfo")
	public ModelAndView getDocumentPage( HttpServletRequest request) {
		Principal principal =request.getUserPrincipal();
		try {
			ModelAndView model =  new ModelAndView("candidate/documentPage");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			CandidatesInfo experienceInfo =documentService.getListOfDocuments(principal.getName(),jwtToken);
			System.out.println(experienceInfo.getCandidateId());
			model.addObject("document", experienceInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
		
		
	}
	
	
	@GetMapping("/user/documentInfo/{id}")
	public ModelAndView getADocument( @PathVariable String id, HttpServletRequest request) {
		Principal principal =request.getUserPrincipal();
		try {
			ModelAndView model =  new ModelAndView("candidate/editDocument");
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			Candidate_DocumentInfo experienceInfo =documentService.getADoc(id,jwtToken);
			System.out.println(experienceInfo.getCandidateId());
			model.addObject("document", experienceInfo);
			return model;
		}catch (Exception e) {
			// TODO: handle exception
			throw new BgcUIException("exceotion occered while accessing the data"+e.getMessage());
		}
			
	}
	
	@PostMapping("/user/addDocument")
	public ModelAndView addDocument(@RequestParam("candidateId") Long candidateId, @RequestParam("docFile") MultipartFile docFile,
			 @RequestParam("typeofDoc") String typeofDoc,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {
	try {
		ModelAndView model =  new ModelAndView("redirect:/user/documentInfo");
		
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =documentService.addADocument(docFile, candidateId, typeofDoc,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
redirectAttributes.addFlashAttribute("AddSuccess", "Success");

				return model;
				
			}else {
				redirectAttributes.addFlashAttribute("message", ProfileInfo.getAlertMessage());
				redirectAttributes.addFlashAttribute("Addfailed", "failed");		
				return model;
			}
			
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
			
	}
	
	
	@PostMapping("/user/updateDocument")
	public ModelAndView updateDocument(@RequestParam("candidateId") Long candidateId,@RequestParam("documentId") Long documentId, @RequestParam("docFile") MultipartFile docFile,
			 @RequestParam("typeofDoc") String typeofDoc,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {
	try {
		
		
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			ResponseInfo ProfileInfo =documentService.updateDocument(docFile, candidateId, documentId, typeofDoc,jwtToken);
			if(ProfileInfo.getAlertMessage().equals(Constants.AuthenticationSuccess)) {
				ModelAndView model =  new ModelAndView("redirect:/user/documentInfo");
redirectAttributes.addFlashAttribute("updateSuccess", "Success");

				return model;
				
			}else {
				ModelAndView model =  new ModelAndView("redirect:/user/documentInfo/"+documentId);
				redirectAttributes.addFlashAttribute("message", ProfileInfo.getAlertMessage());
				redirectAttributes.addFlashAttribute("updatefailed", "failed");;		
				return model;
			}
			
			
		}catch (Exception e) {
			throw new BgcUIException("exception occered while accessing the data"+e.getMessage());
		}
			
	}
	
	
	@GetMapping("/user/downloadDocument/{id}")
	public ResponseEntity<ByteArrayResource> downloadResume(@PathVariable String id, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		try {
			String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
			Candidate_DocumentInfo response = documentService.getADoc(id,jwtToken);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(response.getDocumenttype()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getDocName() + "\"")
					.body(new ByteArrayResource(response.getDocument()));
		} catch (Exception e) {
			// TODO: handle exceptio
			throw new BgcUIException("exception occered while accessing the data");
		}

	}

	@PostMapping("/user/deleteDocument")
	public ModelAndView deleteDocument( ResponseInfo responseInfo,RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
		
		String jwtToken=AddAndRetriveCookieService.readCandiddateServletCookie(request);
		ResponseInfo response=documentService.deleteDocument(responseInfo.getEmailId(),jwtToken);
		ModelAndView model =  new ModelAndView("redirect:/user/documentInfo");
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
