package com.autumn.repository;

import com.autumn.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkflowRepository extends JpaRepository<Workflow, Long> {
}
