package com.autumn.service;

import com.autumn.model.Contrato;
import com.autumn.model.Historial;
import com.autumn.repository.IHistorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private IHistorialRepository repository;

    public void create(Historial historial){
        repository.save(historial);
    }

    public List<Historial> getAllByContrato(Contrato contrato){
        List <Historial> historial = repository.findByContrato(contrato);
        return historial;
    }
}
