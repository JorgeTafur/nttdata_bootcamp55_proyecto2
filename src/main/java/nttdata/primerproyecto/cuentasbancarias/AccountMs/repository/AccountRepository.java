package nttdata.primerproyecto.cuentasbancarias.AccountMs.repository;

import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}