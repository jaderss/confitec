package br.com.confitec.teste.model.api.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpcaoParcelamento {
    private Integer quantidadeMinimaParcelas;
    private Integer quantidadeMaximaParcelas;
    private BigDecimal juros;
}
