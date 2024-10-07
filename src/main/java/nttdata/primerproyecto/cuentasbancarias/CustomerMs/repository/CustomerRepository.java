package nttdata.primerproyecto.cuentasbancarias.CustomerMs.repository;

import nttdata.primerproyecto.cuentasbancarias.CustomerMs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByDni(String dni);
}