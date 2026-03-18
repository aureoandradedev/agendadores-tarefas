package com.javanauta.agendador_tarefas.infrastructure.business.mapper;

import com.javanauta.agendador_tarefas.infrastructure.business.dto.TarefasDTORecord;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper (componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    void updateTarefas (TarefasDTORecord dto, @MappingTarget TarefasEntity entity);
}
