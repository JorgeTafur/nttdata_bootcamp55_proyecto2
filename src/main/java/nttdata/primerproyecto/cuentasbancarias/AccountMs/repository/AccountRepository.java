package nttdata.primerproyecto.cuentasbancarias.AccountMs.repository;

import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByNumeroCuenta(String accountNumber);
}