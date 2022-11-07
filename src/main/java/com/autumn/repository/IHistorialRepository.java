package com.autumn.repository;

import com.autumn.model.Contrato;
import com.autumn.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistorialRepository extends JpaRepository<Historial, Long> {

    @Query("SELECT NEW historial(h.id, h.descripcion, h.fecha) FROM historial h WHERE h.contrato = ?1")
    List<Historial> findByContrato(Contrato contrato);
}
