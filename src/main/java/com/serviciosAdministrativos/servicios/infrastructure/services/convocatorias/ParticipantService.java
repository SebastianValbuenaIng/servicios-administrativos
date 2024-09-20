package com.serviciosAdministrativos.servicios.infrastructure.services.convocatorias;

import com.serviciosAdministrativos.servicios.api.models.convocatorias.response.ParticipantResponse;
import com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias.AspiranteEntity;
import com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias.ParticipanteRepository;
import com.serviciosAdministrativos.servicios.infrastructure.abstract_services.convocatorias.IParticipantService;
import com.serviciosAdministrativos.servicios.util.errors.NotFoundError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "db5TransactionManager", readOnly = true)
public class ParticipantService implements IParticipantService {
    private final ParticipanteRepository participanteRepository;

    public ParticipantService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public ParticipantResponse getInfoAspirant(String document) {
        AspiranteEntity participant = participanteRepository.findById(document)
                .orElseThrow(() -> new NotFoundError("El participante no existe"));

        return entityToResponse(participant);
    }

    private ParticipantResponse entityToResponse(AspiranteEntity aspiranteEntity) {
        ParticipantResponse response = new ParticipantResponse();

        response.setDocid(aspiranteEntity.getDocid());
        response.setTdoc(aspiranteEntity.getTdoc());
        response.setAp1((aspiranteEntity.getAp1()).toUpperCase());
        response.setAp2((aspiranteEntity.getAp2()).toUpperCase());
        response.setNom((aspiranteEntity.getNom().toUpperCase()));
        response.setFnac(aspiranteEntity.getFnac());
        response.setCiunac(aspiranteEntity.getCiunac());
        response.setEciv(aspiranteEntity.getEciv());
        response.setTpsan(aspiranteEntity.getTpsan());
        response.setFrh(aspiranteEntity.getFrh());
        response.setLmil(aspiranteEntity.getLmil());
        response.setClase(aspiranteEntity.getClase());
        response.setDmil(aspiranteEntity.getDmil());
        response.setDirres(aspiranteEntity.getDirres());
        response.setTelres(aspiranteEntity.getTelres());
        response.setCiudad(aspiranteEntity.getCiudad());
        response.setCel(aspiranteEntity.getCel());
        response.setEmail(aspiranteEntity.getEmail());
        response.setFreg(aspiranteEntity.getFreg());
        response.setPerfil(aspiranteEntity.getPerfil());
        response.setGen(aspiranteEntity.getGen());

        return response;
    }
}
