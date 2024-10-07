package nttdata.primerproyecto.cuentasbancarias.CustomerMs.business;

import nttdata.primerproyecto.cuentasbancarias.CustomerMs.entity.Customer;
import nttdata.primerproyecto.cuentasbancarias.CustomerMs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerRepository.existsByDni(customer.getDni())) {
            throw new IllegalArgumentException("El DNI ya está en uso.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customer) {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
