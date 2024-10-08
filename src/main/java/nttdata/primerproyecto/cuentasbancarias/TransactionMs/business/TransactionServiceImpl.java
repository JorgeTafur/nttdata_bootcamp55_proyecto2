package nttdata.primerproyecto.cuentasbancarias.TransactionMs.business;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;
import nttdata.primerproyecto.cuentasbancarias.TransactionMs.repository.TransactionRepository;
import nttdata.primerproyecto.cuentasbancarias.AccountMs.entity.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.host}")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    //final String accountPath = "http://" + serverHost + ":" + serverPort + "/cuentas";
    final String accountPath = "http://localhost:8080/cuentas";

    @Override
    public Transaction realizarDeposito(Transaction transaccion) {
        // Obtener los detalles de la cuenta destino desde AccountMs
        String numeroCuentaDestino = transaccion.getCuentaDestino();
        Account cuentaDestino = obtenerCuentaPorNumeroCuenta(numeroCuentaDestino, accountPath);

        // Actualizar el saldo de la cuenta destino
        if (cuentaDestino != null) {
            realizarDepositoCuenta(transaccion, cuentaDestino, accountPath);

            // Guardar la transacción
            transactionRepository.save(transaccion);
        } else {
            throw new IllegalArgumentException("Cuenta destino no encontrada");
        }
        return transaccion;
    }

    @Override
    public Transaction realizarRetiro(Transaction transaccion) {
        // Obtener los detalles de la cuenta origen desde AccountMs
        String numeroCuentaOrigen = transaccion.getCuentaOrigen();
        Account cuentaOrigen = obtenerCuentaPorNumeroCuenta(numeroCuentaOrigen, accountPath);

        // Validar saldo y actualizar la cuenta origen
        if (cuentaOrigen != null && cuentaOrigen.getSaldo() >= transaccion.getMonto()) {
            realizarRetiroCuenta(transaccion, cuentaOrigen, accountPath);

            // Guardar la transacción
            transactionRepository.save(transaccion);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente o cuenta origen no encontrada");
        }
        return transaccion;
    }

    @Override
    public Transaction realizarTransferencia(Transaction transaccion) {
        realizarRetiro(transaccion);
        realizarDeposito(transaccion);
        return transaccion;
    }

    @Override
    public List<Transaction> obtenerTransacciones() {
        return transactionRepository.findAll();
    }

    private Account obtenerCuentaPorNumeroCuenta(String numeroCuenta, String accountPath) {
        String url = accountPath + "/numeroCuenta/" + numeroCuenta;
        return restTemplate.getForObject(url, Account.class); // Realiza el GET a AccountM
    }

    public void realizarRetiroCuenta(Transaction transaccion, Account cuenta, String accountPath) {
        Integer accountId = cuenta.getId();
        String url = accountPath + "/" + accountId + "/withdraw";
        restTemplate.put(url, transaccion.getMonto(), Void.class);
    }

    public void realizarDepositoCuenta(Transaction transaccion, Account cuenta, String accountPath) {
        Integer accountId = cuenta.getId();
        String url = accountPath + "/" + accountId + "/deposit";
        restTemplate.put(url, transaccion.getMonto(), Void.class);
    }
}
