package fr.formation.proxi4.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.proxi4.metier.entity.Answer;

/**
 * Interface utilisée par Spring pour gérer les échanges avec la table Answer de
 * la base de données.
 * 
 * @author Adminl
 *
 */
@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

	/**
	 * Methodé personnalisée permettant de récupérer toutes les réponses d'un
	 * sondage particulier grâce à l'id du sondage.
	 * 
	 * @param id L'id du sondage qui contient les réponses visées.
	 * @return List L'ensemble des réponses du sondage visé.
	 */
	public List<Answer> findBySurveyId(Integer id);
}
