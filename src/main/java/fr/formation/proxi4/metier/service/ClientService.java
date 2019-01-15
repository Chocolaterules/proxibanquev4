package fr.formation.proxi4.metier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import fr.formation.proxi4.metier.entity.Client;
import fr.formation.proxi4.persistence.ClientDao;

/**
 * Classe de service pour l'entité Client de l'application. Permet les opéarions
 * du CRUD + récupérer toutes les clients d'un coup.
 * @author Adminl
 *
 */
@Service
public class ClientService extends RestService<Client> {

	@Autowired
	private ClientDao dao;
	
	@Override
	protected JpaRepository<Client, Integer> getDao() {
		return this.dao;
	}
	
	/** Permet de récupérer un client à partir de son identifiant de 8 chiffres.
	 * @param clientNum
	 * @return
	 */
	public Client confirmClient(String clientNum) {
		return this.dao.findByClientNum(clientNum);
	}

}
