package fr.formation.proxi4.metier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends RestService<Client> {

	@Autowired
	private ClientDao dao;
	
	@Override
	protected JpaRepository<Client, Integer> getDao() {
		return this.dao;
	}

}
