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

@Service
public class SurveyService extends RestService<Survey> {

	@Autowired
	private SurveyDao dao;
	
	@Override
	protected JpaRepository<Survey, Integer> getDao() {
		return this.dao;
	}
	
	public Survey getCurrentSurvey() {
		List<Survey> surveys = this.readAll();
		Survey curSurvey = new Survey();
		for (Survey survey: surveys) {
			if (survey.getEndDate() == null) {
				curSurvey = survey;
			}
		}
		return curSurvey;
	}
	
	public Integer countPos(List<Survey> surveys) {
		Integer pos = 0;
		for (Survey survey : surveys) {
			for (Answer answer : survey.getAnswers()) {
				if (answer.getIsPositive() == true) {
					pos += 1;
				}
			}
		}
		return pos;
	}
	
	public Integer countNeg(List<Survey> surveys) {
		Integer neg = 0;
		for (Survey survey : surveys) {
			for (Answer answer : survey.getAnswers()) {
				if (answer.getIsPositive() == false) {
					neg += 1;
				}
			}
		}
		return neg;
	}
	
	public LocalDate dateFormat(String date) {
		System.out.println("entree dateFormat : " + date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formatDate = date.format(formatter);
//		System.out.println(formatDate);
		LocalDate newDate = LocalDate.parse(date, formatter);
		System.out.println(newDate);
		return newDate;
	}

}
