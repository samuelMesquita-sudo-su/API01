package org.example.eventos.service;

import org.example.eventos.dto.EventoRequestDTO;
import org.example.eventos.dto.EventoResponseDTO;
import org.example.eventos.exception.RecursoNaoEncontradoException;
import org.example.eventos.model.Evento;
import org.example.eventos.model.LocalEvento;
import org.example.eventos.repository.EventoRepository;
import org.example.eventos.repository.LocalEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final LocalEventoRepository localRepository;

    public EventoService(EventoRepository eventoRepository, LocalEventoRepository localRepository) {
        this.eventoRepository = eventoRepository;
        this.localRepository = localRepository;
    }

    public List<EventoResponseDTO> listarTodos(){
        return eventoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public EventoResponseDTO buscarPorId(Long idEvento){
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new RecursoNaoEncontradoException("ID não encontrado"));
        Evento salvo = eventoRepository.save(evento);
        return toResponseDTO(salvo);
    }

    public EventoResponseDTO cadastrar(EventoRequestDTO dto){
        LocalEvento localEvento = localRepository.findById(dto.localId()).orElseThrow(() -> new RecursoNaoEncontradoException("ID nao encontrado"));
        Evento evento = new Evento();

        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setDataEvento(dto.dataEvento());
        evento.setValorIngresso(dto.valorIngresso());
        evento.setLocalId(localEvento);

        Evento salvo = eventoRepository.save(evento);

        return toResponseDTO(salvo);
    }

    public EventoResponseDTO atualizar(Long idEvento, EventoRequestDTO dto){
        LocalEvento localEvento = localRepository.findById(dto.localId()).orElseThrow(() -> new RecursoNaoEncontradoException("ID nao encontrado de local evento"));
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new RecursoNaoEncontradoException("ID não encontrado"));

        evento.setNome(dto.nome());
        evento.setDescricao(dto.descricao());
        evento.setDataEvento(dto.dataEvento());
        evento.setValorIngresso(dto.valorIngresso());
        evento.setLocalId(localEvento);

        Evento atualizada = eventoRepository.save(evento);
        return toResponseDTO(atualizada);
    }

    public void deletar(Long idEvento){
        eventoRepository.deleteById(idEvento);
    }

    public List<EventoResponseDTO> filtrarPorNome(String nome){
        return eventoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<EventoResponseDTO> listarPorLocal(Long idLocal){
        return eventoRepository.findByLocalId(idLocal).stream().map(this::toResponseDTO).toList();
    }

    public EventoResponseDTO toResponseDTO(Evento evento){
        LocalEvento localEvento = evento.getLocalId();
        return new EventoResponseDTO(
                evento.getIdEvento(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataEvento(),
                evento.getValorIngresso(),
                localEvento.getIdLocal(),
                localEvento.getNome(),
                localEvento.getEndereco(),
                localEvento.getCapacidade()
        );
    }
}
