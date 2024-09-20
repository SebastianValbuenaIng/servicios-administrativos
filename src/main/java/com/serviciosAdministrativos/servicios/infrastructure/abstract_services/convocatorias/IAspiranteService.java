package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.AspiranteResponse;

import java.util.Map;
import java.util.Set;

public interface IAspiranteService {
    Set<AspiranteResponse> getAspirantes(Integer id);
}
