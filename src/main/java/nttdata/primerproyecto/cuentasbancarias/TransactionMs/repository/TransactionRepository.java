package nttdata.primerproyecto.cuentasbancarias.TransactionMs.repository;

import nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}