package com.javanauta.agendador_tarefas.infrastructure.controller;

import com.javanauta.agendador_tarefas.infrastructure.business.TarefasService;
import com.javanauta.agendador_tarefas.infrastructure.business.dto.TarefasDTORecord;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTORecord> gravarTarefas(@RequestBody TarefasDTORecord dto, @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTORecord>> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadaPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTORecord>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTORecord> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status, @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefasService.alterarStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTORecord> updateTarefas(@RequestBody TarefasDTORecord dto, @RequestParam("id")String id){

    return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
    }
}
