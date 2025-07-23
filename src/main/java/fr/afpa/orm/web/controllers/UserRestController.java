package fr.afpa.orm.web.controllers;

import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private ClientRepository clientRepository;

    public UserRestController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping()
    public List<Client> getAll() {
        List<Client> all = (List<Client>) clientRepository.findAll();

        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getOne(@PathVariable long id) {
        // TODO compléter le code

        return ResponseEntity.ok(clientRepository.findById(id).orElseThrow());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        Client createClient = clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createClient).getBody();
    }

    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Client client) {
        // TODO Compléter le code
        Client updatedClient = clientRepository.findById(id).orElseThrow();
        updatedClient.setFirstName(client.getFirstName());
        updatedClient.setLastName(client.getLastName());
        updatedClient.setEmail(client.getEmail());
        updatedClient.setBirthdate(client.getBirthdate());
        clientRepository.save(updatedClient);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id, HttpServletResponse response) {
        // TODO implémentation

        Client clientDelete = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(clientDelete);
    }
}
