package nttdata.primerproyecto.cuentasbancarias.TransactionMs.business;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction realizarDeposito(Transaction transaccion);
    Transaction realizarRetiro(Transaction transaccion);
    Transaction realizarTransferencia(Transaction transaccion);
    List<Transaction> obtenerTransacciones();
}
