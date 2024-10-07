package nttdata.primerproyecto.cuentasbancarias.TransactionMs.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Data
@Document(collection = "transactions")
public class Transaction {
    @Id
    private Integer id;

    private String numeroCuenta;
    private Double saldo;
    private TipoTransaction tipoTransaction;
    private Integer clienteId;
}
