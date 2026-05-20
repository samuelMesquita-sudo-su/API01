package org.example.eventos.dto;

import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.eventos.model.Evento;

public record InscricaoRequestDTO(
        @NotBlank(message = "O participante esta sem nome")
        String nomeParticipante,
        //pode-se usar o @Email para validar no DTO!
        @Email(message = "Informe um endereco de email valido")
        @NotBlank(message = "O endereco de email esta vazio")
        String emailParticipante,
        @NotBlank(message = "Deve haver o status")
        String status,
        @NotNull(message = "O id do evento esta vazio")
        Long eventoId
) {
}
