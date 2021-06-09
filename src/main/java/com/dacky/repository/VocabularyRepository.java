package com.dacky.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dacky.entity.Vocabulary;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long>{

	
	@Query(value = "SELECT v FROM Vocabulary v where v.eng LIKE %?1%")
	List<Vocabulary> findByEngLike(String seach);

	Optional<Vocabulary> findByEng(String eng);
	
	@Query(value = "SELECT v FROM Vocabulary v where v.enable = TRUE")
	Page<Vocabulary> getVocabulary(Pageable paging);

}
