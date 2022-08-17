package com.autumn.service;

import com.autumn.model.EntidadLegal;
import com.autumn.repository.IEntidadLegalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadLegalService {

    @Autowired
    private IEntidadLegalRepository repository;

    public void create(EntidadLegal entidadLegal){
        if (repository.findByNombre(entidadLegal.getNombre()) != null){
            repository.save(entidadLegal);
            //TODO: Borrar SOUT
            System.out.println("La entidad legal " + entidadLegal + " fue guardada exitosamente");
        }else {
            //TODO: Borrar SOUT
            System.out.println("Ya existe la entidad legal: " + entidadLegal);
        }
    }

    public void delete(Long id){

    }

    public void update(EntidadLegal entidadLegal){

    }

    public EntidadLegal get(Long id){
        return null;
    }

    public List<EntidadLegal> getAll(){
        return null;
    }
}
