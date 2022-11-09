package com.autumn.repository;

import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Long> {
    List<Estado> findByWorkflow(Workflow workflow);

    Estado findFirstByWorkflow(Workflow workflow);
}
