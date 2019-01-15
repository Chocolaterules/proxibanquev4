package fr.formation.proxi4.presentation.rest;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Answer;
import fr.formation.proxi4.metier.service.AnswerService;

/**
 * Classe WebService pour les réponses aux sondages.
 * 
 * @author Adminl
 *
 */
@RestController
@RequestMapping("/answer")
@Transactional(readOnly = true)
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
public class AnswerWebService {

	@Autowired
	private AnswerService service;

	/**
	 * Methode permettant de persister une réponse en base de données.
	 * 
	 * @param answer La réponse à ajouter en BDD.
	 * @return Answer La réponse ajoutée en BDD avec un id.
	 */
	@PostMapping
	public Answer create(@RequestBody Answer answer) {
		answer.setEntryDate(LocalDate.now());
		return this.service.create(answer);
	}
}
