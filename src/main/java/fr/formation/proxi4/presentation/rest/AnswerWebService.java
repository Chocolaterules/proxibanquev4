package fr.formation.proxi4.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Answer;
import fr.formation.proxi4.metier.service.AnswerService;

@RestController
@RequestMapping("/answer")
@Transactional(readOnly=true)
@CrossOrigin(origins={"http://localhost:4200", "http://localhost:8080"})
public class AnswerWebService {

	@Autowired
	private AnswerService service;
	
	@PostMapping
	public Answer create(@RequestBody Answer answer) {
		return this.service.create(answer);
	}
}
