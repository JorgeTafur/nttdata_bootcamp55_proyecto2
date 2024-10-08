package nttdata.primerproyecto.cuentasbancarias.AccountMs.business;

import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> listAllAccounts();
    Account createAccount(Account account);
    Account getAccountById(Integer id);
    Optional<Account> getAccountByAccountNumber(String accountNumber);
    void deleteAccount(Integer id);
    Account deposit(Integer id, Double amount);
    Account withdraw(Integer id, Double amount);
}