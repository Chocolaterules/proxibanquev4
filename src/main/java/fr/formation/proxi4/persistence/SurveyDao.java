package fr.formation.proxi4.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.proxi4.metier.entity.Survey;

/**
 * Interface utilisée par Spring pour gérer les échanges avec la table survey de la
 * base de données.
 * 
 * @author Adminl
 *
 */
@Repository
public interface SurveyDao extends JpaRepository<Survey, Integer> {

}
