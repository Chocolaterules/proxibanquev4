package fr.formation.proxi4.presentation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("Entrée sur la page index.");
		mav.setViewName("index");
		LOGGER.debug("Ajout du sondage actuel.");
		mav.addObject("survey", this.surveyService.getCurrentSurvey());
		LOGGER.debug("Sondage ajouté.");
		return mav;
	}
		
	@RequestMapping("surveys")
	public ModelAndView loadSurveys() {
		ModelAndView mav = new ModelAndView();
		LOGGER.debug("Entrée page des sondages.");
		mav.setViewName("surveys");
		LOGGER.debug("Chargement des sondages");
		mav.addObject("surveys", this.surveyService.readAll());
		return mav;
	}
	
	public ModelAndView showSurvey() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	
	public ModelAndView createSurvey() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(path="form", method=RequestMethod.POST)
	public String validateSurvey(Survey survey, RedirectAttributes attributes) {
		String message = null;
		LOGGER.debug("Création du sondage en DB.");
		this.surveyService.create(survey);
		message = "Sondage ajouté";
		LOGGER.debug("Renvoie du message");
		return message;
		
	}
}
