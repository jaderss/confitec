package br.com.confitec.teste.model.api.request;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Cobertura {
    @NotNull
    private Integer cobertura;
    @DecimalMin(value = "0.01", message = "É obrigatório informar o valor da cobertura.")
    private BigDecimal valor;
}
