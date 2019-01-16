package fr.formation.proxi4.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Client;
import fr.formation.proxi4.metier.service.ClientService;

/**
 * Classe WebService pour les clients de ProxiBanque.
 * 
 * @author Adminl
 *
 */
@RestController
@RequestMapping("/client")
@Transactional(readOnly = true)
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080" })
public class ClientWebService {

	@Autowired
	private ClientService service;

	/**
	 * Permet de persister un nouveau client en BDD.
	 * 
	 * @param client Le client à enregistrer.
	 * @return Client le client enregistré avec un id.
	 */
	@PostMapping
	public Client create(@RequestBody Client client) {
		return this.service.create(client);
	}

	/**
	 * Méthode renvoyant un objet Client à partir de son identifiant à 8 chiffres.
	 * 
	 * @param clientNum L'identifiant à 8 chiffres du client recherché.
	 * @return Client Le client recherché. Renvoie null s'il n'y a pas de client
	 *         identifié.
	 */
	@GetMapping("/{clientNum}")
	public Client confirm(@PathVariable String clientNum) {
		return this.service.confirmClient(clientNum);
	}
}
