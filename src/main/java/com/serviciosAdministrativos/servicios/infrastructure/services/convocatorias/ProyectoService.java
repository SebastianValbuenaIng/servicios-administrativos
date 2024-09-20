package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;


import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ProyectoResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IProyectoService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProyectoService implements IProyectoService {
    private final ConvocatoriaRepository convocatoriaRepository;

    public ProyectoService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<ProyectoResponse> getProyectos(Integer id) {
        List<Map<String, Object>> proyecto = convocatoriaRepository.proyecto(String.valueOf(id));
        return proyecto.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private ProyectoResponse entityToResponse(Map<String, Object> proyecto) {
        ProyectoResponse response = new ProyectoResponse();

        response.setIdpro((Integer) proyecto.get("idpro"));
        response.setTipp((String) proyecto.get("tipp"));
        response.setTitp((String) proyecto.get("titp"));
        response.setFini((String) proyecto.get("fini"));
        response.setFfin((String) proyecto.get("ffin"));
        response.setBlq(String.valueOf(proyecto.get("blq")));
        return response;
    }

}