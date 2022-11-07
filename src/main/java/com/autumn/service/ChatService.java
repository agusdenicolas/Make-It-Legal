package com.autumn.service;

import com.autumn.model.Chat;
import com.autumn.model.Contrato;
import com.autumn.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private IChatRepository repository;

    public void create(Chat chat){ repository.save(chat); }

    public List<Chat> getAllByContrato(Contrato contrato){
        return repository.findByContrato(contrato);
    }
}
