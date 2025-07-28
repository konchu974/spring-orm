package fr.afpa.orm.web.controllers;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.dto.ClientDtoMapper;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    private ClientRepository clientRepository;
    private ClientDtoMapper clientDtoMapper;

    public UserRestController(ClientRepository clientRepository, ClientDtoMapper clientDtoMapper) {
        this.clientRepository = clientRepository;
        this.clientDtoMapper = clientDtoMapper;
    }

    @GetMapping()
    public List<ClientDto> getAll() {
        List<ClientDto> result = new ArrayList<>();
        clientRepository.findAll().forEach(client -> result.add(clientDtoMapper.apply(client)));
        return result;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getOne(@PathVariable long id) {
        // TODO compléter le code

        Client client = clientRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(clientDtoMapper.apply(client));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody ClientDto clientDto) {
        Client client = clientDtoMapper.toEntity(clientDto);
        Client saved = clientRepository.save(client);
        return clientDtoMapper.apply(saved);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ClientDto clientDto) {
        // TODO Compléter le code
        Client updatedClient = clientRepository.findById(id).orElseThrow();
        updatedClient.setFirstName(clientDto.firstName());
        updatedClient.setLastName(clientDto.lastName());
        updatedClient.setEmail(clientDto.email());
        updatedClient.setBirthdate(clientDto.birthdate());
        clientRepository.save(updatedClient);

    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id, HttpServletResponse response) {
        // TODO implémentation

        Client clientDelete = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(clientDelete);
    }
}
