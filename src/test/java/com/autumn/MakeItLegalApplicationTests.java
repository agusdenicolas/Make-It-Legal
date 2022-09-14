package com.autumn;

import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.service.BufuService;
import com.autumn.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MakeItLegalApplicationTests {

	@Autowired
	private BufuService bufuService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IEntidadLegalRepository entidadLegalRepository;

	@Test
	public void createTest() {
	}
}
