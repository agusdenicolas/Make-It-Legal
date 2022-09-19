package com.autumn.repository;

import com.autumn.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByMail(String mail);

    List<Usuario> findByRol(String rol);

    List<Usuario> findByRolIs(String emptyRol);

    List<Usuario> findByRolIsNotAndRolIsNot(String rol, String emptyRol);

    List<Usuario> findUsuarioByBufusId(Long bufuId);
}
