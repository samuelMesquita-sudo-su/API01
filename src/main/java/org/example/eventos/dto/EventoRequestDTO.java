package org.example.eventos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoRequestDTO(

        //TODO: colocar validações de entrada de dados
        @NotBlank(message = "O nome esta vazio")
        String nome,
        @NotBlank(message = "A descricao esta vazio")
        String descricao,
        @NotNull(message = "a data esta vazia")
        LocalDate dataEvento,
        @NotNull(message = "O valor esta vazio")
        Double valorIngresso,
        @NotNull(message = "O valor esta nulo")
        Long localId
) {
}
