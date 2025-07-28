package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {

    private InsuranceDtoMapper insuranceDtoMapper;

    @Override
    public ClientDto apply(Client client) {
        List<AccountDto> accountDtos = client.getAccounts() != null
                ? client.getAccounts().stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getBalance(),
                        // mini ClientDto pour éviter boucle infinie
                        new ClientDto(
                                client.getId(),
                                client.getFirstName(),
                                client.getLastName(),
                                client.getEmail(),
                                client.getBirthdate(),
                                null,
                                null // les comptes ici pour éviter boucle
                        )
                )).toList()
                : List.of();
        List<InsuranceDto> insuranceDtos = client.getInsurances() != null
                ? client.getInsurances().stream()
                .map(insuranceDtoMapper)
                .toList()
                : List.of();


        return new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getBirthdate(),
                accountDtos,
                insuranceDtos
        );
    }
    public Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setId(dto.id());
        client.setFirstName(dto.firstName());
        client.setLastName(dto.lastName());
        client.setEmail(dto.email());
        client.setBirthdate(dto.birthdate()); // si tu utilises ce champ
        return client;
    }

}
