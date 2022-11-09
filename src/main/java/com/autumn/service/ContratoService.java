package com.autumn.service;

import com.autumn.model.Contrato;
import com.autumn.model.Estado;
import com.autumn.model.Usuario;
import com.autumn.repository.IContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private IContratoRepository repository;

    public void create(Contrato nuevoContrato){
        repository.save(nuevoContrato);
    }

    public Contrato getOne(Long id){
        Optional<Contrato> optionalContrato = repository.findById(id);
        if(optionalContrato.isPresent()){
            //.get() castea el "Optional" a "Entidad Legal"
            return optionalContrato.get();
        }
        return null;
    }

    public List<Contrato> getAll(){
        return repository.findAll(); //TODO: Implement findAllByUsuario
    }

    public List<Contrato> getAllEnvioImpuestos(){ return repository.findAllByEstadoActual_Nombre("Envío Impuestos"); }

    public List<Contrato> getAllFirmaApoderados(){ return repository.findAllByEstadoActual_Nombre("Firma Apoderados"); }

    public List<Contrato> getAllByUsuarioLogeado(Usuario usuario){ return repository.findAllByUsuario(usuario); }

    public int getCountContratosSegunEstado(String estado){ return repository.countAllByEstadoActual_Nombre(estado); }

    public int getCountContratosSegunEstadoNoEsIgual(String estado){ return repository.countAllByEstadoActual_NombreNot(estado); }
}
