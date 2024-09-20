package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.EstudiosResponse;

import java.util.Set;

public interface IEstudioService {
    Set<EstudiosResponse> getEstudios(String id);
}
