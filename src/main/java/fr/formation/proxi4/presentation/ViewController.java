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

/**
 * Classe utilisée par SpringDispatcher permettant de rediriger les requetes
 * entrantes vers les traitements adéquats.
 * 
 * @author Adminl
 *
 */
@Controller
@RequestMapping("/")
@Transactional(readOnly = true)
public class ViewController {

	private static final Logger LOGGER = Logger.getLogger(ViewController.class);

	@Autowired
	private SurveyService surveyService;

	/**
	 * Methode appelée lors de l'arrivée sur la page d'accueil de l'application.
	 * Redirige le client vers index.jsp après avoir chargé le sondage en cours s'il
	 * y en a un.
	 * 
	 * @param closeMessage Message de confirmation issu de closeSurvey() pour
	 *                     confirmer la fermeteur du sondage en cours.
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping({ "", "index" })
	public ModelAndView index(@RequestParam(required = false) String closeMessage) {
		LOGGER.info("Entrée sur la page index.");
		// Ajoute index.jsp au ModelAndView.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		// Récupère le sondage en cours et l'ajoute à l'objet ModelAndView.
		LOGGER.info("Ajout du sondage actuel.");
		Survey currentSurvey = this.surveyService.getCurrentSurvey();
		mav.addObject("survey", currentSurvey);
		LOGGER.info("Sondage en cours ajouté.");

		return mav;
	}

	/**
	 * Methode appelée lors de la redirection vers surveys.html. Récupère l'ensemble
	 * des sondages de la BDD et appelle analyseAnswers de SurveyService pour
	 * calculer le nombre de réponses positives et négatives de chauqe sondage.
	 * 
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping("surveys")
	public ModelAndView loadSurveys() {
		LOGGER.info("Entrée page des sondages.");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("surveys");

		LOGGER.info("Chargement des sondages");
		List<Survey> surveys = this.surveyService.readAll();
		// Methode qui va modifier les attributs PositiveCount et NegativeCount de
		// chaque sondage après avoir calculé le nombre de réponses positives et
		// négatives.
		this.surveyService.analyzeAnswers(surveys);
		mav.addObject("surveys", surveys);

		return mav;
	}

	/**
	 * Methode appelée lors de la redirection vers survey.html pour un sondage
	 * spécifique. Après avoir récupéré le sondage visé, la méthode ajoute deux
	 * listes au ModelAndView : la liste des réponses positives et celle des
	 * réponses négatives pour les transmettre à la page jsp.
	 * 
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping(path = "surveyDetail")
	public ModelAndView showSurvey(@RequestParam Integer id) {
		LOGGER.info("Entrée sur la page Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("surveyDetail");

		// récupération du sondage visé.
		LOGGER.info("Lecture du sondage.");
		Survey survey = this.surveyService.read(id);

		// Hibernate.initialize permet d'aviter l'erreur "no session" d'Hibernate.
		LOGGER.info("Hibernate.initialize.");
		Hibernate.initialize(survey);

		// Récupération des listes de bonnes et mauvaises réponses du sondage et ajout au
		// ModelAndView.
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

	/**
	 * Methode appelée lors de la redirection vers form.html qui permettra la
	 * création d'un nouveau sondage.
	 * 
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping("form")
	public ModelAndView createSurvey() {
		LOGGER.info("Entrée sur le formulaire de création du Sondage");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("form");
		mav.addObject("survey", new Survey());
		return mav;
	}

	/**
	 * Methode appelée lorsque la création d'un sondage a été demandée par
	 * l'utilisateur.
	 * 
	 * @param startDate   La date de début du sondage. L'annotation @DateTimeFormat
	 *                    permet de gérer le type="date" de l'input du formulaire de
	 *                    création de sondage.
	 * @param tempEndDate La date de fin prévisionnelle du sondage.
	 *                    L'annotation @DateTimeFormat permet de gérer le
	 *                    type="date" de l'input du formulaire de création de
	 *                    sondage.
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping(path = "form", method = RequestMethod.POST)
	public String validateSurvey(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tempEndDate) {
		LOGGER.info("Création du sondage en BDD.");
		LOGGER.info("Ajout du sondage.");
		this.surveyService.create(new Survey(startDate, tempEndDate));
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}

	/**
	 * Methode appelée lorsque l'utilsateur souhaite cloturer le sondage en cours.
	 * 
	 * @param id         L'id du sondage à fermer.
	 * @param attributes Attributs utilisés lors de la redirection vers index.html
	 *                   après création du sondage.
	 * @return ModelAndView Objet contenant le modèle et la vue du modèle MVC.
	 */
	@RequestMapping("close")
	public String closeSurvey(@RequestParam Integer id, RedirectAttributes attributes) {
		LOGGER.info("Récupération du sondage pour lui ajouter une date de fin");
		Survey survey = this.surveyService.read(id);

		// initialize permet d'aviter l'erreur "no Session" d'Hibernate.
		LOGGER.info("Hibernate.initialize.");
		Hibernate.initialize(survey);

		// on fixe la date de cloture à la date du jour avec LocalDate.now().
		LOGGER.info("Setting de la date.");
		survey.setEndDate(LocalDate.now());

		// Mise à jour du sondage en BDD.
		LOGGER.info("Update du sondage.");
		this.surveyService.update(survey);

		// Ajout d'un attribut à la requete qui sera récupéré par la méthode index()
		// lors de la redirection vers la page d'accueil.
		String closeMessage = "Sondage terminé.";
		LOGGER.info("Fin de close survey" + closeMessage);
		attributes.addFlashAttribute("closeMessage", closeMessage);
		return Proxi4Constants.REDIRECT_TO_INDEX;
	}
}
