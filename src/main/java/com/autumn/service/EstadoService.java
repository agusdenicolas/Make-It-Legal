package com.autumn.service;

import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import com.autumn.repository.IEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private IEstadoRepository repository;

    public List<Estado> getAllByWorkflow(Workflow workflow){ return repository.findByWorkflow(workflow); }
}
