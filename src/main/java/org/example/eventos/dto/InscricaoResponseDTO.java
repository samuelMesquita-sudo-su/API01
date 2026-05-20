package org.example.eventos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InscricaoResponseDTO(
        Long id,
        String nomeParticipante,
        String emailParticipante,
        String status,

        Long eventoId,
        String nomeEvento,
        String descricaoEvento,
        LocalDate dataEvento,
        Double valorIngresso
) {
}
