package fr.formation.proxi4.presentation.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Survey;
import fr.formation.proxi4.metier.service.SurveyService;

@RestController
@RequestMapping("/survey")
@Transactional(readOnly=true)
@CrossOrigin(origins={"http://localhost:4200", "http://localhost:8080"})
public class SurveyWebService {

	@Autowired
	private SurveyService service;
	
	@GetMapping
	public Survey getCurrentSurvey() {
		Survey survey = this.service.getCurrentSurvey();
		Hibernate.initialize(survey);
		return survey;
	}
	
	@GetMapping("/currentDate")
	public String getCurrentDate() {
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		String formattedString = localDate.format(formatter);
		return formattedString;
	}
}
