package org.example.eventos.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.validation.Valid;
import org.example.eventos.dto.InscricaoRequestDTO;
import org.example.eventos.dto.InscricaoResponseDTO;
import org.example.eventos.model.Inscricao;
import org.example.eventos.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final InscricaoService service;

    public InscricaoController(InscricaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<InscricaoResponseDTO> listar(){
        return service.listarTodas();
    }

    @GetMapping("/buscar/{idEvento}")
    public List<InscricaoResponseDTO> filtrarPorNome(@PathVariable Long idEvento){
        return service.listarPorEvento(idEvento);
    }

    @GetMapping("/{idInscicao}")
    public InscricaoResponseDTO buscarPorId(@PathVariable Long idInscicao){
        return service.buscarPorId(idInscicao);
    }

    @PostMapping
    public InscricaoResponseDTO cadastrar(@RequestBody @Valid InscricaoRequestDTO inscricao){
        return service.cadastrar(inscricao);
    }

    @PutMapping("/{idInscicao}")
    public InscricaoResponseDTO atualiar(@PathVariable Long idInscicao, @RequestBody @Valid InscricaoRequestDTO dto){
        return service.atualizar(idInscicao, dto);
    }

    @DeleteMapping("/{idInscicao}")
    public void deletar(@PathVariable Long idInscicao){
        service.deletar(idInscicao);
    }
}
