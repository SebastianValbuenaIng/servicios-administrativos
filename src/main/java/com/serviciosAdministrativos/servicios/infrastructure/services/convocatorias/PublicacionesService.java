package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.PublicacionesResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IPublicacionesService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PublicacionesService implements IPublicacionesService {

    private final ConvocatoriaRepository convocatoriaRepository;

    public PublicacionesService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<PublicacionesResponse> getPublicaciones(Integer id) {
            List<Map<String, Object>> publicacion = convocatoriaRepository.publicacion(String.valueOf(id));
            return publicacion.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private PublicacionesResponse entityToResponse(Map<String, Object> publicacion) {
        PublicacionesResponse response = new PublicacionesResponse();

        response.setIdpub((Integer) publicacion.get("idpub"));
        response.setTipo((String) publicacion.get("tipo"));
        response.setTitulo((String) publicacion.get("titulo"));
        response.setTitmay((String) publicacion.get("titmay"));
        response.setArea((String) publicacion.get("area"));
        response.setFec((String) publicacion.get("fec"));
        response.setEdic((String) publicacion.get("edic"));
        response.setEdit((String) publicacion.get("edit"));
        response.setCiudad((String) publicacion.get("ciudad"));
        response.setPais((String) publicacion.get("pais"));
        return response;
    }

}
