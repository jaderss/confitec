package br.com.confitec.teste.model.api.response;

import java.util.List;
import lombok.Data;

@Data
public class Response{
    private List<Parcelamento> dados;
}
