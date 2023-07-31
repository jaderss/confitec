package br.com.confitec.teste.model.api.request;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ParcelamentoRequest {
    @NotEmpty
    @Valid
    private List<OpcaoParcelamento> listOpcaoParcelamento;
    @NotEmpty
    @Valid
    private List<Cobertura> listCobertura;
}
