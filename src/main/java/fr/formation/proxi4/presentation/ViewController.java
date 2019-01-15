package fr.formation.proxi4.presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.proxi4.Proxi4Constants;
import fr.formation.proxi4.metier.entity.Answer;
import fr.formation.proxi4.metier.entity.Survey;
import fr.formation.proxi4.metier.service.SurveyService;

@Controller
@RequestMapping("/")
@Transactional(readOnly = true)
public class ViewController {

	private static final Logger LOGGER = Logger.getLogger(ViewController.class);

	@Autowired
	private SurveyService surveyService;

	@RequestMapping({ "", "index" })
	public ModelAndView index(@RequestParam(required = false) String message,
			@RequestParam(required = false) String closeMessage) {

		LOGGER.info("Entrée sur la page index.");
		LOGGER.info("Message récupéré suite à un redirection : " + message);
		System.out.println(closeMessage);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		LOGGER.info("Ajout du sondage actuel.");
		mav.addObject("message", message);
		mav.addObject("closeMessage", closeMessage);
		Survey currentSurvey = this.surveyService.getCurrentSurvey();
		System.out.println(currentSurvey);
		mav.addObject("survey", currentSurvey);
		LOGGER.info("Sondage en cours ajouté.");
		return mav;
	}

	@RequestMapping("surveys")
	public ModelAndView loadSurveys() {
		LOGGER.info("Entrée page des sondages.");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("surveys");
		LOGGER.info("Chargement des sondages");
		List<Survey> surveys = this.surveyService.readAll();
		this.surveyService.analyzeAnswers(surveys);
		mav.addObject("surveys", surveys);

		return mav;
	}

	@RequestMapping(path = "surveyDetail")
	public ModelAndView showSurvey(@RequestParam Integer id) {
		System.out.println("id du sondage a afficher " + id);
		LOGGER.info("Entrée sur la page Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("surveyDetail");

		LOGGER.info("Lecture du sondage.");
		Survey survey = this.surveyService.read(id);

		LOGGER.info("Hibernate.initialize.");
		Hibernate.initialize(survey);

		LOGGER.info("Récupération des réponses du sondage");
		List<Answer> answers = survey.getAnswers();
		List<Answer> goodAnswers = new ArrayList<>();
		List<Answer> badAnswers = new ArrayList<>();
		for (Answer answer : answers) {
			if (answer.getIsPositive()) {
				goodAnswers.add(answer);
			} else {
				badAnswers.add(answer);
			}
		}

		LOGGER.info("Ajout des réponses au mav.");
		mav.addObject("survey", survey);
		mav.addObject("goodAnswers", goodAnswers);
		mav.addObject("badAnswers", badAnswers);
		return mav;
	}

	@RequestMapping("form")
	public ModelAndView createSurvey() {
		LOGGER.info("Entrée sur le formulaire de création du Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		mav.addObject("survey", new Survey());
		return mav;
	}

	@RequestMapping(path = "form", method = RequestMethod.POST)
	public String validateSurvey(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tempEndDate, RedirectAttributes attributes) {
		LOGGER.info("Création du sondage en DB.");
		String message = null;
		LOGGER.info("Ajout du sondage.");
		this.surveyService.create(new Survey(startDate, tempEndDate));
		message = "Sondage ajouté";
		attributes.addFlashAttribute("message", message);
		LOGGER.info("Renvoi du message");
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}

	@RequestMapping("close")
	public String closeSurvey(@RequestParam Integer id, RedirectAttributes attributes) {
		LOGGER.info("Set du sondage pour lui ajouter une date de fin");
		Survey survey = this.surveyService.read(id);
		LOGGER.info("Hibernate.initialize.");
		Hibernate.initialize(survey);
		LOGGER.info("Setting de la date.");
		survey.setEndDate(LocalDate.now());
		LOGGER.info("Update du sondage.");
		this.surveyService.update(survey);
		String closeMessage = "Sondage terminé.";
		LOGGER.info("Fin de close survey" + closeMessage);
		attributes.addFlashAttribute("closeMessage", closeMessage);
		System.out.println(attributes.getFlashAttributes());
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}
}
