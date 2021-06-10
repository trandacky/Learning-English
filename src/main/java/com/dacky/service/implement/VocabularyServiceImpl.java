package com.dacky.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dacky.entity.Vocabulary;
import com.dacky.repository.VocabularyRepository;
import com.dacky.service.VocabularyService;

@Service
public class VocabularyServiceImpl implements VocabularyService{

	@Autowired
	private VocabularyRepository vocabularyRepository;
	
	@Override
	public List<Vocabulary> findAll() {
		return vocabularyRepository.findAll();
	}

	@Override
	public Vocabulary save(String eng, String vi) {
		Optional<Vocabulary> optional=vocabularyRepository.findByEng(eng);
		if(optional.isPresent())
		{
			return new Vocabulary();
		}
		Vocabulary vocabulary = new Vocabulary();
		vocabulary.setEng(eng);
		vocabulary.setVi(vi);
		return vocabularyRepository.save(vocabulary);
	}

	@Override
	public List<Vocabulary> seach(String seach) {
		return vocabularyRepository.findByEngLike(seach);
	}

	@Override
	public Vocabulary changeEnable(Long id) {
		Vocabulary vocabulary=vocabularyRepository.findById(id).get();
		vocabulary.setEnable(!vocabulary.isEnable());
		return vocabularyRepository.save(vocabulary);
	}

	@Override
	public Vocabulary findById(Long id) {
		
		return vocabularyRepository.findById(id).get();
	}

	@Override
	public Vocabulary updateVocabulary(Long id, String eng, String vi) {
		
		Vocabulary vocabulary=vocabularyRepository.findById(id).get();
		vocabulary.setEng(eng);
		vocabulary.setVi(vi);
		return vocabularyRepository.save(vocabulary);
	}

	@Override
	public List<Vocabulary> getVocabulary(int count) {
		Pageable paging = PageRequest.of(0, count, Sort.by("id").ascending());
		Page<Vocabulary> pageable = vocabularyRepository.getVocabulary(paging);
		return pageable.getContent();
	}
	
}
