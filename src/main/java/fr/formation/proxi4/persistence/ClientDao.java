package fr.formation.proxi4.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.proxi4.metier.entity.Client;

/**
 * Interface utilisée par Spring pour gérer les échanges avec la table client de
 * la base de données.
 * 
 * @author Adminl
 *
 */
@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

	/**
	 * Méthode permettant de récupérer un client à partir de son identifiant à 8
	 * chiffres.
	 * 
	 * @param clientNum L'identifiant à 8 chiffres du client visé.
	 * @return Client Le client ayant cet identifiant. Null s'il n'existe aucun
	 *         client avec cet identifiant.
	 */
	public Client findByClientNum(String clientNum);
}
