package com.autumn;

import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.service.BufuService;
import com.autumn.service.EstadoService;
import com.autumn.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MakeItLegalApplicationTests {

	@Autowired
	private BufuService bufuService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private IEntidadLegalRepository entidadLegalRepository;

	@Autowired
	private EstadoService estadoService;

	@Test
	public void createTest() {
	}
}
