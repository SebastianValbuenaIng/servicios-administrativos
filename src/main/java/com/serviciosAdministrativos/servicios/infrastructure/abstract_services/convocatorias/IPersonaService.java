package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PersonaResponse;

import java.util.Set;

public interface IPersonaService {
    Set<PersonaResponse> getPersonas();
}
