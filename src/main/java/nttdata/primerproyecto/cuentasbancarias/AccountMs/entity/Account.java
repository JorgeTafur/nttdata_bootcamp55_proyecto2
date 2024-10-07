package nttdata.primerproyecto.cuentasbancarias.AccountMs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numeroCuenta;
    private Double saldo;
    private TipoCuenta tipoCuenta;

    @Column(name = "cliente_id")
    private Integer clienteId;
}
