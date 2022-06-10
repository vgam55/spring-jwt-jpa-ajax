package com.cursojava.curso.controllers;

import com.cursojava.curso.DAO.UsuarioDao;
import com.cursojava.curso.Models.Usuario;
import com.cursojava.curso.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtils jwtUtil;


    @RequestMapping(value="api/usuarios" , method=RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String toke)
    {
        ArrayList<Usuario> usuarios=null;
        String idUsuario= jwtUtil.getKey(toke);
        if(idUsuario!=null)
        {
            usuarios= (ArrayList<Usuario>) usuarioDao.getUsuarios();
        }
        return usuarios;

    }

    @RequestMapping(value="api/login", method= RequestMethod.POST)

    public String login(@RequestBody Usuario usuario)
    {
        String tokenJwt="Fail";
        Usuario userLog=usuarioDao.findUsuarioByEmail(usuario);
        if(userLog!=null)
        {
            tokenJwt=jwtUtil.create(String.valueOf(userLog.getId()), userLog.getEmail());
        }

        return tokenJwt;
    }


}
