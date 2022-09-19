package com.autumn;

import com.autumn.repository.IEntidadLegalRepository;
import com.autumn.service.BufuService;
import com.autumn.service.UsuarioService;
import com.autumn.utils.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


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
