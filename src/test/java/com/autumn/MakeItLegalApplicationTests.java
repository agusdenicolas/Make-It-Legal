package com.autumn;

import com.autumn.model.Contrato;
import com.autumn.model.Estado;
import com.autumn.model.Workflow;
import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.service.*;
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
	private ContratoService contratoService;

	@Autowired
	private EstadoService estadoService;

	@Autowired
	private HistorialService historialService;

	@Test
	public void createTest() {

	}
}
