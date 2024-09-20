package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.AspiranteResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IAspiranteService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AspiranteService implements IAspiranteService {
    private final ConvocatoriaRepository convocatoriaRepository;

    public AspiranteService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<AspiranteResponse> getAspirantes(Integer id) {
        List<Map<String, String>> aspirant = convocatoriaRepository.aspirant(String.valueOf(id));
        return aspirant.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private AspiranteResponse entityToResponse(Map<String, String> aspirant) {
        AspiranteResponse response = new AspiranteResponse();

        response.setNombre((aspirant.get("nombre").toUpperCase()));
        response.setEstado(aspirant.get("estado"));
        response.setObs(aspirant.get("obs"));
        response.setFecha(String.valueOf(aspirant.get("fecha")));
        response.setDocid(Integer.parseInt(aspirant.get("docid").trim()));
        return response;
    }
}
