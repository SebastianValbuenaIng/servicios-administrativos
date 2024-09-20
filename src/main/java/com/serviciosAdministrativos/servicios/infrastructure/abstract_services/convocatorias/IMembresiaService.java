package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.MembresiaResponse;

import java.util.Set;

public interface IMembresiaService {
    Set<MembresiaResponse> findAllByIdemp(String id);
}
