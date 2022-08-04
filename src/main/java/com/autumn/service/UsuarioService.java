package com.autumn.service;

import com.autumn.model.Usuario;
import com.autumn.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByMail(username);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol()));

        UserDetails userDetails = new User(usuario.getMail(), usuario.getContrasena(), roles);

        return userDetails;
    }

    public boolean[] register(String username){
        Usuario usuario = repository.findByMail(username);

        //[0] -> existe el usuario, [1] -> mail @basf.com
        boolean validaciones [] = {false, false};

        if(usuario != null){
            //El usuario ya existe
            validaciones[0] = true;
        }
        if(!username.matches(".+@basf\\.com")){
            //El usuario no es de dominio Basf
            validaciones[1] = true;
        }
        if(!validaciones[0] && !validaciones[1]){
            //repository.save();
        }
        return validaciones;
    }
}
