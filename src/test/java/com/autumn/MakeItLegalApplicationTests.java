package com.autumn;

import com.autumn.model.EntidadLegal;
import com.autumn.model.Usuario;
import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.service.EntidadLegalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MakeItLegalApplicationTests {

	@Autowired
	private EntidadLegalService service;

	@Test
	public void createTest() {
		EntidadLegal entidadLegal = new EntidadLegal();
		entidadLegal.setId(3L);

        System.out.println(service.getAll());
	}

}
