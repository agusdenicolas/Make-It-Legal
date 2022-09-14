package com.autumn.repository;

import com.autumn.model.BUFU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBufuRepository extends JpaRepository<BUFU, Long> {

    List<BUFU> findBUFUByIbpsId(Long ibpId);

    BUFU findByNombre(String nombre);
}
