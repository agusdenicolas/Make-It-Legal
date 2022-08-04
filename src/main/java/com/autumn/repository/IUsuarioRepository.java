package com.autumn.repository;

import com.autumn.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByMail(String mail);

}
