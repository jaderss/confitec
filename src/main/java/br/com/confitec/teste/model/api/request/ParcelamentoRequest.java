package br.com.confitec.teste.model.api.request;

import lombok.Data;

import java.util.List;

@Data
public class ParcelamentoRequest {
    private List<OpcaoParcelamento> listOpcaoParcelamento;
    private List<Cobertura> listCobertura;
}
