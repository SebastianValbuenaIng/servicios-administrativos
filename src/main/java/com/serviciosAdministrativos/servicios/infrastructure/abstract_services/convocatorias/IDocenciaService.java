package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.DocenciaResponse;

import java.util.Set;

public interface IDocenciaService {
    Set<DocenciaResponse> getDocencia(Integer id);
}
