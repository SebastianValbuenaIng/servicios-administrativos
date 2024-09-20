package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.IdiomaResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.IdiomasEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.IdiomasRegistroRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IIdiomaService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IdiomaService implements IIdiomaService {
    private final IdiomasRegistroRepository idiomasRepository;

    public IdiomaService(IdiomasRegistroRepository idiomasRepository) {
        this.idiomasRepository = idiomasRepository;
    }

    public Set<IdiomaResponse> findAllByCedula(String cedula) {
        List<IdiomasEntity> idioma = idiomasRepository.findAllByIdemp(cedula);
        return idioma.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private IdiomaResponse entityToResponse(IdiomasEntity idiomasEntity) {
        IdiomaResponse response = new IdiomaResponse();

        response.setIdlen(idiomasEntity.getIdlen());
        response.setIdemp(idiomasEntity.getIdemp());
        response.setLengua(idiomasEntity.getLengua());
        response.setNivlec(idiomasEntity.getNivlec());
        response.setNivhab(idiomasEntity.getNivhab());
        response.setNivesc(idiomasEntity.getNivesc());
        response.setBlq(idiomasEntity.getBlq());
        return response;
    }

}
