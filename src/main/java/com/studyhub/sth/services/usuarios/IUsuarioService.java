package com.studyhub.sth.services.usuarios;

import com.studyhub.sth.dtos.users.NovoUsuarioDto;
import com.studyhub.sth.entities.Usuario;

public interface IUsuarioService {
    public Usuario criar(NovoUsuarioDto novoUsuarioDto);
    public Usuario atualizar();
    public Usuario detalhar();
    public Usuario listar();
}
