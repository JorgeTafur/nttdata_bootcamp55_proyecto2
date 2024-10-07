package nttdata.primerproyecto.cuentasbancarias.AccountMs.business;

import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.TipoCuenta;
import nttdata.primerproyecto.cuentasbancarias.AccountMs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> listAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        if (account.getSaldo() <= 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser mayor a 0.");
        }
        // Reconozco que esto no es optimo
        // al agregar mas tipos de cuenta habria que editarlo y no cumpliria con las buenas practicas
        //para el siguiente proyecto tomaré medidas mas flexibles para cumplir con los requisitos
        if (account.getTipoCuenta() == null ||
                (!account.getTipoCuenta().equals(TipoCuenta.AHORROS) && !account.getTipoCuenta().equals(TipoCuenta.CORRIENTE))) {
            throw new IllegalArgumentException("El tipo de cuenta debe ser AHORROS o CORRIENTE.");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account deposit(Integer id, Double amount) {
        Account account = getAccountById(id);
        if (account != null) {
            account.setSaldo(account.getSaldo() + amount);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public Account withdraw(Integer id, Double amount) {
        Account account = getAccountById(id);
        if (account == null)
            throw new IllegalArgumentException("No existe la cuenta");
        if (account.getSaldo() < amount)
            throw new IllegalArgumentException("No hay saldo suficiente");
        account.setSaldo(account.getSaldo() - amount);
        return accountRepository.save(account);
    }
}
