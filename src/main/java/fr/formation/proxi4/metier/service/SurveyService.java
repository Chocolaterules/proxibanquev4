package fr.formation.proxi4.metier.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.proxi4.metier.entity.Answer;
import fr.formation.proxi4.metier.entity.Survey;
import fr.formation.proxi4.persistence.SurveyDao;

/**
 * Classe regroupant les services utilisés pour les Survey de l'application.
 * 
 * @author Adminl
 *
 */
@Service
public class SurveyService extends RestService<Survey> {

	@Autowired
	private SurveyDao dao;

	@Override
	protected JpaRepository<Survey, Integer> getDao() {
		return this.dao;
	}

	/**
	 * Methode permettant de récupérer le sondage en cours le jour du lancement de
	 * l'application
	 * 
	 * @return Survey le sondage en cours. Renvoie null s'il n'y a aucun sondage en
	 *         cours.
	 */
	public Survey getCurrentSurvey() {
		List<Survey> surveys = this.readAll();
		Survey curSurvey = null;
		for (Survey survey : surveys) {
			// s'il existe un sondage en cours dans la BDD, on le retourne.
			if (survey.getEndDate() == null && survey.getStartDate() != null) {
				curSurvey = survey;
				break;
			}
		}
		return curSurvey;
	}

	/**
	 * Methode permettant de compter le nombre de réponses positives pour un sondage
	 * donné.
	 * 
	 * @param survey Le sondage à analyser
	 */
	public void countPos(Survey survey) {
		Integer pos = 0;
		for (Answer answer : survey.getAnswers()) {
			if (answer.getIsPositive() == true) {
				pos += 1;
			}
		}
		// utilisation du paramètre annoté @Transient PositiveCount de la classe Survey.
		survey.setPositiveCount(pos);
	}

	/**
	 * Methode permettant de compter le nombre de réponses negatives pour un sondage
	 * donné.
	 * 
	 * @param survey Le sondage à analyser
	 */
	public void countNeg(Survey survey) {
		Integer neg = 0;
		for (Answer answer : survey.getAnswers()) {
			if (answer.getIsPositive() == false) {
				neg += 1;
			}
		}
		// utilisation du paramètre annoté @Transient NegativeCount de la classe Survey.
		survey.setNegativeCount(neg);
	}

	/**
	 * Permet de transformer un String en objet LocalDate. la date doit être au
	 * format "dd-MM-yyyy" pour être formaté en LocalDate par cette méthode.
	 * 
	 * @param date La date à transformer sous forme de chaine de caractères.
	 * @return LocalDate la date en format LocalDate.
	 */
	public LocalDate dateFormat(String date) {
		System.out.println("entree dateFormat : " + date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		// craétion du LocalDate à partir du formatter
		LocalDate newDate = LocalDate.parse(date, formatter);
		System.out.println(newDate);
		return newDate;
	}

	/**
	 * Methode permettant de fixer le nombre de réponses positives et négatives d'un
	 * ensemble de sondages. Utilise les méthodes countPos et countNeg de
	 * SurveyService sur chaque sondage de la liste.
	 * 
	 * @param surveys La liste des sondages à analyser.
	 */
	public void analyzeAnswers(List<Survey> surveys) {
		for (Survey survey : surveys) {
			this.countPos(survey);
			this.countNeg(survey);
		}

	}

}
