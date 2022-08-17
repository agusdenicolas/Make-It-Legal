package com.autumn.repository;

import com.autumn.model.EntidadLegal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntidadLegalRepository extends JpaRepository<EntidadLegal, Long> {
    EntidadLegal findByNombre(String nombre);
}
