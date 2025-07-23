package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class ClientDtoMapper implements Function<Client, ClientDto> {


    @Override
    public ClientDto apply(Client client) {
        return new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getAccounts()
        );
    }
}
