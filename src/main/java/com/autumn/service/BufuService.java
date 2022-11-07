package com.autumn.service;

import com.autumn.model.BUFU;
import com.autumn.repository.IBufuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BufuService {

    @Autowired
    private IBufuRepository repository;

    public boolean create(BUFU bufu){
        if (repository.findByNombre(bufu.getNombre()) == null){
            repository.save(bufu);
            return false;
        }
        return true;
    }

    public boolean update(BUFU bufu){
        BUFU bufuExists = repository.findByNombre(bufu.getNombre());
        if( bufuExists == null || bufuExists.getId() == bufu.getId()) {
            repository.save(bufu);
            return false;
        }
        return true;
    }

    public BUFU getOne(Long id){
        Optional<BUFU> optionalbufu = repository.findById(id);
        if(optionalbufu.isPresent()){
            //.get() castea el "Optional" a "BUFU"
            return optionalbufu.get();
        }
        return null;
    }

    public List<BUFU> getAll(){
        return repository.findAll();
    }

    public List<BUFU> getAllActive(){
        return repository.findBUFUByIsActivoTrue();
    }
}
