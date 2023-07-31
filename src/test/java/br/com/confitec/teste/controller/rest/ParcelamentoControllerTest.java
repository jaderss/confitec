package br.com.confitec.teste.controller.rest;

import br.com.confitec.teste.model.api.response.ParcelamentoResponse;
import br.com.confitec.teste.service.ParcelamentoService;
import com.adelean.inject.resources.junit.jupiter.GivenJsonResource;
import com.adelean.inject.resources.junit.jupiter.GivenTextResource;
import com.adelean.inject.resources.junit.jupiter.TestWithResources;
import com.adelean.inject.resources.junit.jupiter.WithJacksonMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcelamentoController.class)
@TestWithResources
class ParcelamentoControllerTest {

    @WithJacksonMapper
    ObjectMapper objectMapper = new ObjectMapper();

    @GivenTextResource("request.json")
    String request;

    @GivenTextResource("request_parcela_conflito.json")
    String requestParcelaConflito;

    @GivenTextResource("request_sem_coberturas.json")
    String requestSemCoberturas;

    @GivenTextResource("request_sem_opcoes.json")
    String requestSemOpcoes;

    @GivenTextResource("request_juros_negativos.json")
    String requestJurosNegativos;

    @GivenTextResource("request_parcelas_invalidas.json")
    String requestParcelasInvalidas;

    @GivenJsonResource("response.json")
    ParcelamentoResponse expectedResponse;


    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private ParcelamentoService parcelamentoService;

    @Test
    void testParcelamentoComSucesso() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        ParcelamentoResponse actual = objectMapper.readValue(result.getResponse().getContentAsString(), ParcelamentoResponse.class);
        assertEquals(expectedResponse, actual);
    }

    @Test
    void testParcelamentoComNumeroParcelasEmConflitoDeveFalhar() throws Exception {
        this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(requestParcelaConflito)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testParcelamentoComJurosNegativosDeveFalhar() throws Exception {
        this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(requestJurosNegativos)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testParcelamentoSemCoberturasDeveFalhar() throws Exception {
        this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(requestSemCoberturas)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testParcelamentoSemOpcoesDeveFalhar() throws Exception {
        this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(requestSemOpcoes)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testParcelamentoNumParcelasInvalidoDeveFalhar() throws Exception {
        this.mockMvc.perform(post("/confitec/teste/parcelamento")
                        .content(requestParcelasInvalidas)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
