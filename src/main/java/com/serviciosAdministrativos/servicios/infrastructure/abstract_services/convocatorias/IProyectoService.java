package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ProyectoResponse;

import java.util.Set;

public interface IProyectoService {
    Set<ProyectoResponse> getProyectos(Integer id);
}
