package com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ExperienceResponse;

import java.util.Set;

public interface IExperienceService {
    Set<ExperienceResponse> getExperiencia(Integer id);
}
