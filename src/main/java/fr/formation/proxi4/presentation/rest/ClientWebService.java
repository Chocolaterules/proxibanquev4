package fr.formation.proxi4.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.proxi4.metier.entity.Client;
import fr.formation.proxi4.metier.service.ClientService;

@RestController
@RequestMapping("/client")
@Transactional(readOnly=true)
@CrossOrigin(origins= {"http://localhost:4200", "http://localhost:8080"})
public class ClientWebService {

	@Autowired
	private ClientService service;
	
	@PostMapping
	public Client create(@RequestBody Client client) {
		return this.service.create(client);
	}
	
	@GetMapping("/{clientNum}")
	public Client confirm(@PathVariable String clientNum) {
		return this.service.confirmClient(clientNum);
	}
}
