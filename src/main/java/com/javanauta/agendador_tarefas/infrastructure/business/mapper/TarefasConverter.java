package com.javanauta.agendador_tarefas.infrastructure.business.mapper;


import com.javanauta.agendador_tarefas.infrastructure.business.dto.TarefasDTO;
import com.javanauta.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper (componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    TarefasEntity paraTarefaEntity (TarefasDTO dto);

    TarefasDTO paraTarefaDTO (TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity (List<TarefasDTO> dtos);

    List<TarefasDTO> paraListaTarefasDTO (List<TarefasEntity> entities);


}
