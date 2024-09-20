package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.MembresiaResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.MembresiaEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.MembresiaRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IMembresiaService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MembresiaService implements IMembresiaService {
    public MembresiaRepository membresiaRepository;

    public MembresiaService(MembresiaRepository membresiaRepository) {
        this.membresiaRepository = membresiaRepository;
    }

    public Set<MembresiaResponse> findAllByIdemp(String id) {
        List<MembresiaEntity> membresia = membresiaRepository.findAllByIdemp(id);
        return membresia.stream().map(this::entityToResponse).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private MembresiaResponse entityToResponse(MembresiaEntity membresiaEntity) {
        MembresiaResponse response = new MembresiaResponse();

        response.setIdmem(membresiaEntity.getIdmem());
        response.setIdemp(Integer.valueOf(membresiaEntity.getIdemp()));
        response.setOrg(membresiaEntity.getOrg());
        response.setTipm(membresiaEntity.getTipm());
        response.setFing(membresiaEntity.getFing());
        response.setFret(membresiaEntity.getFret());
        response.setCat(membresiaEntity.getCat());
        response.setPais(membresiaEntity.getPais());
        return response;
    }
}