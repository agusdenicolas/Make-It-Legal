package com.autumn.repository;

import com.autumn.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByMail(String mail);

    //TODO: Change "rol" to Enum
    List<Usuario> findByRol(String rol);

    List<Usuario> findUsuarioByBufusId(Long bufuId);
}
