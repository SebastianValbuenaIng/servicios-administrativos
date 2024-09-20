package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.DistincionResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IDistincionesServices;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DistincionesService implements IDistincionesServices {

    private final ConvocatoriaRepository convocatoriaRepository;

    public DistincionesService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<DistincionResponse> getDistinciones(Integer id){
        List<Map<String, Object>> distincion = convocatoriaRepository.distincion(String.valueOf(id));
        return distincion.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private DistincionResponse entityToResponse(Map<String, Object> distinciones) {
        DistincionResponse response = new DistincionResponse();

        response.setIddis((Integer) distinciones.get("iddis"));
        response.setNombre((String) distinciones.get("nombre"));
        response.setInstit((String) distinciones.get("instit"));
        response.setPais((String) distinciones.get("pais"));
        response.setAnio(String.valueOf(distinciones.get("anio")));
        response.setRazon((String) distinciones.get("razon"));
        response.setBlq(String.valueOf(distinciones.get("blq")));
        return response;
    }

}