package nttdata.primerproyecto.cuentasbancarias.TransactionMs.business;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> listAllTransactions();
    Transaction createTransaction(Transaction transaction);
    Transaction getTransactionById(Integer id);
    void deleteTransaction(Integer id);
    Transaction deposit(Integer id, Double amount);
    Transaction withdraw(Integer id, Double amount);
}