package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Client;

import java.math.BigDecimal;

public record AccountDto(
        Long id,
        BigDecimal balance,
        Client client
) {}
