package fr.formation.proxi4.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.proxi4.metier.entity.Answer;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

	public List<Answer> findBySurveyId(Integer id);
}
