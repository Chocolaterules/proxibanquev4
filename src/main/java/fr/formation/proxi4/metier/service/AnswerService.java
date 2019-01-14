package fr.formation.proxi4.metier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.proxi4.metier.entity.Answer;
import fr.formation.proxi4.persistence.AnswerDao;

@Service
public class AnswerService extends RestService<Answer> {

	@Autowired
	private AnswerDao dao;

	@Override
	protected JpaRepository<Answer, Integer> getDao() {
		return this.dao;
	}
}
