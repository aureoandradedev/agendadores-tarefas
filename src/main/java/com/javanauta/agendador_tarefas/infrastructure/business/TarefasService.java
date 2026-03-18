package com.javanauta.agendador_tarefas.infrastructure.business;

import com.javanauta.agendador_tarefas.infrastructure.business.dto.TarefasDTORecord;
import com.javanauta.agendador_tarefas.infrastructure.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendador_tarefas.infrastructure.business.mapper.TarefasConverter;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.javanauta.agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendador_tarefas.infrastructure.execeptions.ResourceNotFoundException;
import com.javanauta.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanauta.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {
    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(), dto.descricao(), LocalDateTime.now(), dto.dataEvento(),
                email,null, StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);

        return tarefaConverter.paraTarefaDTO(
                tarefasRepository.save(entity));
    }

    public List<TarefasDTORecord> buscarTarefasAgendadaPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDTORecord(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE ));
    }

    public List<TarefasDTORecord> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefasEntity> listaTarefas = tarefasRepository.findByEmailUsuario(email);

        return tarefaConverter.paraListaTarefasDTORecord(listaTarefas);
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {

            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }

    public TarefasDTORecord alterarStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }

    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateTarefas(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefasRepository.save(entity));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }
    }

}
