package com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.UsuariosEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuariosRepository extends CrudRepository<UsuariosEntity, Integer> {
    Optional<UsuariosEntity> findByCodEmp(String cod_emp);
    Optional<UsuariosEntity> findByCorreo(String correo);
}
