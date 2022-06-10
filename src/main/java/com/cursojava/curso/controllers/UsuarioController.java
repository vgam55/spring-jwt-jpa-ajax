package com.cursojava.curso.controllers;

import com.cursojava.curso.DAO.UsuarioDao;
import com.cursojava.curso.Models.Usuario;
import com.cursojava.curso.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtils jwtUtil;

    private String validarToken(String token)
    {
        String usuarioIdjwtUtil.getKey(toke);
        return usuarioId;
    }

    @RequestMapping(value="api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id )
    {
        Usuario usuario=new Usuario();
        usuario.setId(id);
        usuario.setNombre("Víctor Manuel");
        usuario.setApellidos("Gamarra Rodríguez");
        usuario.setEmail("beorn57@yahoo.es");
        usuario.setTelefono("123456789");
        return usuario;
    }

    @RequestMapping(value="api/usuarios")
    public List<Usuario> getAll(@RequestHeader(value = "Autorizathion") String token)
    {
        ArrayList<Usuario> usuarios=null;
        if(validarToken(token)!=null)
        {
            usuarios= (ArrayList<Usuario>) usuarioDao.getUsuarios();
        }
        return usuarios;
    }

    @RequestMapping(value="api/usuarios", method=RequestMethod.POST)
    public void addUsuario(@RequestBody Usuario usuario)
    {

         usuarioDao.addUsuario(usuario);
    }
    @RequestMapping(value="api/usuarios/{id}", method= RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id,@RequestHeader(value = "Autorizathion") String token)
    {
        if(validarToken(token)!=null) {
            usuarioDao.eliminar(id);
        }
    }
}
