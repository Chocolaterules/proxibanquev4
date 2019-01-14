package fr.formation.proxi4.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formation.proxi4.metier.entity.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {

	public Client findByClientNum(String clientNum);
}
