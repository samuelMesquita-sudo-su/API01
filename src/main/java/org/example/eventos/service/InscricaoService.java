package org.example.eventos.service;

import org.example.eventos.dto.InscricaoRequestDTO;
import org.example.eventos.dto.InscricaoResponseDTO;
import org.example.eventos.exception.RecursoNaoEncontradoException;
import org.example.eventos.model.Evento;
import org.example.eventos.model.Inscricao;
import org.example.eventos.repository.EventoRepository;
import org.example.eventos.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {

    //@Autowired
    private final InscricaoRepository inscricaoRepository;

    //@Autowired
    private final EventoRepository eventoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository, EventoRepository eventoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.eventoRepository = eventoRepository;
    }

    public List<InscricaoResponseDTO> listarTodas() {
        return inscricaoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    public InscricaoResponseDTO buscarPorId(Long id) {
        Inscricao inscricao = inscricaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Inscrição não encontrada"));

        return converterParaResponse(inscricao);
    }

    public InscricaoResponseDTO cadastrar(InscricaoRequestDTO dto) {
        Evento evento = eventoRepository.findById(dto.eventoId()).orElseThrow(() -> new RuntimeException("Id não encontrado"));
                //TODO: adicionar a exception

        Inscricao inscricao = new Inscricao();

        inscricao.setNomeParticipante(dto.nomeParticipante());
        inscricao.setEmailParticipante(dto.emailParticipante());
        inscricao.setStatus(dto.status());
        inscricao.setEventoId(evento);

        Inscricao salva = inscricaoRepository.save(inscricao);

        return converterParaResponse(salva);
    }

    public InscricaoResponseDTO atualizar(Long id, InscricaoRequestDTO dto) {
        Inscricao inscricao = inscricaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado!"));
                //TODO: adicionar a exception

        Evento evento = eventoRepository.findById(dto.eventoId()).orElseThrow(() -> new RuntimeException("Id não encontrado!"));
                //TODO: adicionar a exception

        inscricao.setNomeParticipante(dto.nomeParticipante());
        inscricao.setEmailParticipante(dto.emailParticipante());
        inscricao.setStatus(dto.status());
        inscricao.setEventoId(evento);

        Inscricao atualizada = inscricaoRepository.save(inscricao);

        return converterParaResponse(atualizada);
    }

    public void deletar(Long id) {
        Inscricao inscricao = inscricaoRepository.findById(id).orElseThrow(() -> new RuntimeException("ID não encontrado"));
                    //TODO: adicionar a exception

        //TODO: chamar método de deletar do repository
        inscricaoRepository.deleteById(id);
    }

    public List<InscricaoResponseDTO> listarPorEvento(Long idEvento) {
        return inscricaoRepository.findByEventoId(idEvento)
                .stream()
                .map(this::converterParaResponse)
                .toList();
    }

    private InscricaoResponseDTO converterParaResponse(Inscricao inscricao) {
        Evento evento = inscricao.getEventoId();
        return new InscricaoResponseDTO(
                //TODO: fazer os gets de "inscricao" conforme o que deve aparecer no response
                inscricao.getIdInscricao(),
                inscricao.getNomeParticipante(),
                inscricao.getEmailParticipante(),
                inscricao.getStatus(),
                evento.getIdEvento(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataEvento(),
                evento.getValorIngresso()
        );
    }
}