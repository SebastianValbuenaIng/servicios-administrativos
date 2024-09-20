package com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.MembresiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembresiaRepository extends JpaRepository<MembresiaEntity, String> {
    List<MembresiaEntity> findAllByIdemp(String id);
}