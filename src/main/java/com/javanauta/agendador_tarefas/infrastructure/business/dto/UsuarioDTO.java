package com.javanauta.agendador_tarefas.infrastructure.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioDTO {

    private String senha;
    private String email;


}
