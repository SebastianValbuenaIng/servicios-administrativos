package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ExperienceResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IExperienceService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExperienceService implements IExperienceService {
    private final ConvocatoriaRepository convocatoriaRepository;

    public ExperienceService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<ExperienceResponse> getExperiencia(Integer id) {
        List<Map<String, Object>> experience = convocatoriaRepository.experience(String.valueOf(id));
        return experience.stream().map(this::entityResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private ExperienceResponse entityResponse(Map<String, Object> experience) {
        ExperienceResponse response = new ExperienceResponse();

        response.setIdhis((Integer) experience.get("idhis"));
        response.setNomorg((String) experience.get("nomorg"));
        response.setPeriodo((String) experience.get("periodo"));
        response.setNombre_cargo((String) experience.get("nombre_cargo"));
        response.setTipo_cargo((String) experience.get("tipo_cargo"));
        response.setFini((String) experience.get("fini"));
        response.setFfin((String) experience.get("ffin"));

        return response;
    }
}