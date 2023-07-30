package br.com.confitec.teste.controller.rest;

import br.com.confitec.teste.model.api.request.ParcelamentoRequest;
import br.com.confitec.teste.model.api.response.Parcelamento;
import br.com.confitec.teste.model.api.response.ParcelamentoResponse;
import br.com.confitec.teste.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/confitec/teste/parcelamento")
@RequiredArgsConstructor
public class ParcelamentoController {

    private final ParcelamentoService service;

    @PostMapping
    public ParcelamentoResponse calcularParcelamento(@RequestBody ParcelamentoRequest request) {
        ParcelamentoResponse response = new ParcelamentoResponse();
        response.setDados(service.calcularParcelamento(request.getListOpcaoParcelamento(), request.getListCobertura()));
        return response;
    }
}
