package fr.formation.proxi4.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.service.SurveyService;

@RestController
public class SurveyWebService {

	@Autowired
	private SurveyService service;
	
	
}
