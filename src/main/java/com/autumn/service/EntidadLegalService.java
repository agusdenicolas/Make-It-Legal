package com.autumn.service;

import com.autumn.model.EntidadLegal;
import com.autumn.repository.IEntidadLegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadLegalService {

    @Autowired
    private IEntidadLegalRepository repository;

    public boolean create(EntidadLegal entidadLegal){
        if (repository.findByNombre(entidadLegal.getNombre()) == null){
            repository.save(entidadLegal);
            return false;
        }
        return true;
    }

    public boolean update(EntidadLegal entidadLegal){
        EntidadLegal entidadLegalExists = repository.findByNombre(entidadLegal.getNombre());
        if( entidadLegalExists == null || entidadLegalExists.getId() == entidadLegal.getId()) {
            repository.save(entidadLegal);
            return false;
        }
        return true;
    }

    public EntidadLegal getOne(Long id){
        Optional<EntidadLegal> optionalEntidadLegal = repository.findById(id);
        if(optionalEntidadLegal.isPresent()){
            //.get() castea el "Optional" a "Entidad Legal"
            return optionalEntidadLegal.get();
        }
        return null;
    }

    public List<EntidadLegal> getAll(){
        return repository.findAll();
    }
}
