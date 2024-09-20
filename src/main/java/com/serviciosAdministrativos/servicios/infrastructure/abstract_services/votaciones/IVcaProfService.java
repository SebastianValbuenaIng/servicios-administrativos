package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.votaciones;

import com.serviciosAdministrativos.servicios.domain.entities.DBTwo.CandidatosEntity;

import java.util.List;
import java.util.Map;

public interface IVcaProfService {
    Map<String, String> verificarEstadoVoto(String email);
    List<CandidatosEntity> buscarCandidatos();
}
