package nttdata.primerproyecto.cuentasbancarias.AccountMs;

import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import nttdata.primerproyecto.cuentasbancarias.AccountMs.business.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountApiController {

    private final AccountService accountService;

    @Autowired
    public AccountApiController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Obtener todas las cuentas
    @GetMapping
    public ResponseEntity<List<Account>> listAllAccounts() {
        List<Account> accounts = accountService.listAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Crear una nueva cuenta
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Obtener una cuenta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        Account account = accountService.getAccountById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar una cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Integer id, @RequestBody Double amount) {
        Account updatedAccount = accountService.deposit(id, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // Retirar dinero de una cuenta
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Integer id, @RequestBody Double amount) {
        Account updatedAccount = accountService.withdraw(id, amount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }
}
