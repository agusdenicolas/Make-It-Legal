package com.autumn.repository;

import com.autumn.model.Chat;
import com.autumn.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT NEW chat(c.id, c.mensaje, c.fecha, c.usuario) FROM chat c WHERE c.contrato = ?1")
    List<Chat> findByContrato(Contrato contrato);
}
