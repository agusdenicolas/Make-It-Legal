package com.autumn.service;

import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import com.autumn.repository.IEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private IEstadoRepository repository;

    public List<Estado> getAllByWorkflow(Workflow workflow){ return repository.findByWorkflow(workflow); }

    public Estado getOne(Long id){
        Optional<Estado> optionalEstado = repository.findById(id);
        if(optionalEstado.isPresent()){
            //.get() castea el "Optional" a "Estado"
            return optionalEstado.get();
        }
        return null;
    }
}
