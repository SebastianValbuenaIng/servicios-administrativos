package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.IdiomaResponse;

import java.util.Set;

public interface IIdiomaService {
    Set<IdiomaResponse> findAllByCedula(String cedula);
}
