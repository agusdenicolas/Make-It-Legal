package com.autumn.repository;

import com.autumn.model.Contrato;
import com.autumn.model.Estado;
import com.autumn.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findAllByEstadoActual_Nombre(String estadoActual);

    List<Contrato> findAllByUsuario(Usuario usuario);

    int countAllByEstadoActual_Nombre(String estado);

    int countAllByEstadoActual_NombreNot(String estado);
}
