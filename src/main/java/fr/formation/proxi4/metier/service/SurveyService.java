package fr.formation.proxi4.metier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
		Survey survey = new Survey();
		
		return survey;
	}

}
