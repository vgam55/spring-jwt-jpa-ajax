package com.cursojava.curso.DAO;

import com.cursojava.curso.Models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuario();
    List<Usuario> getUsuarios();
    void addUsuario(Usuario usuario);
    void eliminar(Long id);
    Usuario findUsuarioByEmail(Usuario usuario);
}
