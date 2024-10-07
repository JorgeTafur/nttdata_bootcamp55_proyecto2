package nttdata.primerproyecto.cuentasbancarias.CustomerMs.business;

import nttdata.primerproyecto.cuentasbancarias.CustomerMs.entity.Customer;
import java.util.List;

public interface CustomerService {

    List<Customer> listAllCustomers();

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Integer id);

    Customer updateCustomer(Integer id, Customer customer);

    void deleteCustomer(Integer id);
}