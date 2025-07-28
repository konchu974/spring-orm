package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {

    @Override
    public AccountDto apply(Account account) {
        Client client = account.getClient();

        ClientDto clientDto = new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getBirthdate(),
                null,
                null
        );

        return new AccountDto(
                account.getId(),
                account.getBalance(),
                clientDto
        );
    }

    public Account toEntity(AccountDto dto) {
        Account account = new Account();
        account.setId(dto.id());
        account.setBalance(dto.balance());

        ClientDto clientDto = dto.client();
        Client client = new Client();
        client.setId(clientDto.id());
        client.setFirstName(clientDto.firstName());
        client.setLastName(clientDto.lastName());
        client.setEmail(clientDto.email());
        account.setClient(client);

        return account;
    }
}
