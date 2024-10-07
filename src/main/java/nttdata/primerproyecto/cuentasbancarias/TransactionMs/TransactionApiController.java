package nttdata.primerproyecto.cuentasbancarias.TransactionMs;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;
import nttdata.primerproyecto.cuentasbancarias.TransactionMs.business.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransactionApiController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposito")
    @Operation(summary = "Registrar un depósito")
    public ResponseEntity<Transaction> createDeposit(@RequestBody Transaction transaction) {
        transaction.setTipo("DEPOSITO");
        transaction.setFecha(LocalDateTime.now());
        return ResponseEntity.status(201).body(transactionService.createTransaction(transaction));
    }

    @PostMapping("/retiro")
    @Operation(summary = "Registrar un retiro")
    public ResponseEntity<Transaction> createWithdrawal(@RequestBody Transaction transaction) {
        transaction.setTipo("RETIRO");
        transaction.setFecha(LocalDateTime.now());
        return ResponseEntity.status(201).body(transactionService.createTransaction(transaction));
    }

    @PostMapping("/transferencia")
    @Operation(summary = "Registrar una transferencia")
    public ResponseEntity<Transaction> createTransfer(@RequestBody Transaction transaction) {
        transaction.setTipo("TRANSFERENCIA");
        transaction.setFecha(LocalDateTime.now());
        return ResponseEntity.status(201).body(transactionService.createTransaction(transaction));
    }

    @GetMapping("/historial")
    @Operation(summary = "Consultar historial de transacciones")
    public ResponseEntity<List<Transaction>> getTransactionHistory() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
