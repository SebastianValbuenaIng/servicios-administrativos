package com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.IdiomasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdiomasRegistroRepository extends JpaRepository<IdiomasEntity, String> {
    List<IdiomasEntity> findAllByIdemp(String id);
}