package com.cursojava.curso.DAO;

import com.cursojava.curso.Models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void addUsuario(Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getContrasenia());
        usuario.setContrasenia(hash);
        entityManager.merge(usuario);
    }

    @Override
    public List<Usuario> getUsuario() {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario findUsuarioByEmail(Usuario usuario) {
        Usuario user=null;
        String query = "FROM Usuario WHERE email=:email";
        String correcto="";
        List<Usuario> lista = entityManager.createQuery(query).
                setParameter("email", usuario.getEmail()).
                getResultList();

        try{
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
           if( argon2.verify(lista.get(0).getContrasenia(), usuario.getContrasenia()))
           {
               user=lista.get(0);
           }
        }catch(NullPointerException e)
        {
            user=null;
        }
        return user;
    }
}

