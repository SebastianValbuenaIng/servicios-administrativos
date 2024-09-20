package com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias;

import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.AspiranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<AspiranteEntity, String> {
}