package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.DocenciaResponse;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ConvocatoriaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IDocenciaService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DocenciaService implements IDocenciaService {
    private final ConvocatoriaRepository convocatoriaRepository;

    public DocenciaService(ConvocatoriaRepository convocatoriaRepository) {
        this.convocatoriaRepository = convocatoriaRepository;
    }

    public Set<DocenciaResponse> getDocencia(Integer id){
            List<Map<String, Object>> docencia = convocatoriaRepository.docencia(String.valueOf(id));
            return docencia.stream().map(this::entityResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private DocenciaResponse entityResponse(Map<String, Object> docencia) {
        DocenciaResponse response = new DocenciaResponse();

        response.setPeriodo((String) docencia.get("periodo"));
        response.setDocprof(Integer.valueOf(String.valueOf(docencia.get("docprof"))));
        response.setNom_asig((String) docencia.get("nom_asig"));
        response.setGrupo(Integer.valueOf(String.valueOf(docencia.get("grupo"))));
        response.setCan_alum((int) (Short) docencia.get("can_alum"));
        response.setCan_gan((int) (Short) docencia.get("can_gan"));
        response.setPerdida(Integer.valueOf(String.valueOf(docencia.get("perdida"))));
        response.setCancelaron(Integer.valueOf(String.valueOf(docencia.get("cancelaron"))));
        return response;
    }
}
