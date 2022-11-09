package com.autumn.service;

import com.autumn.model.Workflow;
import com.autumn.repository.IWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkflowService {

    @Autowired
    private IWorkflowRepository repository;

    public List<Workflow> getAll(){
        return repository.findAll();
    }
}
