package com.dacky.controller;

import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dacky.entity.Vocabulary;
import com.dacky.service.VocabularyService;

@Controller
@RequestMapping("/")
public class WebController {
	@Autowired 
	private VocabularyService vocabularyService;
	
	@GetMapping
	public String index(Model model) {
		List<Vocabulary> vocabularies=vocabularyService.findAll();
		vocabularies.sort(Comparator.comparing(Vocabulary::getEng));
		
		model.addAttribute("vocabularies", vocabularies);
		return "index";
	}
	@PostMapping
	public String addNew(Model model,HttpServletRequest request) {
		String eng = request.getParameter("eng");
		String vi = request.getParameter("vi");
		vocabularyService.save(eng,vi);
		List<Vocabulary> vocabularies=vocabularyService.findAll();
		vocabularies.sort(Comparator.comparing(Vocabulary::getEng));
		
		model.addAttribute("vocabularies", vocabularies);
		return "index";
	}
	@GetMapping(value="/seach")
	public String seach(@RequestParam("seach") String seach,Model model) {
		List<Vocabulary> vocabularies=vocabularyService.seach(seach);
		vocabularies.sort(Comparator.comparing(Vocabulary::getEng));
		
		model.addAttribute("vocabularies", vocabularies);
		return "index";
	}
	
	@GetMapping(value="/change/{id}")
	public String changeEnable(@PathVariable("id") Long id,HttpServletRequest request) {
		vocabularyService.changeEnable(id);
		String back = request.getHeader("Referer");
		return "redirect:" + back;
	}
	@GetMapping(value="/edit/{id}")
	public String toEdit(@PathVariable("id") Long id,HttpServletRequest request, Model model) {
		Vocabulary vocabulary=vocabularyService.findById(id);
		model.addAttribute("vocabulary", vocabulary);
		return "edit";
	}
	@PostMapping(value="/edit")
	public String edit(HttpServletRequest request, Model model) {
		
		Long id = Long.parseLong(request.getParameter("id"));
		
		String eng = request.getParameter("eng");
		String vi = request.getParameter("vi");
		vocabularyService.updateVocabulary(id,eng,vi);
		return "redirect:" + "/";
	}
}
