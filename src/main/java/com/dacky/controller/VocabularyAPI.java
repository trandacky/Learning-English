package com.dacky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dacky.entity.Vocabulary;
import com.dacky.service.VocabularyService;

@RestController
@RequestMapping("/api")
public class VocabularyAPI {
	private final int count=15;
	
	@Autowired 
	private VocabularyService vocabularyService;
	
	@GetMapping
	public List<Vocabulary> get()
	{
		return vocabularyService.getVocabulary(count);
	}
	@GetMapping(value="/{id}")
	public Vocabulary changeEnable(@PathVariable("id") Long id)
	{
		return vocabularyService.changeEnable(id);
	}
}
