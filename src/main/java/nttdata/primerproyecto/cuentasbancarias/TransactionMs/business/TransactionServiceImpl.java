package nttdata.primerproyecto.cuentasbancarias.TransactionMs.business;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;
import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.TipoTransaction;
import nttdata.primerproyecto.cuentasbancarias.TransactionMs.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> listAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getSaldo() <= 0) {
            throw new IllegalArgumentException("El saldo inicial debe ser mayor a 0.");
        }
        // Reconozco que esto no es optimo
        // al agregar mas tipos de transaction habria que editarlo y no cumpliria con las buenas practicas
        //para el siguiente proyecto tomaré medidas mas flexibles para cumplir con los requisitos
        if (transaction.getTipoTransaction() == null ||
                (!transaction.getTipoTransaction().equals(TipoTransaction.AHORROS) && !transaction.getTipoTransaction().equals(TipoTransaction.CORRIENTE))) {
            throw new IllegalArgumentException("El tipo de transaction debe ser AHORROS o CORRIENTE.");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction deposit(Integer id, Double amount) {
        Transaction transaction = getTransactionById(id);
        if (transaction != null) {
            transaction.setSaldo(transaction.getSaldo() + amount);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public Transaction withdraw(Integer id, Double amount) {
        Transaction transaction = getTransactionById(id);
        if (transaction == null)
            throw new IllegalArgumentException("No existe la transaction");
        if (transaction.getSaldo() < amount)
            throw new IllegalArgumentException("No hay saldo suficiente");
        transaction.setSaldo(transaction.getSaldo() - amount);
        return transactionRepository.save(transaction);
    }
}
