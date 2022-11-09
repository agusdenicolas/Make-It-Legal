package com.autumn;

import com.autumn.model.*;
import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.repository.IUsuarioRepository;
import com.autumn.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
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

	@Autowired
	private ChatService chatService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private IUsuarioRepository repo;
	@Test
	public void createTest() {

	}
}
