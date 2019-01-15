package fr.formation.proxi4.presentation.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Survey;
import fr.formation.proxi4.metier.service.SurveyService;

/**
 * Classe WebService pour les sondages de l'application.
 * 
 * @author Adminl
 *
 */
@RestController
@RequestMapping("/survey")
@Transactional(readOnly = true)
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
public class SurveyWebService {

	@Autowired
	private SurveyService service;

	/**
	 * Methode envoyant le sondage en cours (sondage avec une date de début
	 * antérieure à la date de jour et pas de date de fermeture.)
	 * 
	 * @return Survey Le sondage en cours.
	 */
	@GetMapping
	public Survey getCurrentSurvey() {
		Survey survey = this.service.getCurrentSurvey();
		Hibernate.initialize(survey);
		return survey;
	}

//	/** 
//	 * methode permettant d'envoyer la date du jour sous forme de chaine de caractères au format "dd MM yyyy".
//	 * @return String La date du jour en String (ex : 15 01 2019)
//	 */
//	@GetMapping("/currentDate")
//	public String getCurrentDate() {
//		LocalDate localDate = LocalDate.now();// For reference
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
//		String formattedString = localDate.format(formatter);
//		return formattedString;
//	}

	/** Methode renvoyant le nombre de jours entre la date du jour et la fin prévisionnelle du sondage en cours.
	 * @return Integer le nombre de jours entre les deux dates.
	 */
	@GetMapping("/date")
	public Integer getDelay() {
		Integer days = 15;
		//Récupération de la date du jour.
		Survey survey = this.service.getCurrentSurvey();
		//between() renvoie un long, d'où le cast en int pour renvoyer le nombre de jours en Integer.
		days = (int) ChronoUnit.DAYS.between(LocalDate.now(), survey.getTempEndDate());
		return days;
	}
}
