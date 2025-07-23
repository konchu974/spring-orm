package fr.afpa.orm.web.controllers;

import java.util.List;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.AccountDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes GET et qui renvoie une liste de comptes
     *
     * Attention, il manque peut être une annotation :)
     */
    @GetMapping()
    public List<Account> getAll() {
        // TODO récupération des compte provenant d'un repository
        List<Account> all = (List<Account>) accountRepository.findAll();

        // TODO renvoyer les objets de la classe "Account"
        return all;
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes GET avec un identifiant "variable de chemin" et qui retourne les informations du compte associé
     * Plus d'informations sur les variables de chemin -> https://www.baeldung.com/spring-pathvariable
     */

    @GetMapping("/{id}")
    public ResponseEntity<Account> getOne(@PathVariable long id) {
        // TODO compléter le code

        return ResponseEntity.ok(accountRepository.findById(id).orElseThrow());
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes POST
     * Cette méthode doit recevoir les informations d'un compte en tant que "request body", elle doit sauvegarder le compte en mémoire et retourner ses informations (en json)
     * Tutoriel intéressant -> https://stackabuse.com/get-http-post-body-in-spring/
     * Le serveur devrai retourner un code http de succès (201 Created)
     **/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        // TODO compléter le code
        Account createAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount).getBody();
    }

    /**
     * TODO implémenter une méthode qui traite les requêtes PUT
     * 
     * Attention de bien ajouter les annotations qui conviennent
     */
    @PostMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Account account) {
        // TODO Compléter le code
        Account updatedAccount = accountRepository.findById(id).orElseThrow();
        updatedAccount.setClient(account.getClient());
        updatedAccount.setBalance(account.getBalance());
        accountRepository.save(updatedAccount);

    }

    /**
     * TODO implémenter une méthode qui traite les requêtes  DELETE 
     * L'identifiant du compte devra être passé en "variable de chemin" (ou "path variable")
     * Dans le cas d'un suppression effectuée avec succès, le serveur doit retourner un status http 204 (No content)
     * 
     * Il est possible de modifier la réponse du serveur en utilisant la méthode "setStatus" de la classe HttpServletResponse pour configurer le message de réponse du serveur
     */

    @DeleteMapping("/{id}")
    public void remove(@PathVariable long id, HttpServletResponse response) {
        // TODO implémentation

        Account accountDelete = accountRepository.findById(id).orElseThrow();
        accountRepository.delete(accountDelete);
    }
}
