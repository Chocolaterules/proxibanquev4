package fr.formation.proxi4.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

	public List<Answer> findBySurveyId(Integer id);
}
