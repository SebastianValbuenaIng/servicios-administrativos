package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.DistincionResponse;

import java.util.Set;

public interface IDistincionesServices {
    Set<DistincionResponse> getDistinciones(Integer id);
}
