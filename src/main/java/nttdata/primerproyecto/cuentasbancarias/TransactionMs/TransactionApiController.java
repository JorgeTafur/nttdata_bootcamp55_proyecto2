package nttdata.primerproyecto.cuentasbancarias.TransactionMs;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.business.TransactionService;
import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransactionApiController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionApiController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Obtener todas las transaccions
    @GetMapping
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        List<Transaction> transactions = transactionService.listAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Crear una nueva transaccion
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    // Obtener una transaccion por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar una transaccion
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable Integer id, @RequestBody Double amount) {
        Transaction updatedTransaction = transactionService.deposit(id, amount);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    // Retirar dinero de una transaccion
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Transaction> withdraw(@PathVariable Integer id, @RequestBody Double amount) {
        Transaction updatedTransaction = transactionService.withdraw(id, amount);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }
}
