package com.autumn;

import com.autumn.model.EntidadLegal;
import com.autumn.model.Usuario;
import com.autumn.repository.IEntidadLegalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MakeItLegalApplicationTests {

	@Autowired
	private IEntidadLegalRepository repo;

	@Test
	public void createTest() {
		EntidadLegal entidadLegal = new EntidadLegal();
		/*//entidadLegal.setId(2L);
		entidadLegal.setNombre("Ent Legal Test 2");
		entidadLegal.set_activo(false);
		repo.save(entidadLegal);
		System.out.println("Entity Saved: " + entidadLegal);*/
		entidadLegal.setId(2L);
		repo.delete(entidadLegal);
	}

}
