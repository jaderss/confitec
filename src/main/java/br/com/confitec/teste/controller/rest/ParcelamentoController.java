package br.com.confitec.teste.controller.rest;

import br.com.confitec.teste.model.api.request.ParcelamentoRequest;
import br.com.confitec.teste.model.api.response.ParcelamentoResponse;
import br.com.confitec.teste.service.ParcelamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/confitec/teste/parcelamento")
@RequiredArgsConstructor
public class ParcelamentoController {

    private final ParcelamentoService service;

    @PostMapping
    public ParcelamentoResponse calcularParcelamentos(@Valid @RequestBody ParcelamentoRequest request) {
        ParcelamentoResponse response = new ParcelamentoResponse();
        response.setDados(service.calcularParcelamentos(request.getListOpcaoParcelamento(), request.getListCobertura()));
        return response;
    }
}
