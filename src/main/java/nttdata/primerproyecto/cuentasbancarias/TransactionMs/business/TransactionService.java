package nttdata.primerproyecto.cuentasbancarias.TransactionMs.business;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}