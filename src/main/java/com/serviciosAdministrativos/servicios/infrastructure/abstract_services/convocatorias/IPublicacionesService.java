package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PublicacionesResponse;

import java.util.Set;

public interface IPublicacionesService {
    Set<PublicacionesResponse> getPublicaciones(Integer id);
}
