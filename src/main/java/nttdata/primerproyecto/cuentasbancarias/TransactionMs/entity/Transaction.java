package nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String tipo; // DEPOSITO, RETIRO, TRANSFERENCIA
    private Double monto;
    private LocalDateTime fecha;
    private String cuentaOrigen;
    private String cuentaDestino;
}