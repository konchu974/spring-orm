package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Account;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {

    @Override
    public AccountDto apply(Account account) {
        return new AccountDto(account.getId(), account.getBalance(), account.getClient());
    }
}
