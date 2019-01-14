package fr.formation.proxi4.presentation;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.formation.proxi4.Proxi4Constants;
import fr.formation.proxi4.metier.entity.Survey;
import fr.formation.proxi4.metier.service.AnswerService;
import fr.formation.proxi4.metier.service.ClientService;
import fr.formation.proxi4.metier.service.SurveyService;

@Controller
@RequestMapping("/")
@Transactional(readOnly=true)
public class ViewController {

	private static final Logger LOGGER = Logger.getLogger(ViewController.class);
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private AnswerService answerService;
	
	
	@RequestMapping({ "", "index" })
	public ModelAndView index(@RequestParam(required=false) String message) {
		LOGGER.debug("Entrée sur la page index.");
		LOGGER.debug("Message récupéré suite à un redirection : " + message);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		LOGGER.debug("Ajout du sondage actuel.");
		mav.addObject("survey", this.surveyService.getCurrentSurvey());
		LOGGER.debug("Sondage ajouté.");
		return mav;
	}
		
	@RequestMapping("surveys")
	public ModelAndView loadSurveys() {
		LOGGER.debug("Entrée page des sondages.");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("surveys");
		LOGGER.debug("Chargement des sondages");
		mav.addObject("surveys", this.surveyService.readAll());
		return mav;
	}
	
	@RequestMapping("survey")
	public ModelAndView showSurvey(@RequestParam Integer id) {
		LOGGER.debug("Entrée sur la page Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("survey");
		LOGGER.debug("Lecture du sondage.");
		Survey survey = this.surveyService.read(id);
		LOGGER.debug("Hibernate.initialize.");
		Hibernate.initialize(survey);
		LOGGER.debug("Ajout du sondage au mav.");
		mav.addObject("survey", survey);
		return mav;
	}
	
	@RequestMapping("form")
	public ModelAndView createSurvey() {
		LOGGER.debug("Entrée sur le formulaire de création du Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		mav.addObject("survey", new Survey());
		return mav;
	}
	
	@RequestMapping(path="form", method=RequestMethod.POST)
	public String validateSurvey(Survey survey, RedirectAttributes attributes) {
		String message = null;
		LOGGER.debug("Création du sondage en DB.");
		this.surveyService.create(survey);
		message = "Sondage ajouté";
		attributes.addFlashAttribute("message", message);
		LOGGER.debug("Renvoi du message");
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}
	
	@RequestMapping("close")
	public String closeSurvey(Integer id, RedirectAttributes attributes) {
		String message = null;
		LOGGER.debug("Set du sondage pour lui ajouter une date de fin");
		Survey survey = this.surveyService.read(id);
		LOGGER.debug("Hibernate.initilaize.");
		Hibernate.initialize(survey);
		LOGGER.debug("Setting de la date.");
		survey.setEndDate(LocalDate.now());
		LOGGER.debug("Update du sondage.");
		this.surveyService.update(survey);
		message = "Sondgae terminé.";
		attributes.addFlashAttribute("message", message);
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}
}
