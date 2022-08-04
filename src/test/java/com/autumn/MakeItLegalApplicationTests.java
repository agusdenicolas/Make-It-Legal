package com.autumn;

import com.autumn.model.Usuario;
import com.autumn.repository.IUsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MakeItLegalApplicationTests {

	@Autowired
	private IUsuarioRepository repo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	public void crearUsuarioTest() {
		Usuario u = new Usuario();
		u.setId(2L);
		u.setNombre("Negro");
		u.setApellido("Perez");
		u.setMail("negro@basf.com");
		u.setContrasena(encoder.encode("123456"));
		u.setRol("IBP");
		u.setEntidadLegalId(1L);

		Usuario retorno = repo.save(u);

		Assertions.assertTrue(retorno.getContrasena().equalsIgnoreCase(u.getContrasena()));
	}

}
