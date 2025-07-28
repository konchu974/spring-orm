package fr.afpa.orm.web.controllers;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.AccountDtoMapper;
import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.afpa.orm.entities.Account;
import fr.afpa.orm.repositories.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;

/**
 * TODO ajouter la/les annotations nécessaires pour faire de "AccountRestController" un contrôleur de REST API
 */

@RestController
@RequestMapping("/accounts")
public class AccountRestController {


    /**
     * TODO implémenter un constructeur
     *  
     * TODO injecter {@link AccountRepository} en dépendance par injection via le constructeur
     * Plus d'informations -> https://keyboardplaying.fr/blogue/2021/01/spring-injection-constructeur/
     */

    @Autowired
    private AccountRepository accountRepository;

    private AccountDtoMapper accountDtoMapper ;

    public AccountRestController(AccountRepository accountRepository, AccountDtoMapper accountDtoMapper) {
        this.accountRepository = accountRepository;
        this.accountDtoMapper = accountDtoMapper;
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes GET et qui renvoie une liste de comptes
     *
     * Attention, il manque peut être une annotation :)
     */
    @GetMapping()
    public List<AccountDto> getAll() {
        List<AccountDto> result = new ArrayList<>();
        accountRepository.findAll().forEach(account -> result.add(accountDtoMapper.apply(account)));
        return result;
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes GET avec un identifiant "variable de chemin" et qui retourne les informations du compte associé
     * Plus d'informations sur les variables de chemin -> https://www.baeldung.com/spring-pathvariable
     */

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getOne(@PathVariable long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(accountDtoMapper.apply(account));
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes POST
     * Cette méthode doit recevoir les informations d'un compte en tant que "request body", elle doit sauvegarder le compte en mémoire et retourner ses informations (en json)
     * Tutoriel intéressant -> https://stackabuse.com/get-http-post-body-in-spring/
     * Le serveur devrai retourner un code http de succès (201 Created)
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody AccountDto accountDto) {
        // TODO compléter le code
        Account account = accountDtoMapper.toEntity(accountDto);
        Account createAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount).getBody();
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     * 
     * Attention de bien ajouter les annotations qui conviennent
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(@PathVariable long id, @RequestBody AccountDto accountDto) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account not found with id " + id));

        Account updatedAccount = accountDtoMapper.toEntity(accountDto);

        updatedAccount.setId(existingAccount.getId());
        updatedAccount.setId(existingAccount.getId());

        Account saved = accountRepository.save(existingAccount);

        return ResponseEntity.ok(accountDtoMapper.apply(saved));
    }


    /**
     * TODO implémenter une méthode qui traite les requêtes  DELETE 
     * L'identifiant du compte devra être passé en "variable de chemin" (ou "path variable")
     * Dans le cas d'un suppression effectuée avec succès, le serveur doit retourner un status http 204 (No content)
     * 
     * Il est possible de modifier la réponse du serveur en utilisant la méthode "setStatus" de la classe HttpServletResponse pour configurer le message de réponse du serveur
     */

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id) {
        Account accountToDelete = accountRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Account not found with id " + id));

        accountRepository.delete(accountToDelete);
    }
}
